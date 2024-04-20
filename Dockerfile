FROM openjdk:11
EXPOSE 8080
ADD target/ATD-springboot.jar ATD-springboot.jar 
ENTRYPOINT [ "java", "-jar","/ATD-springboot.jar " ]