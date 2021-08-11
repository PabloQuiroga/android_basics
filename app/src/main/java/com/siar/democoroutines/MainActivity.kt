package com.siar.democoroutines

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.siar.democoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var success: Boolean = false
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.loginResult.observe(this, Observer { success ->
            toast(if (success) "Success" else "Failure")
        })

        /*
        // En este activity
        // GlobalScope es para toda la aplicacion y si cambiamos de activity,
        // cuando se quiera actualizar algo del activity que la lanzo, va a lanzar
        // una exception.
        // con async y awwait podemos lanzar tantas coroutines como querramos

        binding.btnLogin.setOnClickListener {
            lifecycleScope.launch { // en este contexto
                success = withContext(Dispatchers.IO) { //en otro contexto
                    validateLogin()
                }
                toast(if (success) "Success" else "Failure")
            }
        }*/
        // en un viewModel
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClick(binding.edEmail.text.toString(), binding.edPassword.text.toString())
        }
    }

    private fun validateLogin(): Boolean {
        val user = binding.edEmail.text.toString()
        val pass = binding.edPassword.text.toString()

        Thread.sleep(5000) // Mock para coroutines

        return user.isNotEmpty() && pass.isNotEmpty()
    }

}

// funcion de extension para Toast
private fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}