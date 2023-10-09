FROM openjdk:17-oracle

COPY target/contact-app-0.0.1-SNAPSHOT.jar contact-app-0.0.1-SNAPSHOT.jar

# Run the Jar file
ENTRYPOINT ["java","-jar","contact-app-0.0.1-SNAPSHOT.jar"]