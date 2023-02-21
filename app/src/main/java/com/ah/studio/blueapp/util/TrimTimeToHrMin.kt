package com.ah.studio.blueapp.util

fun trimTimeToHrMin(timeToTrim: String, endIndex: Int = 2): String =
    timeToTrim.substring(0, endIndex)
