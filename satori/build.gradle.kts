plugins {
    kotlin("jvm")
}

group = "com.github.cong"
version = "0.1.0"

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
    implementation("com.github.Nyayurn:Yutori-Next:0568ef2419")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}