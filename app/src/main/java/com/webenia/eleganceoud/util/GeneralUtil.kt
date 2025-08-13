
package com.elegance_oud.util

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
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
@Composable
fun EnsureNotificationPermissionIfNeeded(
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            onGranted()
        } else {
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { granted ->
                if (granted) onGranted() else onDenied()
            }

            // Call this launcher when user taps "Enable notifications"
            LaunchedEffect(Unit) {
                launcher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    } else {
        // Below Android 13 → no runtime permission
        onGranted()
    }
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}

 const val BASE_IMAGE_URL = "https://backend.webenia.org/public/storage/"
// -------------------------------------------------------------- //