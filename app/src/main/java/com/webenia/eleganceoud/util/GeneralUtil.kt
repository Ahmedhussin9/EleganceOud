package com.elegance_oud.util

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.Html
import android.util.Log

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.webenia.eleganceoud.util.state.ApiState
import com.google.gson.Gson
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.util.ResultModel
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.text.NumberFormat
import java.text.ParseException
import java.util.Locale




fun <T> toResultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {
    emit(ApiState.Loading())
    try {
        val response = call()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(ApiState.Success(it))
                Log.e("networkResponse", "Success\n${it.toString()}")
            } ?: run {
                emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
                Log.e("networkResponse", "Failure\n Response body is null")
            }
        } else {
            val errorBody = response.errorBody()?.string()
            val errorObject = Gson().fromJson(errorBody, ResultModel::class.java)
            emit(ApiState.Error(UiText.DynamicString(errorObject.message)))
            Log.e("networkResponse", "Failure\n$errorBody")
        }

    } catch (e: HttpException) {
        Log.e("networkResponse", "HttpException\n ${e.message.toString()}")
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))

    } catch (e: IOException) {
        Log.e("networkResponse", "IOException\n ${e.message.toString()}")
        emit(ApiState.Error(UiText.StringResource(R.string.check_your_internet_connection)))

    } catch (e: Exception) {
        Log.e("networkResponse", "Exception\n ${e.message.toString()}")
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}
// -------------------------------------------------------------- //