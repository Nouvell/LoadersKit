package io.eyram.loaderskit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.eyram.loaders.CircularLoadingAnimation
import io.eyram.loaders.Loader02
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaderskit.ui.theme.LoadersKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadersKitTheme {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                ) {
                    item {
                        CircularLoadingAnimation()
                    }

                    item {
                        Loader02(
                            color = LoaderColor.Black,
                            modifier = Modifier.size(300.dp)
                        )
                    }
                }
            }
        }
    }
}

