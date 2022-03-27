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

  @Test
  fun allowsUnnamedTrailingLambda() {
    val main = SourceFile.kotlin("main.kt", """
            import io.github.chao2zhang.RequireNamedArgument

            @RequireNamedArgument
            fun functionWithLambda(a: () -> Unit) = a()

            @RequireNamedArgument
            fun functionWith2Lambdas(a: () -> Unit, b: () -> Unit) = a()

            @RequireNamedArgument
            fun functionWithLambdaAndOtherParameter(a: Int, b: () -> Unit) = b()

            fun call() {
                functionWithLambda { 1 }
                functionWithLambda(a = { 1 })
                functionWith2Lambdas(a = { 1 }) { 2 }
                functionWith2Lambdas(a = { 1 }, b = { 2 })
                functionWithLambdaAndOtherParameter(a = 1) { 1 }
                functionWithLambdaAndOtherParameter(a = 1, b = { 1 })
            }
        """.trimIndent())
    val result = compile(listOf(REQUIRE_NAMED_ARGUMENT, main))
    assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
  }

  @Test
  fun doesNotAllowUnnamedNonTrailingLambda() {
    val main = SourceFile.kotlin("main.kt", """
            import io.github.chao2zhang.RequireNamedArgument

            @RequireNamedArgument
            fun functionWithLambda(a: () -> Unit) = a()

            @RequireNamedArgument
            fun functionWith2Lambdas(a: () -> Unit, b: () -> Unit) = a()

            @RequireNamedArgument
            fun functionWithLambdaAndOtherParameter(a: Int, b: () -> Unit) = b()

            fun call() {
                functionWithLambda({ 1 })
                functionWith2Lambdas({ 1 }, { 2 })
                functionWith2Lambdas(a = { 1 }, { 2 })
                functionWith2Lambdas({ 1 }, b = { 2 })
                functionWithLambdaAndOtherParameter(a = 1, { 1 })
            }
        """.trimIndent())
    val result = compile(listOf(REQUIRE_NAMED_ARGUMENT, main))
    assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.exitCode)
    assertTrue(result.messages.contains(
      "(13, 24): Argument '{ 1 }' should have name 'a' because the target function is @RequireNamedArgument."
    ))
    assertTrue(result.messages.contains(
      "(14, 26): Argument '{ 1 }' should have name 'a' because the target function is @RequireNamedArgument."
    ))
    assertTrue(result.messages.contains(
      "(14, 33): Argument '{ 2 }' should have name 'b' because the target function is @RequireNamedArgument."
    ))
    assertTrue(result.messages.contains(
      "(15, 37): Argument '{ 2 }' should have name 'b' because the target function is @RequireNamedArgument."
    ))
    assertTrue(result.messages.contains(
      "(16, 26): Argument '{ 1 }' should have name 'a' because the target function is @RequireNamedArgument."
    ))
    assertTrue(result.messages.contains(
      "(17, 48): Argument '{ 1 }' should have name 'b' because the target function is @RequireNamedArgument."
    ))
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
