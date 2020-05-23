package me.subhrajyoti.lint

class WebViewLintBug {
    fun setJavaScriptEnabled(flag: Boolean) {}

    fun main() {
        val randomObject = WebViewLintBug()
        randomObject.setJavaScriptEnabled(true)
    }
}

