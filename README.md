# micro-services-test
Build 3 microservices:

# Registration Service
Using Eureka in order to allow services to find and communicate with each other without hard-coding hostname and port. 
http://localhost:8761

# Shuffle Service
Get a number between 1 to 1000 using post request, return a shuffled array from 1 to this number.
Without duplications of numbers. complexity time- O(N).
Send the generated array to another service (service-log). 
The host & port is taken from Eureka service. The path is taken from application.properties
http://localhost:8081/service-shuffle

# Log Service
Get an object request, and log it to console.
http://localhost:8082/service-log

# Requirements
* Java 8
* Maven 

# Install
1. Clone the project
2. For each subdirectory - run *'mvn clean install'*
3. For each jar(under target directory) - run *'java -jar <service_name>.jar'*

      <service_name> = jar name (i.e *service-registration-0.0.1-SNAPSHOT*)
    
**Pay attention** - You should run 'Registration Service' first.
