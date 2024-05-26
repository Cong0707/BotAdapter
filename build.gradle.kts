plugins {
    kotlin("jvm") version "2.0.0-RC3"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven ( url = "https://www.jitpack.io" )
    maven ( url = "https://oss.sonatype.org/content/repositories/snapshots" )
    maven ( url = "https://oss.sonatype.org/content/repositories/releases" )
    maven ( url = "https://maven.tinylake.tk/")
}

group = "com.github.cong"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(17)
}