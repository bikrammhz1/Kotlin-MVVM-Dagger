package com.rbmhz.actwtk.transformer

import com.rbmhz.actwtk.api.imgur.ResponseModel
import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.User
import io.reactivex.SingleTransformer

fun ResponseTransformer():
        SingleTransformer<ResponseModel<List<GalleryModel>>, List<GalleryModel>> {
    return SingleTransformer { result ->
        result.map {
            when (it.success) {
                true -> it.data
                false -> listOf()
            }
        }
    }
}

fun ResponseTransformer1():
        SingleTransformer<ResponseModel<User>, User> {
    return SingleTransformer { result ->
        result.map {
            it.data
        }
    }
}
fun ResponseObject():
        SingleTransformer<ResponseModel<Object>, Object> {
    return SingleTransformer { result ->
        result.map {
            it.data
        }
    }
}