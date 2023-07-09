plugins {
    kotlin("jvm") version "1.8.20"
}

group = "io.github.itsk1mlot.tridentcool"
version = "1.0"

val testServerDir: String by project

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")

    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

task("fatJar", type = Jar::class) {
    dependsOn(tasks.jar)
    archiveBaseName.set("TridentCooldown")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

task("testJar") {
    val fatJar = tasks.getByName("fatJar") as Jar
    dependsOn(fatJar)

    if(!fatJar.archiveFile.get().asFile.exists()) return@task
    val destFile = File("$testServerDir/plugins/")
    doLast {
        copy {
            from(fatJar.archiveFile.get().asFile.absolutePath)
            into(destFile.absolutePath)
        }
    }
}