package com.game.retrofitcrashcoursekotlin.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.game.retrofitcrashcoursekotlin.R
import com.game.retrofitcrashcoursekotlin.databinding.ActivityLoginBinding
import com.game.retrofitcrashcoursekotlin.location.LocationActivity
import com.game.retrofitcrashcoursekotlin.models.UserRequest
import com.game.retrofitcrashcoursekotlin.utils.NetworkResult
import com.game.retrofitcrashcoursekotlin.utils.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()

        bindObservers()
    }

    private fun bindObservers() {
        loginViewModel.userResponseLiveData.observe(this) {
            when (it) {
                is NetworkResult.Error -> binding.txtError.text = it.message
                is NetworkResult.Loading -> binding.txtError.text = "Loading"
                is NetworkResult.Success -> {
                    preferenceManager.saveToken(it.data!!.token)
                    preferenceManager.saveUserName(it.data.username)
                    preferenceManager.setLogin(it.data)
                    Toast.makeText(this, "Login success token saved..!!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, LocationActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnLogin.setOnClickListener{
                val userRequest : UserRequest = getUserRequest()
                Log.e("userRequest_log"," = "+userRequest)
                loginViewModel.loginUser(userRequest)
            }
        }
    }

    private fun getUserRequest(): UserRequest {
        binding.apply {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            return UserRequest(email,"912E79CD13C64069D91DA65D62FBB78C","3.0.0.2")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.login_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.setting -> {
                Toast.makeText(this,"click on setting",Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.debug -> {
                Toast.makeText(this,"click on debug",Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.app_settings -> {
                Toast.makeText(this,"click on app setting",Toast.LENGTH_SHORT).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}