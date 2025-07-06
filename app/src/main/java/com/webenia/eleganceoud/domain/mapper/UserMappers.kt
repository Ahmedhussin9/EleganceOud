package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.auth.signup.UserDto
import com.webenia.eleganceoud.domain.model.User

// data.mapper.UserMapper.kt
fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        role = role,
        countryId = country_id.toInt(),
        isVerified = is_verified
    )
}
