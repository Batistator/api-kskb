# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el código antes de instalar dependencias
COPY . .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Ejecuta el empaquetado de la aplicación
RUN ./mvnw clean package -DskipTests

# Copia el JAR generado al directorio raíz del contenedor
RUN cp ./target/*.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
