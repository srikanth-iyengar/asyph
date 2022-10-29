pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building'
            }
        }
        stage('Api bootJar') {
            steps {
                echo "Deploying"
                sh "cd ApiGateway && chmod +x ./gradlew && ./gradlew bootJar && cd .."
            }
        }
        stage('Judge bootJar') {
            steps {
                sh "cd UserMicroservice && chmod +x ./gradlew && ./gradlew bootJar"
            }
        }
    }
}
