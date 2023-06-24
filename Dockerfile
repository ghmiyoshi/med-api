FROM maven:3.8.3-openjdk-17
WORKDIR /med-api
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run -Dspring-boot.run.profiles=prod
