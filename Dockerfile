FROM anapsix/alpine-java
LABEL maintainer="sravani.madireddy14@gmail.com"
WORKDIR C:\Users\VenkataSatyaSravaniM\.jenkins\workspace\sample
COPY build/libs/V2-springbootapp-pipeline-0.0.2-SNAPSHOT.jar /home/springbootapp-pipeline-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","-Dspring.data.mongodb.host=mongo-service","/home/springbootapp-pipeline-0.0.2-SNAPSHOT.jar"]
