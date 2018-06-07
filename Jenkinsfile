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

   stage ('test') {
    if (isUnix()) {
     sh './gradlew test'
    } 
	else {
     bat './gradlew.bat test'
    }
   }
   
   stage ('Docker Build') {
    if (isUnix()) {
      sh 'docker build -t sravani/springboot-mongo:latest .'
    } 
    else {
      bat 'docker build -t sravani/springboot-mongo:latest .'
    }
   }
}
