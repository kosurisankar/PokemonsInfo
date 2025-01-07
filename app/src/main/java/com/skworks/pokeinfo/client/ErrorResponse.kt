package com.skworks.pokeinfo.client

class ErrorResponse(val code: Int, message: String) : Throwable("($code) $message")
