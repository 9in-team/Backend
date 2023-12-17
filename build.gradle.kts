import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("kapt") version "1.4.10"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.springframework.boot") version "2.7.8" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    idea
}
java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "team.guin"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring") // all-open
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("com.h2database:h2:2.1.214")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("com.querydsl:querydsl-jpa:5.0.0")
        kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
        kapt("org.springframework.boot:spring-boot-configuration-processor")
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":api") {
    tasks.getByName("bootJar") {
        enabled = true
    }

    tasks.getByName("jar") {
        enabled = false
    }

    dependencies {
        implementation(project(":domain"))
        implementation(project(":web-common"))
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        implementation("it.ozimov:embedded-redis:0.7.1")
        implementation("org.springframework.session:spring-session-data-redis")
        implementation("org.springframework.boot:spring-boot-starter-validation:2.7.5")
        testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
        testImplementation("io.kotest:kotest-assertions-core:5.5.4")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter-websocket")
        implementation("com.nimbusds:nimbus-jose-jwt:3.10")
    }
}

project(":domain") {

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    dependencies {
        runtimeOnly("com.mysql:mysql-connector-j")
        api("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.modelmapper:modelmapper:3.1.1")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
        testImplementation("io.kotest:kotest-assertions-core:5.5.4")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    }
    kotlin.sourceSets.main {
        setBuildDir("$buildDir/generated/source/kapt/main")
    }
}

project(":web-common") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.core:jackson-core:2.12.5")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.12.5")
        testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
        testImplementation("io.kotest:kotest-assertions-core:5.5.4")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
        testImplementation("io.mockk:mockk:1.12.0")
    }
}
