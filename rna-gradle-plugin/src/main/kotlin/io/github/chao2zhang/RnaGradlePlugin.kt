package io.github.chao2zhang

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

class RnaGradlePlugin : KotlinCompilerPluginSupportPlugin {

    override fun apply(target: Project) {
    }

    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        kotlinCompilation.target.project.dependencies.add(
            "compileOnly",
            "${BuildConfig.PLUGIN_GROUP}:rna-compiler-plugin:${BuildConfig.PLUGIN_VERSION}"
        )
        return kotlinCompilation.target.project.provider { emptyList() }
    }

    override fun getCompilerPluginId(): String = BuildConfig.PLUGIN_ID

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
        groupId = BuildConfig.PLUGIN_GROUP,
        artifactId = BuildConfig.PLUGIN_NAME,
        version = BuildConfig.PLUGIN_VERSION
    )

    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true
}