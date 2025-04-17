package com.webenia.eleganceoud.presentation.screens.otp

sealed class OtpEvents{
    data class DigitEntered(val digit:String):OtpEvents()
    object SubmitOtp:OtpEvents( )
    object ResendCode:OtpEvents()
    object ResendOtp:OtpEvents()
}

