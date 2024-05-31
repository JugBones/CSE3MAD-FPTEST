plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.fptest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fptest"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:compose-theme-adapter:1.2.1")
    implementation("com.hanks:passcodeview:0.1.2")

    // Firebase BOM
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))

    // Mockito for mocking
    testImplementation ("org.mockito:mockito-core:3.9.0")
    testImplementation ("org.mockito:mockito-inline:3.9.0")

    // Firebase dependencies
    implementation("com.google.firebase:firebase-inappmessaging")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-database")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.google.firebase:firebase-analytics")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("androidx.test:core:1.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")

    implementation ("androidx.multidex:multidex:2.0.1")
    implementation("com.google.zxing:core:3.4.1")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("com.journeyapps:zxing-android-embedded:4.2.0")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}
