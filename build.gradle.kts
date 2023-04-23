plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.20" // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.dokka") version "1.7.10"
    id("com.palantir.git-version") version "0.12.3"
    id("maven-publish")
    id("signing")

    `java-library` // For API and implementation separation.
    `kotlin-dsl`
}

val executableName = "fabrikt"

group = "com.electronicmuse"
val gitVersion: groovy.lang.Closure<*> by extra
version = gitVersion.call()

val projectUrl = "https://github.com/rlodge/fabrikt"
val projectScmUrl = "scm:https://rlodge@github.com/rlodge/fabrikt.git"
val projectScmConUrl = "scm:https://rlodge@github.com/rlodge/fabrikt.git"
val projectScmDevUrl = "scm:git://github.com/rlodge/fabrikt.git"
val projectIssueUrl = "https://github.com/rlodge/fabrikt/issues"
val projectName = "Fabrikt"
val projectDesc = "Fabricates Kotlin code from OpenApi3 specifications"
val projectLicenseName = "Apache License 2.0"
val projectLicenseUrl = "https://opensource.org/licenses/Apache-2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.7.20"))
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.14.2"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.jknack:handlebars:4.3.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.beust:jcommander:1.82")
    implementation("com.reprezen.kaizen:openapi-parser:4.0.4") { exclude(group = "junit") }
    implementation("com.reprezen.jsonoverlay:jsonoverlay:4.0.4")
    implementation("com.squareup:kotlinpoet:1.12.0") { exclude(module = "kotlin-stdlib-jre7") }
    implementation("com.google.flogger:flogger:0.7.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.1.0")
    testImplementation("org.assertj:assertj-core:3.14.0")

    // Below dependencies are solely present so code examples in the test resources dir compile
    testImplementation("javax.validation:validation-api:2.0.1.Final")
    testImplementation("jakarta.validation:jakarta.validation-api:3.0.2")
    testImplementation("org.springframework:spring-webmvc:5.3.23")
    testImplementation("org.springframework.security:spring-security-web:5.4.0")
    testImplementation("io.micronaut:micronaut-core:3.8.4")
    testImplementation("io.micronaut:micronaut-http:3.8.4")
    testCompileOnly("io.micronaut.security:micronaut-security:3.8.3")
    testImplementation("com.squareup.okhttp3:okhttp:4.9.1")
    testImplementation("com.pinterest.ktlint:ktlint-core:0.41.0")
    testImplementation("com.pinterest:ktlint:0.41.0")
    testImplementation("org.openapitools:jackson-databind-nullable:0.2.6")
}

tasks {
    create("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.getByName("main").allSource)
    }

    create("kotlinDocJar", Jar::class) {
        group = JavaBasePlugin.DOCUMENTATION_GROUP
        description = "Assembles Kotlin docs with Dokka"
        archiveClassifier.set("javadoc")
        from(dokkaHtml)
        dependsOn(dokkaHtml)
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

publishing {
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("fabrikt") {

            artifactId = "fabrikt"

            artifact(tasks["sourcesJar"])
            artifact(tasks["kotlinDocJar"])

            from(components["java"])

            pom {
                name.set(projectName)
                description.set(projectDesc)
                url.set(projectUrl)
                inceptionYear.set("2020")
                licenses {
                    license {
                        name.set(projectLicenseName)
                        url.set(projectLicenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set("cjbooms")
                        name.set("Conor Gallagher")
                        email.set("cjbooms@gmail.com")
                    }
                    developer {
                        id.set("averabaq")
                        name.set("Alejandro Vera-Baquero")
                        email.set("averabaq@gmail.com")
                    }
                    developer {
                        id.set("rlodge")
                        name.set("Ross Lodge")
                        email.set("ross.lodge@electronicmuse.com")
                    }
                }
                scm {
                    connection.set(projectScmConUrl)
                    developerConnection.set(projectScmDevUrl)
                    url.set(projectScmUrl)
                }
            }
        }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)

    sign(publishing.publications["fabrikt"])
}
