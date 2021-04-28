Please build services:
    * mvnw clean install spring-boot:repackage

After add docker images:
    * docker build --target docker-rest-service -t docker-rest-service:1.0 .
    * docker build --target docker-rest-service-meta -t docker-rest-service-meta:1.0 .

Then start docker-compose and see http://localhost:8081/api/students/2 or http://localhost:8081/api/students/1