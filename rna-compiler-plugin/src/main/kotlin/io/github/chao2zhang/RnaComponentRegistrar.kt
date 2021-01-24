package io.github.chao2zhang

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension

@AutoService(ComponentRegistrar::class)
class RnaComponentRegistrar : ComponentRegistrar {

    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        StorageComponentContainerContributor.registerExtension(
            project,
            RnaCallChecker()
        )
    }
}
