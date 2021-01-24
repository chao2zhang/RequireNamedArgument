package io.github.chao2zhang

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.container.StorageComponentContainer
import org.jetbrains.kotlin.container.useInstance
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.platform.TargetPlatform
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall

class RnaCallChecker : CallChecker, StorageComponentContainerContributor {

    override fun registerModuleComponents(
        container: StorageComponentContainer,
        platform: TargetPlatform,
        moduleDescriptor: ModuleDescriptor
    ) {
        container.useInstance(this)
    }

    override fun check(
        resolvedCall: ResolvedCall<*>,
        reportOn: PsiElement,
        context: CallCheckerContext
    ) {
        if (!resolvedCall.resultingDescriptor.annotations.hasAnnotation(ANNOTATION_FQ_NAME)) {
            return
        }
        resolvedCall.valueArgumentsByIndex
        resolvedCall.call.valueArguments.forEachIndexed { index, arg ->
            if (!arg.isNamed()) {
                // Fallback to raw index if the parameter is missing at the given index
                val parameterName = resolvedCall
                    .resultingDescriptor
                    .valueParameters
                    .getOrNull(index)
                    ?.name
                    ?.asString()
                    ?: index.toString()
                context.trace.report(RnaErrors.INVOCATION_OF_REQUIRE_NAMED_ARGUMENT_FUNCTION.on(
                    arg.asElement(),
                    arg.asElement(),
                    parameterName
                ))
            }
        }
    }

}

private val ANNOTATION_FQ_NAME = FqName(RequireNamedArgument::class.qualifiedName!!)
