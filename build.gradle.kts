plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.0.4"
    id("io.micronaut.aot") version "4.0.4"
    id("io.micronaut.openapi") version "4.1.1"
}

version = "0.1"
group = "example.micronaut"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("example.micronaut.Application")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("example.micronaut.*")
    }
    aot {
    // Please review carefully the optimizations enabled below
    // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
    openapi {
        server(file("src/main/resources/modelwithprimitivelist.yml")) {
            apiPackageName = "com.example.openapi.server.api"
            modelPackageName = "com.example.openapi.server.model"
            useReactive = false
        }

        client(file("src/main/resources/modelwithprimitivelist.yml")) {
            apiPackageName = "com.example.openapi.client.api"
            modelPackageName = "com.example.openapi.client.model"
            useReactive = false
        }
    }
}

/* TODO: Comment the code below in to fix the generated classes and to fix the tests
tasks {
    named("generateServerOpenApiModels") {
        doLast {
            val file =
                layout.buildDirectory.file("generated/openapi/generateServerOpenApiModels/src/main/java/com/example/openapi/server/model/BooksContainer.java")
                    .get().asFile
            val content = file.readText()
            val updatedContent = content.replaceFirst(
                """    private List<String> books;
""",
                """    private List<@Pattern(regexp = "[a-zA-Z ]+") @Size(max = 10) String> books;
"""
            )
            file.writeText(updatedContent)
        }
    }

    named("generateServerOpenApiApis") {
        doLast {
            val file = layout.buildDirectory.file("generated/openapi/generateServerOpenApiApis/src/main/java/com/example/openapi/server/api/BooksApi.java").get().asFile
            val content = file.readText()
            val updatedContent = content.replaceFirst(
                "List<String> requestBody",
                "List<@Pattern(regexp = \"[a-zA-Z ]+\") @Size(max = 10) String> requestBody"
            )
            file.writeText(updatedContent)
        }
    }
}
*/
