plugins {
    kotlin("jvm")
}

group = "com.github.cong"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven ( url = "https://www.jitpack.io" )
    maven ( url = "https://oss.sonatype.org/content/repositories/snapshots" )
    maven ( url = "https://oss.sonatype.org/content/repositories/releases" )
    maven ( url = "https://maven.tinylake.tk/")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":satori"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}