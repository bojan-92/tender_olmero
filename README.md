## Built With
 - [Maven](https://maven.apache.org/) - Dependency Management
 - [Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
 - [JDK-8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Java™ Platform, Standard Edition Development Kit
 - [MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System
 - [git](https://git-scm.com/) - Version control system
 - [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
 
## External Tools Used
 - [Postman](https://www.postman.com/) - API Development Environment
 
## Running the Application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the  com.olmero.tender.TenderApplication class from your IDE.
 - Download the zip or clone the Git repository.
 - Unzip the zip file (if you downloaded one)
 - Execute resources/propagate.sql script
 - Be sure that you have Lombok plugin installed in your IDE
 - Open Intellj -> choose to open project -> right click on pom.xml -> Maven -> Generate Sources and Update Folders
 - Choose the TenderApplication class (search for @SpringBootApplication)
 - Click 'TenderApplication' nest to the class signature
Alternatively you can use the Spring Boot Maven plugin like so:
 `mvn spring-boot:run`


