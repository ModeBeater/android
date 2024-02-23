package com.example.simpleapp
import java.io.Serializable
data class Person (
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val login: String,
    val password: String
): Serializable