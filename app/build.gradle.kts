plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.cameraswitchpatch"
    compileSdk = 33
    
    defaultConfig {
        applicationId = "com.example.cameraswitchpatch"
        minSdk = 31
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
}
