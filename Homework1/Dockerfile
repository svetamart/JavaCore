FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1

COPY ./src ./src

RUN mkdir ./out

RUN javac -sourcepath ./src -d out src/main/java/org/example/Main.java

CMD java -classpath ./out main.java.org.example.Main

