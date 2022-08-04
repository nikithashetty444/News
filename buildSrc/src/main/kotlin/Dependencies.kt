import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradleVersion}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object AndroidCore {
        const val material = "com.google.android.material:material:${Versions.material}"

        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

        const val kotlinSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
        const val retrofitKotlinSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerialization}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
        const val composePreview = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val composeNavigation =
            "androidx.navigation:navigation-compose:${Versions.composeNaviagtion}"
        const val composeLifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeLifecycle}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
        const val swipeRefresh =
            "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefresh}"
        const val room = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.logging}"
    }

    object Coroutine {
        const val kotlinCoroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
        const val kotlinCoroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    }

    object DI {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Test {
        //test
        const val junit = "junit:junit:${Versions.junit}"
        const val androidxJunit = "androidx.test.ext:junit:${Versions.androidJUnitTest}"
        const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockito}"
        const val nharmaan = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.nharmaan}"
        const val core = "androidx.arch.core:core-testing:${Versions.core}"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    }

    object AnnotationProcessor {
        const val room = "androidx.room:room-compiler:${Versions.room}"
    }

    val appLibs = listOf(
        AndroidCore.androidCoreKtx,
        AndroidCore.appCompat,
        AndroidCore.material,
        AndroidCore.composeUi,
        AndroidCore.composeMaterial,
        AndroidCore.composeActivity,
        AndroidCore.composeNavigation,
        AndroidCore.composeLifecycle,
        AndroidCore.composePreview,
        AndroidCore.kotlinSerialization,
        AndroidCore.retrofitKotlinSerialization,
        AndroidCore.retrofit,
        AndroidCore.coil,
        AndroidCore.swipeRefresh,
        AndroidCore.room,
        AndroidCore.roomKtx,
        AndroidCore.logging,
        DI.koin,
        DI.koinCompose,
        Coroutine.kotlinCoroutineCore,
        Coroutine.kotlinCoroutineAndroid
    )

    val androidLibsTest = listOf(
        Test.androidxJunit

    )

    val testLibs = listOf(
        Test.junit,
        Test.mockito,
        Test.mockitoInline,
        Test.core,
        Test.coroutineTest,
        Test.nharmaan
    )

    val annotationLibs = listOf(
        AnnotationProcessor.room
    )

    fun DependencyHandler.addAppLibs() {
        Dependencies.appLibs.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.addTestLibs() {
        Dependencies.testLibs.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    fun DependencyHandler.addAndroidTestLibs() {
        Dependencies.androidLibsTest.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.addAnnotationProcessor() {
        Dependencies.annotationLibs.forEach { dependency ->
            add("kapt", dependency)
        }
    }
}