package io.github.chao2zhang

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory2
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.diagnostics.Severity

object RnaErrors {

    @JvmField
    val INVOCATION_OF_REQUIRE_NAMED_ARGUMENT_FUNCTION =
        DiagnosticFactory2.create<PsiElement, PsiElement, String>(
            Severity.ERROR
        )

    init {
        Errors.Initializer.initializeFactoryNamesAndDefaultErrorMessages(
            RnaErrors::class.java,
            RnaErrorMessages()
        )
    }
}
