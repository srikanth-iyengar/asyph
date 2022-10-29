pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building'
            }
        }
        stage('bootJar') {
            steps {
                echo "Deploying"
                sh "cd ApiGateway && chmod +x ./gradlew && ./gradlew bootJar && cd .."
                sh "cd OnlineJudge && chmod +x ./gradlew && ./gradlew bootJar && cd .."
            }
        }
    }
}
