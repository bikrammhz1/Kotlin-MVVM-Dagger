package com.rbmhz.actwtk.repository

import com.rbmhz.actwtk.api.imgur.InterfaceAPI
import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.ResourceModel
import com.rbmhz.actwtk.transformer.ResponseObject
import com.rbmhz.actwtk.transformer.ResponseTransformer
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(
    private val galleryApi: InterfaceAPI
) : InterfaceRepository {

    override fun getGallery(): Observable<ResourceModel<List<GalleryModel>>> {
        return ResourceModel.convert(
            galleryApi.get().compose(ResponseTransformer())
        )
    }

    override fun getGallery2(): Observable<ResourceModel<List<GalleryModel>>> {
        return ResourceModel.convert(
            galleryApi.get().compose(ResponseTransformer())
        )
    }

    override fun getGallery3(): Observable<ResourceModel<List<GalleryModel>>> {
        return ResourceModel.convert(
            galleryApi.getData().compose(ResponseTransformer())
        )
    }

    var url = "http://www.json-generator.com/api/json/get/cqlDhyfkOa?indent=2";
    override fun getGallery4(): Observable<ResourceModel<Object>> {

        return ResourceModel.convert(
            galleryApi.getUsers(url).compose(ResponseObject())
        )
    }
}