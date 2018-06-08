FROM anapsix/alpine-java
LABEL maintainer="sravani.madireddy14@gmail.com"
COPY build/libs/springbootapp-pipeline-0.0.1-SNAPSHOT.jar /home/springbootapp-pipeline-0.0.1-SNAPSHOT.jar
CMD ["java","-Dspring.data.mongodb.uri=mongodb://spring-demo-mongo:27017/test","-jar","/home/springbootapp-pipeline-0.0.1-SNAPSHOT.jar"]
