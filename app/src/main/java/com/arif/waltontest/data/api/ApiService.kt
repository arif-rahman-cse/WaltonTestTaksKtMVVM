package com.arif.waltontest.data.api

import com.arif.waltontest.data.model.RpProduct
import retrofit2.http.GET


interface ApiService {

    @GET("UserFeedback/ProductList")
    suspend fun getProduct(): List<RpProduct>
}