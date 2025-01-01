plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.7.7"
    id("io.github.goooler.shadow") version "8.1.7"
}

group = "me.kryz.dedsacore"
version = "1.0-SNAPSHOT"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-snapshots"
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    paperweight.paperDevBundle("1.21.3-R0.1-SNAPSHOT")
    implementation("org.incendo:cloud-core:2.0.0")
    implementation("org.incendo:cloud-paper:2.0.0-beta.10")
    implementation("org.incendo:cloud-annotations:2.0.0")
}

tasks {
    shadowJar{
        relocate("org.incendo.cloud", "me.kryz.commandlib")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "me.kryz.dedsacore.DedsaCore" // Asegúrate de usar tu clase principal.
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21)) // Ajusta según tu versión de Java.
    }
}
