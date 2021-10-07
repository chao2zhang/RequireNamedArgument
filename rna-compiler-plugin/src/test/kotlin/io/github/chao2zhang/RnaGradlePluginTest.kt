package io.github.chao2zhang

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RnaGradlePluginTest {

    @Test
    fun failToCompile() {
        val main = SourceFile.kotlin("main.kt", """
            import io.github.chao2zhang.RequireNamedArgument
            
            @RequireNamedArgument
            fun add(a: Int, b: Int): Int = a + b
            
            fun callAdd() {
                add(2, 3)
            }
        """.trimIndent())
        val result = compile(listOf(REQUIRE_NAMED_ARGUMENT, main))
        assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.exitCode)
        assertTrue(result.messages.contains(
            "Argument '2' should have name 'a' because the target function is @RequireNamedArgument."
        ))
        assertTrue(result.messages.contains(
            "Argument '3' should have name 'b' because the target function is @RequireNamedArgument."
        ))
    }

    @Test
    fun succeedToCompile() {
        val main = SourceFile.kotlin("main.kt", """
            import io.github.chao2zhang.RequireNamedArgument
            
            @RequireNamedArgument
            fun add(a: Int, b: Int): Int = a + b
            
            fun callAdd() {
                add(a = 2, b = 3)
            }
        """.trimIndent())
        val result = compile(listOf(REQUIRE_NAMED_ARGUMENT, main))
        assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
    }
}

private fun compile(
    sourceFiles: List<SourceFile>,
): KotlinCompilation.Result {
    val plugins = listOf(RnaComponentRegistrar())
    val compilation = KotlinCompilation().apply {
        sources = sourceFiles
        compilerPlugins = plugins
        inheritClassPath = true
    }
    return compilation.compile()
}

private val REQUIRE_NAMED_ARGUMENT = SourceFile.kotlin("RequireNamedArgument.kt", """
        package io.github.chao2zhang

        @Retention(AnnotationRetention.BINARY)
        @Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
        annotation class RequireNamedArgument
    """.trimIndent()
)
