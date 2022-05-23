FROM openjdk:17
WORKDIR /app
ADD ./target/laboratuvar-raporlama-0.0.1-SNAPSHOT.jar laboratuvar-raporlama-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","laboratuvar-raporlama-0.0.1-SNAPSHOT.jar"]

