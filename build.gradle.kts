plugins {
    java
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
}

group = "io.taraxacum"
version = "3.0.1"

// 原 Java 17；2026-11-07 随 Slimefun4 2025.1 / Paper 1.21 升到 21。
// 不用 toolchain，避免本机/CI JDK 不一致解析失败。
repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io")
}

dependencies {
    // Core
    compileOnly(libs.paper.api)
    compileOnly(libs.slimefun4)

    // Tools etc.（会被 shade）
    implementation(libs.bstats)
}

tasks.withType<JavaExec>().configureEach {
    systemProperty("file.encoding", "UTF-8")
    systemProperty("sun.stdout.encoding", "UTF-8")
    systemProperty("sun.stderr.encoding", "UTF-8")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release = 21
    }

    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version.toString())
        }
    }

    shadowJar {
        archiveBaseName.set("FinalTECH-Changed")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("")

        // 原 pom 的 shade 配置未开 minimize（bstats 部分初始化走反射，minimize 有误删风险），保持一致。
        relocate("org.bstats", "io.taraxacum.finaltech")

        exclude("META-INF/*")

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        mergeServiceFiles()
    }

    build {
        dependsOn(shadowJar)
    }

    runServer {
        dependsOn(shadowJar)
        val run = file(providers.gradleProperty("server.run.dir").orElse("run"))
        runDirectory.set(run)

        doFirst {
            run.resolve("eula.txt").writeText("eula=true")

            val pl = run.resolve("plugins")
            pl.mkdirs()
            copy {
                from(projectDir.resolve("build/libs")) {
                    include("${name}-${version}.jar")
                }
                into(pl)
            }
        }

        jvmArgs(
            "-Dfile.encoding=UTF-8",
            "-Dsun.jnu.encoding=UTF-8"
        )
        maxHeapSize = "4G"
        minecraftVersion("1.20.1")
    }
}

// Set default tasks
defaultTasks("clean", "build")
