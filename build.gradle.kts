repositories {
    jcenter()
}

plugins {
    java;
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

group = "com.jake.url.shortener"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets.getByName("test").java.srcDir("src/integrationtest/java")
sourceSets.getByName("test").resources.srcDir("src/integrationtest/resources")

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    compile("org.projectlombok:lombok")

    runtime("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}
