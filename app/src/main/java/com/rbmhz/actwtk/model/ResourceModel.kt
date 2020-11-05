package com.rbmhz.actwtk.model

import io.reactivex.Observable
import io.reactivex.Single

data class ResourceModel<out T>(
    val data: T? = null,
    val state: State = State.FAILED,
    val message: String? = null
) {
    companion object {
        fun <T> loading(): ResourceModel<T> = ResourceModel(state = State.IN_PROGRESS)

        fun <T> failed(message: String?): ResourceModel<T> =
            ResourceModel(message = message)

        fun <T> success(data: T): ResourceModel<T> =
            ResourceModel(data = data, state = State.SUCCESS)

        /**
         * Convert Single to Resource Observable
         */
        fun <T> convert(single: Single<T>): Observable<ResourceModel<T>> {
            return Observable.create { emitter ->
                emitter.onNext(ResourceModel.loading())
                single.subscribe { data, throwable ->
                    data?.let {
                        emitter.onNext(ResourceModel.success(data))
                    }
                    throwable?.let {
                        emitter.onNext(ResourceModel.failed(it.message))
                    }
                    emitter.onComplete()
                }
            }
        }
    }
}

enum class State {
    IN_PROGRESS,
    SUCCESS,
    FAILED
}

