FROM maven:3.9-eclipse-temurin-25 AS builder

WORKDIR /app

# client
COPY client/pom.xml client/pom.xml
COPY client/src client/src

RUN mvn -f client/pom.xml clean install -DskipTests

# gateway
COPY gateway/pom.xml gateway/pom.xml
COPY gateway/src gateway/src

RUN mvn -f gateway/pom.xml clean package -DskipTests


# =========================
# Runtime
# =========================

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/gateway/target/*.jar app.jar

RUN useradd --system --create-home spring

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]