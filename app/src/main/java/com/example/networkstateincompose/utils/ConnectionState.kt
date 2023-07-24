package com.example.networkstateincompose.utils

sealed class ConnectionState {
    object Available : ConnectionState()
    object Lost : ConnectionState()
}
