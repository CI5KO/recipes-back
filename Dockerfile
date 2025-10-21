# Entorno de desarrollo
FROM openjdk:17-jdk-slim AS development

WORKDIR /app

RUN apt-get update && apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

COPY pom.xml .
RUN mvn dependency:go-offline

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]

# Entorno de producción
FROM openjdk:17-jdk-slim AS production

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests && \
    apt-get remove -y maven && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

EXPOSE 8080

CMD ["java", "-jar", "target/recipes-back-1.0.0.jar"]

# Por defecto usa desarrollo
FROM development
