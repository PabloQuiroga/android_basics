package com.siar.democoroutines

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.siar.democoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val success = validateLogin()
            toast(if (success) "Success" else "Failure")
        }
    }

    private fun validateLogin(): Boolean {
        val user = binding.edEmail.text.toString()
        val pass = binding.edPassword.text.toString()

        return user.isNotEmpty() && pass.isNotEmpty()
    }


}

// funcion de extension
private fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}