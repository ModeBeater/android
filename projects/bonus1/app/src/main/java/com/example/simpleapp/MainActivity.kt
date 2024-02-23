package com.example.simpleapp

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.simpleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.simpleapp.databinding.ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Register.setOnClickListener{
            val firstName = binding.FirstName.text.toString()
            val lastName = binding.LastName.text.toString()
            val dateOfBirth = binding.DateOfBirth.text.toString()
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()
            val person = Person(firstName,lastName,dateOfBirth,login,password)
            val intent = Intent(this@MainActivity, UserDetailsActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }
    }
}