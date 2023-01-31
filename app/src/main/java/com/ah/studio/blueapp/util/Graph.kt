package com.ah.studio.blueapp.util

object Graph {
    const val ROOT = "root_graph"
    const val Main = "main_graph"
    const val HomeGraph = "home_graph"
    const val MyParkingGraph = "MyParking_graph"
    const val SeafarerGraph = "Seafarer_graph"
    const val AccountGraph = "Account_graph"

    fun withArgs(vararg args: String): String {
        return buildString {
            append(HomeGraph)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}