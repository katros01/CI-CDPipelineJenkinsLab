pipeline {
    agent any

    environment {

        DOCKER_IMAGE = 'katros01/cicdjenkins'
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        DOCKER_CREDENTIALS_ID = credentials('docker')
        TOMCAT_CREDS = credentials('tomcat')
        TOMCAT_SERVER_URL = "localhost"
        WAR_PATH = "target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war"
    }

    tools {
            maven 'maven'
        }

    stages {
        stage('Checkout Code') {
                steps {
                    git url: 'https://github.com/katros01/CI-CDPipelineJenkinsLab.git', branch: 'main'
                }
        }

        stage('Build Maven Project') {
            steps {
                script {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                        bat "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                    }

                }
            }
        }

        stage('Deploy WAR to Tomcat') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'tomcat', usernameVariable: 'TOMCAT_USER', passwordVariable: 'TOMCAT_PASS')]) {
                        bat """
                        curl --upload-file target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war "http://%TOMCAT_USER%:%TOMCAT_PASS%@localhost:8080/manager/text/deploy?path=/CI-CD-Jenkins&update=true"
                        """
                    }
                }
            }
        }
    }

    post {

        always {
            echo 'Cleaning workspace...'
            cleanWs()
        }

        success {
            echo 'Application deployed successfully!'
        }

        failure {
            echo 'Application deployment failed.'
        }
    }
}
