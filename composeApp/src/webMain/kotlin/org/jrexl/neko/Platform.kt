package org.jrexl.neko

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform