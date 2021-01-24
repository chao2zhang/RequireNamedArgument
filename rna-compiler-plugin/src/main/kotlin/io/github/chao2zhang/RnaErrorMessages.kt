package io.github.chao2zhang

import io.github.chao2zhang.RnaErrors.INVOCATION_OF_REQUIRE_NAMED_ARGUMENT_FUNCTION
import org.jetbrains.kotlin.diagnostics.rendering.DefaultErrorMessages
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.rendering.Renderers

class RnaErrorMessages : DefaultErrorMessages.Extension {

    private val rendererMap = DiagnosticFactoryToRendererMap("RequireNameArgument").apply {
        put(
            INVOCATION_OF_REQUIRE_NAMED_ARGUMENT_FUNCTION,
            "Argument ''{0}'' should have name ''{1}'' " +
                    "because the target function is @RequireNamedArgument.",
            Renderers.ELEMENT_TEXT,
            Renderers.STRING
        )
    }

    override fun getMap() = rendererMap
}
