package com.arif.waltontest.data.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getProduct() = apiService.getProduct()
}