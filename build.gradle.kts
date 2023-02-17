import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    kotlin("plugin.spring") version "1.7.22"
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("plugin.jpa") version "1.4.10" // wrapped no-arg (kotlin("plugin.noarg") version "1.4.10" 을 포함)
}

allOpen {
    annotation("javax.persistence.Entity") // @Entity가 붙은 클래스에 한해서만 all open 플러그인을 적용
}

noArg {
    annotation("javax.persistence.Entity") // @Entity가 붙은 클래스에 한해서만 no arg 플러그인을 적용
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

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
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
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("io.kotest:kotest-runner-junit5:5.5.4")
        testImplementation("io.kotest:kotest-assertions-core:5.5.4")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    }
}

project(":domain") {
    dependencies {
        runtimeOnly("com.mysql:mysql-connector-j")
        api("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("com.h2database:h2:2.1.214")
        implementation("org.modelmapper:modelmapper:3.1.1")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    }
}
