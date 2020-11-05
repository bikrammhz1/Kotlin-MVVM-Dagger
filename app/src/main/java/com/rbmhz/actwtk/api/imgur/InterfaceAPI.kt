package com.rbmhz.actwtk.api.imgur

import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface InterfaceAPI {

    @GET("/3/gallery/{section}/{sort}/{window}/{page}")
    fun get(
        @Path("section") section: String = "hot",
        @Path("sort") sort: String = "viral",
        @Path("page") page: Int = 0,
        @Path("window") window: String = "day",
        @Query("showViral") showViral: Boolean = true
    ): Single<ResponseModel<List<GalleryModel>>>

    @GET("/3/gallery/{section}/{sort}/{window}/{page}")
    fun getData(
        @Path("section") section: String = "hot",
        @Path("sort") sort: String = "viral",
        @Path("page") page: Int = 0,
        @Path("window") window: String = "day",
        @Query("showViral") showViral: Boolean = true
    ): Single<ResponseModel<List<GalleryModel>>>


    @GET
    fun getUsers(@Url url: String?): Single<ResponseModel<Object>>

}

