package com.webenia.eleganceoud.data.remote.repositroy.auth

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.resend_otp.ResendOtpRequest
import com.webenia.eleganceoud.data.remote.requests.submit_otp.SubmitOtpRequest
import com.webenia.eleganceoud.data.remote.response.auth.otp.ResendOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.SubmitOtpResponse
import com.webenia.eleganceoud.domain.repository.OtpRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OtpRepositoryImpl @Inject constructor(
    val api: WebServices
) : OtpRepository {
    override fun submitOtp(request: SubmitOtpRequest): Flow<Resource<SubmitOtpResponse>> {
        return toResultFlow {
            api.submitOtp(
                body = request
            )
        }.map {
            when (it) {
                is ApiState.Success -> Resource.Success(it.data)
                is ApiState.Error -> Resource.Error(
                    it.message ?: UiText.DynamicString("Try again later")
                )

                is ApiState.Loading -> Resource.Loading()
            }
        }
    }

    override fun resendOtp(request: ResendOtpRequest): Flow<Resource<ResendOtpResponse>> {
        return toResultFlow {
            api.resendOtp(
                body = request
            )
        }.map {
            when (it) {
                is ApiState.Success -> Resource.Success(it.data)
                is ApiState.Error -> Resource.Error(
                    it.message ?: UiText.DynamicString("Try again later")
                )

                is ApiState.Loading -> Resource.Loading()
            }
        }

    }
}