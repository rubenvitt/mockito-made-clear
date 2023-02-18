plugins {
    id("java")
}

group = "dev.rubeen.java"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.mockito:mockito-junit-jupiter:5.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.mockito:mockito-core:5.1.1")
}

tasks.test {
    useJUnitPlatform()
}