# url-shortener  
This project provides "URL shortener" service.  
It uses Base62 encoding to generate short url with "long value".  
The "long value" means Java Long type. It is managed by Database (SHORT_URL_KEY table).  
Except negative value, it covers 1 ~ 9,223,372,036,854,775,807.  
Base62 encoder generate value with those long values.  
Results of generated base62 value covers "b" ~ "k9viXaIfiWh".  
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
Environment variables are used in the project (application-{profile}.yml).  
You can not skip this step.  
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
$ curl -X POST \
  http://localhost:8080/url-shorteners \
  -H 'Content-Type: application/json' \
  -d '{
	"url" : "https://www.google.com/search?source=hp&ei=0mgSXIPvE4iVkwX3263oCQ&q=kotlin&btnK=Google+Search&oq=kotlin&gs_l=psy-ab.3..0l10.1355.2447..2835...0.0..0.76.480.7......0....1..gws-wiz.....0.h8HxJVy-QF0"
}'


# Response
{"shortUrl":"http://localhost:8080/b","originalUrl":"https://www.google.com/search?source=hp&ei=0mgSXIPvE4iVkwX3263oCQ&q=kotlin&btnK=Google+Search&oq=kotlin&gs_l=psy-ab.3..0l10.1355.2447..2835...0.0..0.76.480.7......0....1..gws-wiz.....0.h8HxJVy-QF0"}
```  
Access generated short url.  
```  
$ open http://localhost:8080/b
```  
