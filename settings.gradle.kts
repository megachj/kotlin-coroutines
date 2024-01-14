pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    val pluginVersions = mapOf(
        "org.jetbrains.kotlin" to "1.8.22",
        "org.jetbrains.kotlin.plugin" to "1.8.22",

        "com.epages" to "0.17.1",
        "org.jlleitschuh.gradle" to "11.0.0",
    )

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace != null) {
                val namespace = requested.id.namespace
                if (pluginVersions.containsKey(namespace)) {
                    println("plugins: $namespace >>> ${pluginVersions[namespace]}")
                    useVersion(pluginVersions[namespace])
                }
            }
        }
    }
}

rootProject.name = "kotlin-coroutines"
