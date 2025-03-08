# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Copia el archivo JAR de Spring Boot
COPY target/*.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]