FROM anapsix/alpine-java
LABEL maintainer="sravani.madireddy14@gmail.com"
ADD build/libs/sample-0.0.2-SNAPSHOT.jar /home/springboot-mongodb-restapi-2017-0.0.2-SNAPSHOT.jar
RUN chmod -R 777 /home/springboot-mongodb-restapi-2017-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","-Dspring.data.mongodb.host=mongo-service","/home/springboot-mongodb-restapi-2017-0.0.2-SNAPSHOT.jar"]
