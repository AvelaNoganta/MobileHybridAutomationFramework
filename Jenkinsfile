
pipeline {
  agent any
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build & Test') { steps { sh 'mvn -q -e clean test' } }
    stage('Publish Report') {
      steps {
        publishHTML(target: [
          reportName: 'Extent Report',
          reportDir: 'Reports',
          reportFiles: '**/ExtentReport_*.html',
          keepAll: true, allowMissing: true, alwaysLinkToLastBuild: true
        ])
        archiveArtifacts artifacts: 'Reports/**', fingerprint: true
      }
    }
  }
}
