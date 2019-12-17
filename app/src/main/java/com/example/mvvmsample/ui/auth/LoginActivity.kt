package com.example.mvvmsample.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsample.R
import com.example.mvvmsample.data.db.entities.User
import com.example.mvvmsample.databinding.ActivityLoginBinding
import com.example.mvvmsample.util.Hide
import com.example.mvvmsample.util.Show
import com.example.mvvmsample.util.snackbar
import com.example.mvvmsample.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
    }

    override fun onStarted() {
        progress_login.Show()
    }

    override fun onSuccess(user: User) {
        progress_login.Hide()
        coordinator_login.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(e: String) {
        progress_login.Hide()
        coordinator_login.snackbar(e)
    }
}
