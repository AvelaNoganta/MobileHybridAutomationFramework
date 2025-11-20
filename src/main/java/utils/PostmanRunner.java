package utils;

import base.ReportManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostmanRunner {

    /**
     * Executes a Postman (Newman) collection from within the automation framework.
     * The output is streamed into ExtentReports and also printed to console.
     *
     * @param collectionPath   Full path to the Postman collection JSON file
     * @param environmentPath  Optional Postman environment file (can be null or empty)
     */
    public static void runPostmanCollection(String collectionPath, String environmentPath) {
        try {
            String command;

            // -----------------------------------------
            // Build the correct Newman command
            // If an environment file is provided, attach it.
            // -----------------------------------------
            if (environmentPath == null || environmentPath.isEmpty()) {
                command = String.format(
                        "newman run \"%s\" --reporters cli,json --reporter-json-export Reports/newman_report.json",
                        collectionPath
                );
            } else {
                command = String.format(
                        "newman run \"%s\" -e \"%s\" --reporters cli,json --reporter-json-export Reports/newman_report.json",
                        collectionPath, environmentPath
                );
            }

            // Log to report that we're starting Postman execution
            ReportManager.getTest().info("Executing Postman collection: " + collectionPath);

            // ------------------------------------------------------
            // Use ProcessBuilder to execute the Newman CLI command
            // ------------------------------------------------------
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);

            // Merge standard error with standard output so all output is captured
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // ------------------------------------------------------
            // Read the Newman output stream line-by-line
            // Stream it into both console and ExtentReports
            // ------------------------------------------------------
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                ReportManager.getTest().info(line);
            }

            // ------------------------------------------------------
            // Wait until Newman finishes executing
            // ------------------------------------------------------
            int exitCode = process.waitFor();

            // Evaluate execution result
            if (exitCode == 0) {
                ReportManager.getTest().pass("Postman collection executed successfully.");
            } else {
                ReportManager.getTest().fail("Postman collection execution failed with exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {

            // If any error happens during process execution, log it
            ReportManager.getTest().fail("Error running Postman collection: " + e.getMessage());
        }
    }
}
