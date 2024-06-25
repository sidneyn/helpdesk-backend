# Usar uma imagem base do OpenJDK com Maven para a etapa de build
FROM maven:3.8.3-openjdk-11-slim AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o projeto Maven para o diretório de trabalho
COPY . .

# Compilar o projeto, pulando os testes
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para a etapa final
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR da aplicação da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]