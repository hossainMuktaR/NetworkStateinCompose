package com.example.networkstateincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.networkstateincompose.presentation.NetworkStatusScreen
import com.example.networkstateincompose.presentation.NetworkViewModel
import com.example.networkstateincompose.ui.theme.NetworkStateInComposeTheme
import com.example.networkstateincompose.utils.ConnectionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkStateInComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val networkState by hiltViewModel<NetworkViewModel>().networkState.collectAsStateWithLifecycle()
                    var networkStateText by remember { mutableStateOf("") }
                    networkStateText = when(networkState) {
                        is ConnectionState.Available -> "Available"
                        is ConnectionState.Lost -> "Lost"
                    }

                    NetworkStatusScreen(networkStateText)
                }
            }
        }
    }
}