package com.example.simpleapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleapp.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val person = intent.getSerializableExtra("person") as Person
        binding.tvFirstName.text = "First Name: ${person.firstName}"
        binding.tvLastName.text = "Last Name: ${person.lastName}"
        binding.tvDateOfBirth.text = "Date of birth: ${person.dateOfBirth}"
        binding.tvLogin.text = "Login: ${person.login}"
        binding.tvPassword.text = "Password: ${person.password}"
    }
}