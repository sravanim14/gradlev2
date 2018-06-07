node {
   stage ('Checkout') {
    git url: "https://github.com/sravanim14/gradle-springboot-mongodb.git"
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