# Usa una imagen base con Gradle preinstalado (en este caso openjdk + gradle)
FROM gradle:8-jdk23 as build

# Establece el directorio de trabajo
WORKDIR /coupon-api

# Copia los archivos de tu proyecto al contenedor
COPY . .

# Ejecuta el comando para construir el JAR con Gradle
RUN gradle build --no-daemon

# Usa una imagen más ligera con solo JDK para ejecutar la aplicación
FROM openjdk:23-jdk-slim

# Establece el directorio de trabajo
WORKDIR /coupon-api

# Copia el archivo JAR construido desde la fase anterior
COPY --from=build /coupon-api/build/libs/*.jar coupon-api-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que la aplicación correrá
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "coupon-api-0.0.1-SNAPSHOT.jar"]
