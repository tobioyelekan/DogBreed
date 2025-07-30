//package com.tobioyelekan.dogbreed.core.network.adapter
//
//import okhttp3.Request
//import okio.Timeout
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import timber.log.Timber
//import java.net.UnknownHostException
//
//internal class ApiResultCall<T>(
//    private val callDelegate: Call<T>,
//) : Call<ApiResult<T>> {
//
//    override fun enqueue(callback: Callback<ApiResult<T>>) = callDelegate.enqueue(
//        object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                val body = response.body()
//                if (response.isSuccessful) {
//                    if (body != null) {
//                        callback.onResponse(
//                            this@ApiResultCall,
//                            Response.success(ApiResult.Success(body))
//                        )
//                    } else {
//                        Timber.e(response.code().toString(), response.message())
//                        callback.onResponse(
//                            this@ApiResultCall,
//                            Response.success(ApiResult.Error(response.code(), response.message()))
//                        )
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<T>, throwable: Throwable) {
//                Timber.e(throwable)
//                val message = if (throwable is UnknownHostException) {
//                    "It appears your network connectivity is down. Check your connection and try again"
//                } else {
//                    "Something went wrong"
//                }
//                callback.onResponse(
//                    this@ApiResultCall, Response.success(
//                        ApiResult.Exception(
//                            Throwable(cause = throwable, message = message)
//                        )
//                    )
//                )
//            }
//        }
//    )
//
//    override fun clone(): Call<ApiResult<T>> = ApiResultCall(callDelegate.clone())
//
//    override fun execute(): Response<ApiResult<T>> =
//        throw UnsupportedOperationException("ResponseCall does not support execute.")
//
//    override fun isExecuted(): Boolean = callDelegate.isExecuted
//
//    override fun cancel() = callDelegate.cancel()
//
//    override fun isCanceled(): Boolean = callDelegate.isCanceled
//
//    override fun request(): Request = callDelegate.request()
//
//    override fun timeout(): Timeout = callDelegate.timeout()
//}
