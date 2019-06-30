# url-shortener  
This project is for $!@#$@#$%#$%#$%#$%#@$  
## Technologies  
Java version: 1.8  
Gradle: 5.4.1  
Spring boot: 2.1.6.RELEASE  
H2 database: 1.4.199  
HikariCP: 3.2.0  
Embed Tomcat: 9.0.21  
Junit jupiter: 5.3.2  
## Setup  
* To run this project, you need the Java SDK(JDK).    
#### Set environment variables  
```  
$ export SPRING_PROFILES_ACTIVE=dev
$ export SHORT_ROOT_URL=http://localhost:8080
$ export DATABASE_URL=jdbc:h2:~/test.h2;FILE_LOCK=SOCKET
$ export DATABASE_USERNAME=sa
$ export DATABASE_PASSWORD=
```  
#### Build project    
```  
$ ./gradlew clean build
```  
#### Run  
jar run  
```  
$ java -jar ./build/libs/url-shortener-0.0.1-SNAPSHOT.jar
```  
gradle run  
```  
$ ./gradlew clean bootrun
```  
