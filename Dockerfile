FROM maven as build
RUN mkdir /app
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests  

FROM openjdk:18
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
