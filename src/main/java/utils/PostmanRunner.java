package utils;

import base.ReportManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PostmanRunner {

    /**
     * Runs a Postman collection using Newman CLI
     *
     * @param collectionPath Path to Postman collection JSON
     * @param environmentPath Optional environment file path
     */
    public static void runPostmanCollection(String collectionPath, String environmentPath) {
        try {
            String command;

            if (environmentPath == null || environmentPath.isEmpty()) {
                command = String.format("newman run \"%s\" --reporters cli,json --reporter-json-export Reports/newman_report.json",
                        collectionPath);
            } else {
                command = String.format("newman run \"%s\" -e \"%s\" --reporters cli,json --reporter-json-export Reports/newman_report.json",
                        collectionPath, environmentPath);
            }

            ReportManager.getTest().info("Executing Postman collection: " + collectionPath);
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                ReportManager.getTest().info(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                ReportManager.getTest().pass("Postman collection executed successfully.");
            } else {
                ReportManager.getTest().fail("Postman collection execution failed with exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            ReportManager.getTest().fail("Error running Postman collection: " + e.getMessage());
        }
    }
}
