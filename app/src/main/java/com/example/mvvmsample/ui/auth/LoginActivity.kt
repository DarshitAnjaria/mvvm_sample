package com.example.mvvmsample.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.R
import com.example.mvvmsample.data.db.AppDatabase
import com.example.mvvmsample.data.db.entities.User
import com.example.mvvmsample.data.network.MyApi
import com.example.mvvmsample.data.repositories.UserRepository
import com.example.mvvmsample.databinding.ActivityLoginBinding
import com.example.mvvmsample.ui.home.HomeActivity
import com.example.mvvmsample.util.Hide
import com.example.mvvmsample.util.Show
import com.example.mvvmsample.util.snackbar
import com.example.mvvmsample.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = MyApi()
        val database = AppDatabase(this)
        val repository = UserRepository(api, database)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_login.Show()
    }

    override fun onSuccess(user: User) {
        progress_login.Hide()
    }

    override fun onFailure(e: String) {
        progress_login.Hide()
        coordinator_login.snackbar(e)
    }
}
