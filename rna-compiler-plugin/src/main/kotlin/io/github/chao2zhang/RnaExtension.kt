package io.github.chao2zhang

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.container.ComponentProvider
import org.jetbrains.kotlin.context.ProjectContext
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension

@AutoService(AnalysisHandlerExtension::class)
class RnaExtension(
    private val logger: MessageCollector
) : AnalysisHandlerExtension {

    override fun analysisCompleted(
        project: Project,
        module: ModuleDescriptor,
        bindingTrace: BindingTrace,
        files: Collection<KtFile>)
        : AnalysisResult? {
        val visitor = RnaVisitor(logger, bindingTrace.bindingContext)
        files.forEach { it.accept(visitor) }
        return null
    }
}
