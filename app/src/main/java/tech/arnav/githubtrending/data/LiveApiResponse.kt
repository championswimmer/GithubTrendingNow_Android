package tech.arnav.githubtrending.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A mutable livedata that wraps [ApiResponse] and handles refreshing the
 * data using Coroutines
 *
 * @param coroutineScope the scope in which the refresh coroutine runs
 * @param refreshFunction the method that contains implementation of actually getting data
 *
 * @author championswimmer
 */
class LiveApiResponse<T>(
    private val coroutineScope: CoroutineScope,
    private val refreshFunction: suspend () -> T
) : MutableLiveData<ApiResponse<T>>(ApiResponse()) {

    fun refresh() {
        // Do not pile up reloads
        if (value?.status == ApiResponse.Status.LOADING) return

        if (value == null) value = ApiResponse()

        postValue(value?.apply { status = ApiResponse.Status.LOADING })

        coroutineScope.launch {
            try {

                val newData = refreshFunction()
                postValue(value?.apply { set(ApiResponse.Status.SUCCESS, newData) })

            } catch (e: Exception) {

                postValue(value?.apply { set(ApiResponse.Status.FAILURE, null, e.message) })
            }
        }
    }
}