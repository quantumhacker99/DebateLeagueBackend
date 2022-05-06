FROM openjdk:8
COPY ./debate_league-0.0.1-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java", "-jar", "debate_league-0.0.1-SNAPSHOT.jar"]
