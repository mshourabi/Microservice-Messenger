FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /app

# client
COPY client/pom.xml client/pom.xml
COPY client/src client/src

RUN mvn -f client/pom.xml clean install -DskipTests

# telegram-agent
COPY telegram-agent/pom.xml telegram-agent/pom.xml
COPY telegram-agent/src telegram-agent/src

RUN mvn -f telegram-agent/pom.xml clean package -DskipTests


# =========================
# Runtime
# =========================

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/telegram-agent/target/*.jar app.jar

RUN useradd --system --create-home spring

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]