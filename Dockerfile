# Usar una imagen base de Java compatible con ARM (Apple Silicon)
FROM openjdk:17-jdk

# Crear un directorio en el contenedor
WORKDIR /app

# Copiar el jar generado en el contenedor
COPY target/tripmate-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (cámbialo si tu app usa otro)
EXPOSE 8082

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]