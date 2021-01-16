package com.chao2zhang.explicitparam

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor

@AutoService(CommandLineProcessor::class)
class ExplicitParamCommandLineProcessor : CommandLineProcessor {

    override val pluginId: String = BuildConfig.PLUGIN_ID

    override val pluginOptions: Collection<AbstractCliOption> = emptyList()
}