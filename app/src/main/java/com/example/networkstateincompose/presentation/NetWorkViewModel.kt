package com.example.networkstateincompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkstateincompose.utils.ConnectionState
import com.example.networkstateincompose.utils.NetworkConnectionStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    networkConnectionState: NetworkConnectionStatus
): ViewModel(){
    val networkState: StateFlow<ConnectionState> = networkConnectionState.getNetworkState().stateIn(
        scope = viewModelScope,
        initialValue = ConnectionState.Lost,
        started = SharingStarted.WhileSubscribed(5000L)
    )
}

