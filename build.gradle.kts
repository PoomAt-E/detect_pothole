import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "com.detect_pothole"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allOpen{
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

extra["springCloudGcpVersion"] = "4.1.4"
extra["springCloudVersion"] = "2022.0.2"

dependencies {
    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")


// https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-spatial
    implementation("org.hibernate:hibernate-spatial:6.1.6.Final")
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:6.1.6.Final")




    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    //gcp
    implementation("com.google.cloud:spring-cloud-gcp-starter-storage")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
