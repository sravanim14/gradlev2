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
        withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) 
		{
		  bat "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          bat 'docker push sravanimadireddy/springboot-restapi:latest'
        }
   }
   
   stage('Deploy Application') {   
	    bat "kubectl create -f kub-deploy-files/mongo-service.yaml"
		bat "kubectl create -f kub-deploy-files/mongo-controller.yaml"
	    bat "kubectl create -f kub-deploy-files/deployment.yaml"
	    bat "kubectl create -f kub-deploy-files/service.yaml"
		bat "powershell.exe SET-Variable -Name PORT -Value \$(kubectl get services/spring-boot-service -o go-template='{{(index .spec.ports 0).nodePort}}')"
	    bat "powershell.exe \$Portvalue=Get-Variable PORT -ValueOnly"
        bat "powershell.exe \$message="My services are running on http://127.0.0.1:" + \$Portvalue"
        bat "\$message"
   }
}