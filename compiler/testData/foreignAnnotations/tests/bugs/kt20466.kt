// !DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER
// JSR305_GLOBAL_REPORT warn

// FILE: test/package-info.java
@javax.annotation.ParametersAreNonnullByDefault()
package test;

// FILE: test/Java.java
package test;

public class Java {
    public static void foo(String s) {
    }
}

// FILE: bar.kt
package test

object Kotlin {
    fun bar(s: String) = Unit
}

fun nonNullableType(args: Array<String>) {
    val s: String? = ""
    Java.foo(s ?: "")
//    Java.foo(s!!)
}