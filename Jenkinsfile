pipeline {
    agent any

    environment {

        MAVEN_HOME = "/usr/local/maven"
        TOMCAT_USER = credentials('admin')
        TOMCAT_PASS = credentials('babu')
        TOMCAT_URL = "http://localhost:8080/manager/html"
        WAR_PATH = "target/CI-CD-Jenkins-0.0.1-SNAPSHOT.war"
    }

    tools {
            maven 'Maven'
        }

    stage('Build') {
        steps {
            sh 'mvn clean package'
        }
    }



    stage('Deploy to Tomcat') {
        steps {
            script {
                sh """
                curl --user ${TOMCAT_USER}:${TOMCAT_PASS} \
                --upload-file ${WAR_PATH} \
                "${TOMCAT_URL}/deploy?path=/your-app-name&update=true"
                """
            }
        }
    }


    post {
        always {
            // Clean up workspace after build
            cleanWs()
        }
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
