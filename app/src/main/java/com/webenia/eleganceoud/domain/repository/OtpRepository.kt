package com.webenia.eleganceoud.domain.repository

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.resend_otp.ResendOtpRequest
import com.webenia.eleganceoud.data.remote.requests.submit_otp.SubmitOtpRequest
import com.webenia.eleganceoud.data.remote.response.auth.otp.ResendOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.SubmitOtpResponse
import kotlinx.coroutines.flow.Flow

interface OtpRepository {
    fun submitOtp(request: SubmitOtpRequest): Flow<Resource<SubmitOtpResponse>>
    fun resendOtp(request: ResendOtpRequest): Flow<Resource<ResendOtpResponse>>
}