pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '15'))
    }
    stages {
        stage('Build') {
            agent {
                docker { 
                    image '3.5.3-jdk-8-alpine'
                    reuseNode true
                    args '-v mvn-repo:/root/.m2'
                }
            }
            steps {
                sh '''
                    mvn package
                    '''
            }
        }
        stage('Deploy') {
            agent {
                docker { 
                    image 'grolland/aws-cli'
                    reuseNode true 
                }
            }
            environment {
                AWS_DEFAULT_REGION = 'eu-central-1'
                AWS_ACCESS_KEY_ID = credentials('AWS_KEY_EVENT2CALENDAR_ID')
                AWS_SECRET_ACCESS_KEY = credentials('AWS_KEY_EVENT2CALENDAR_KEY')
                STACK_NAME = 'awscodestar-event2calendar-lambda'
            }
            steps {
                s3Upload consoleLogLevel: 'INFO', dontWaitForConcurrentBuildCompletion: false, entries: [[bucket: 'cfn-infra-jenkins-artifacts', excludedFile: '', flatten: false, gzipFiles: false, keepForever: false, managedArtifacts: true, noUploadOnFailure: true, selectedRegion: 'eu-central-1', showDirectlyInBrowser: false, sourceFile: '**/target/*.jar', storageClass: 'STANDARD', uploadFromSlave: false, useServerSideEncryption: false]], pluginFailureResultConstraint: 'FAILURE', profileName: 'ARTIFACTS', userMetadata: []
                sh '''
                    cd cfn
                    chmod +x prepare_template.sh
                    ./prepare_template.sh
                    aws cloudformation package --template aws-resources.yml --s3-bucket $S3_BUCKET --output-template template-export.yml
                    aws cloudformation deploy  --template-file=template-export.yml --stack-name="${STACK_NAME}" --capabilities=CAPABILITY_NAMED_IAM
                    '''
            }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}