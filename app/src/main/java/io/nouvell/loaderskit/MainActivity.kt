package io.nouvell.loaderskit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.nouvell.loaders.Loader02
import io.nouvell.loaders.Loader07
import io.nouvell.loaderskit.ui.theme.LoadersKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadersKitTheme(darkTheme = true) {
                Scaffold { paddingValues ->
//                    Column(
//                        Modifier
//                            .padding(paddingValues)
//                            .fillMaxSize()
//                            .background(Color.White),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                    ) {
//                        Loader02(
//                            modifier = Modifier.size(150.dp)
//                                .background(Color.Yellow)
//                        )
//                    }

                    LoaderListScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

