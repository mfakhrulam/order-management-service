package com.batch14.orderservice.domain.dto.response

data class ResGetUserDto(
    val id: Int,
    val email: String,
    val username: String,
    var roleId: Int? = null,
    var roleName: String? = null
)
