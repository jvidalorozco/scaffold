package com.celeste.scaffold.model

sealed class Routes(val route: String) {
    object Screen1: Routes(route = "screen1")
    object Screen2: Routes(route = "screen2")
    object Screen3: Routes(route = "screen3")
    object Screen4: Routes(route = "screen4/{age}") {
        fun createRoute(age: Int) = "screen4/$age"
    }
    object Screen5: Routes(route = "screen5?name={name}") {
        fun createRoute(name: String) = "screen5?name=$name"
    }
}