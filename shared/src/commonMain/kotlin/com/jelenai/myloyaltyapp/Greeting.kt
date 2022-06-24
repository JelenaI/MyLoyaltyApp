package com.jelenai.myloyaltyapp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}