pipeline {
    agent any

    environment {

        MAVEN_HOME = "/usr/local/maven"
        TOMCAT_CREDS = credentials('tomcat')
        TOMCAT_URL = "http://localhost:8080/manager/text"
        WAR_PATH = "target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war"
    }

    tools {
            maven 'maven'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }



        stage('Deploy to Tomcat') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'tomcat', usernameVariable: 'TOMCAT_USER', passwordVariable: 'TOMCAT_PASS')]) {
                        sh """
                        curl --user ${TOMCAT_USER}:${TOMCAT_PASS} \
                        --upload-file ${WAR_PATH} \
                        "${TOMCAT_URL}/deploy?path=/your-app-name&update=true"
                        """
                    }
                }
            }
        }
    }


    post {

        success {
            // Notify success
            echo 'Application deployed successfully!'
        }
        failure {
            // Notify failure
            echo 'Application deployment failed.'
        }
    }

}
