FROM openjdk:17
ADD target/bookstore-0.0.1-SNAPSHOT.jar bookstore.jar
EXPOSE 8585
ENTRYPOINT ["java","-jar","bookstore.jar"]