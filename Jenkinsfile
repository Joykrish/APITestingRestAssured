pipeline {
    agent any
    
    stages {
        stage('Execute the framework') {
            steps {
                echo 'to execute the framework'
				bat 'mvn clean install'
            }
        }
    }
}