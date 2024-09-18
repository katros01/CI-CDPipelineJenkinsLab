pipeline {
    agent any

    environment {

        MAVEN_HOME = "/usr/local/maven"  // Path to Maven installation
        TOMCAT_USER = credentials('admin') // Tomcat manager username stored in Jenkins credentials
        TOMCAT_PASS = credentials('babu') // Tomcat manager password stored in Jenkins credentials
        TOMCAT_URL = "http://localhost:8080/manager/html" // Tomcat manager URL
        WAR_PATH = '' // Path to your built WAR file
    }

        stage('Build') {
            steps {
                // Run Maven to clean and package the project
                sh 'mvn clean package'
            }
        }

        stage('Find WAR') {
            steps {
                WAR_PATH = sh(
                    script: 'find target -name "*.war" | head -n 1',
                    returnStdout: true
                ).trim()

                if (WAR_PATH == '') {
                    error('WAR file not found!')
                }

                echo "WAR file found at: ${WAR_PATH}"
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Deploy WAR to Tomcat using curl
                    sh """
                    curl --user ${TOMCAT_USER}:${TOMCAT_PASS} \
                    --upload-file ${WAR_PATH} \
                    "${TOMCAT_URL}/deploy?path=/your-app-name&update=true"
                    """
                }
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
