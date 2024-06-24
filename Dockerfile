# Usar uma imagem base do OpenJDK
FROM maven:3.8.3-openjdk-11-slim AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR da aplicação para o diretório de trabalho
COPY target/*.jar app.jar

# Expor a porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


