FROM azul/zulu-openjdk:11
ADD build/libs/servicea-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app.jar"]