package com.chao2zhang.explicitparam

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension

@AutoService(ComponentRegistrar::class)
class ExplicitParamComponentRegistrar : ComponentRegistrar {

    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        AnalysisHandlerExtension.registerExtension(
            project,
            ExplicitParamExtension(configuration.get(CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY, MessageCollector.NONE))
        )
    }
}