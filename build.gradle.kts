import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Dependencies.Ktlint.PLUGIN_NAME) version Dependencies.Versions.Ktlint.GRADLE
    id(Dependencies.Ktlint.GIT_HOOK)
}

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(Dependencies.GRADLE)
        classpath(Dependencies.Navigation.CLASSPATH)
        classpath(Dependencies.Hilt.CLASSPATH)
        classpath(kotlin("gradle-plugin", version = Dependencies.Versions.Kotlin.CORE))
        classpath(Dependencies.Ktlint.CLASSPATH)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://jitpack.io")
        }
        maven("https://jitpack.io")
    }
}

subprojects {
    apply(plugin = Dependencies.Ktlint.PLUGIN_NAME)
    ktlint {
        android.set(true)
        outputColorName.set("RED")
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

val preCommitHook by tasks.named("installGitHooks")

tasks.getByPath(":app:preBuild").apply {
    dependsOn += preCommitHook
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.apply {
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.time.ExperimentalTime",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview"
        )
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}