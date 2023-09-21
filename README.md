# Malcheck
Malcheck is a REST API-based tool that aims to check if there is malware in a file. This tool is implemented using the help of [Yara](https://github.com/VirusTotal/yara) and also uses a collection of rules that can be seen at [Yara-Rules](https://github.com/Yara-Rules/rules).

Malcheck itself is built using the [Spring framework](https://spring.io/) version 3.X framework which is documented using [Swagger](https://swagger.io/).



## Installation Guide
1. You should Have [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) (I don't know if other versions work).
2. Create a private-application.properties file in the project root 
3. Write `uploaded-file-dir=` and append it with the directory path where you want to put the uploaded file (the file will be deleted after yara finishes checking).
4. Build the application using `./mvnw clean install` (adjust the command according to your needs). The jar file should be in target folder
5. Run the application using `java -jar .\target\malcheck-1.0.0.jar` (adjust the command according to your needs)
6. Enter http://localhost:8080/docs in your browser to check the swagger page.