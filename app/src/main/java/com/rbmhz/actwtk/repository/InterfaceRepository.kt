package com.rbmhz.actwtk.repository

import com.rbmhz.actwtk.model.GalleryModel
import com.rbmhz.actwtk.model.ResourceModel
import io.reactivex.Observable

interface InterfaceRepository {

    fun getGallery(): Observable<ResourceModel<List<GalleryModel>>>
    fun getGallery2(): Observable<ResourceModel<List<GalleryModel>>>
    fun getGallery3(): Observable<ResourceModel<List<GalleryModel>>>
    fun getGallery4(): Observable<ResourceModel<Object>>
}