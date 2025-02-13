plugins {
    id("flipper.android-compose")
}

android.namespace = "com.flipperdevices.faphub.installation.button.api"

dependencies {
    implementation(projects.components.faphub.dao.api)

    implementation(projects.components.core.data)

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
}
