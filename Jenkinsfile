node {
   stage ('Checkout') {
    checkout scm
   }

   stage ('Build') {  
    if (isUnix()) {
      sh './gradlew clean build' 
    } 
	else {
      bat './gradlew.bat clean build'
    }
   }

   stage ('Test') {
    if (isUnix()) {
     sh './gradlew test'
    } 
	else {
     bat './gradlew.bat test'
    }
   }
   
   stage ('Nexus Upload') {
    nexusArtifactUploader(
      nexusVersion: 'nexus3',
      protocol: 'http',
      nexusUrl: 'localhost:8081',
      groupId: 'hello',
      version: '0.0.1-SNAPSHOT',
      repository: 'gradle-springboot-repo',
      credentialsId: 'nexus-admin-creds',
      artifacts: [
        [artifactId: 'springbootapp-pipeline',
         classifier: '',
         file: 'build/libs/springbootapp-pipeline-0.0.1-SNAPSHOT.jar',
         type: 'jar']
      ]
    )  
   }
   
   stage ('Docker Build') {
    if (isUnix()) {
      sh 'docker build -t sravanimadireddy/springboot-restapi:latest .'
    } 
    else {
      bat 'docker build -t sravanimadireddy/springboot-restapi:latest .'
    }
   }
   
   stage('Publish') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'dockerhub-credsPassword', usernameVariable: 'dockerhub-credsUser')]) 
		{
		  bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          bat 'docker push sravanimadireddy/springboot-restapi:latest'
        }
    }
   }
}
