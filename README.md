# url-shortener  
This project provides "URL shortener" service.
## Technologies  
Java version: 1.8  
Gradle: 5.4.1  
Spring boot: 2.1.6.RELEASE  
H2 database: 1.4.199  
HikariCP: 3.2.0  
Embed Tomcat: 9.0.21  
Junit jupiter: 5.3.2  
## Setup  
* To run this project, you need Java SDK(JDK) 1.8.    
#### Set environment variables(Required)
Environment variables are used in the project (application-{profile}.yml). You can not skip this step.
You can modify them, if you need.  
The project has 3 types of profiles. (dev, stag, prod)  
Use "dev" profile for testing.  
```  
$ export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
$ export SPRING_PROFILES_ACTIVE=dev
$ export SHORT_ROOT_URL=http://localhost:8080
$ export DATABASE_URL=jdbc:h2:~/test.h2;FILE_LOCK=SOCKET
$ export DATABASE_USERNAME=sa
$ export DATABASE_PASSWORD=
```  
#### Build project  
This project has gradle wrapper. You can build it without installing gradle.
```  
$ ./gradlew clean build
```  
#### Run  
There is 2 ways you can run this project with. Run JAR file and use gradle "bootRun" task.  
##### jar run  
```  
$ java -jar ./build/libs/url-shortener-0.0.1-SNAPSHOT.jar
```  
##### gradle bootRun  
```  
$ ./gradlew clean bootRun
```  
#### Usage  
Generate short url.
```  
curl -X POST \
  http://localhost:8080/url-shorteners \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-length: 129' \
  -d '{
	"url" : "https://www.google.com/search?q=teseturllal&oq=teseturllal&aqs=chrome..69i57j0l5.2753j0j7&sourceid=chrome&ie=UTF-8"
}'


Response
{"shortUrl":"http://localhost:8080/b","originalUrl":"https://www.google.com/search?q=teseturllal&oq=teseturllal&aqs=chrome..69i57j0l5.2753j0j7&sourceid=chrome&ie=UTF-8"}
```  
Access generated short url.
```  
$ open http://localhost:8080/b
```  