FROM maven:3.9.9-amazoncorretto-21
WORKDIR /app
COPY . .
RUN mvn clean install
ENTRYPOINT ["mvn", "test"]