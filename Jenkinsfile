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
}
