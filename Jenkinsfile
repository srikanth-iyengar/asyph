pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building'
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying"
                sh "cd ApiGateway && chmod +x ./gradlew && ./gradlew bootJar"
                sh "cd OnlineJudge && ./gradlew bootJar"
            }
        }
    }
}
