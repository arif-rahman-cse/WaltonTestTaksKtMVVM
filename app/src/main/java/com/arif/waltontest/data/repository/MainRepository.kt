package com.arif.waltontest.data.repository

import com.arif.waltontest.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

//    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getProduct() = apiHelper.getProduct()
}