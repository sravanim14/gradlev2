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
    
   stage ('Docker Build') {
    if (isUnix()) {
      sh 'docker build -t sravanimadireddy/springboot-restapi:v2 .'
    } 
    else {
      bat 'docker build -t sravanimadireddy/springboot-restapi:v2 .'
    }
   }
   
   stage('Publish') {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) 
		{
		  bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          bat 'docker push sravanimadireddy/springboot-restapi:v2'
        }
   }
}
