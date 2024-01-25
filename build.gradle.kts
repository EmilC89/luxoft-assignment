plugins {
    id("java")
    application
}

group = "org.assignment"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

application {
//    mainClassName = "org.assignment.AmazonPriceFinder"
    mainClass.set("org.assignment.AmazonPriceFinder")
}

tasks.test {
    useJUnitPlatform()
}