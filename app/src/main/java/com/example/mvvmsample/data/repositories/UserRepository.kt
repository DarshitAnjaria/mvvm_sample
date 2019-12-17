package com.example.mvvmsample.data.repositories

import com.example.mvvmsample.data.network.MyApi
import com.example.mvvmsample.data.network.responses.AuthResponse
import com.example.mvvmsample.data.network.responses.SafeApiRequest

class UserRepository : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { MyApi().userLogin(email, password) }
    }
}