plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.appium:java-client:9.0.0") {
        exclude(group = "org.seleniumhq.selenium", module = "selenium-support")
        exclude(group = "org.seleniumhq.selenium", module = "selenium-api")
    }
    implementation("org.seleniumhq.selenium:selenium-java:4.14.1")
    implementation("io.github.bonigarcia:webdrivermanager:5.6.2")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.json:json:20210307")
    testImplementation("org.testng:testng:7.4.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}
