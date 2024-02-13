pipeline {
    agent any
    
    stages {
        stage('Execute the framework') {
            steps {
                echo 'to execute the framework'
				bat 'mvn test'
            }
        }
         stage('Report Generation') {
            steps {
                echo 'to execute the framework'
				publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'reports', reportFiles: 'TestReport.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
            }
        }
    }
}

