package io.eyram.loaderskit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.eyram.loaders.Loader01
import io.eyram.loaders.Loader04
import io.eyram.loaders.Loader09
import io.eyram.loaders.Loader10
import io.eyram.loaders.Loader11
import io.eyram.loaders.Loader12
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
                    contentPadding = PaddingValues(24.dp)
                ) {
                    item {
                        Loader01()
                    }
//
//                    item {
//                        Loader02()
//                    }

                    item{
                        Loader04()
                    }

                    item{
                        Loader09()
                    }

                    item{
                        Loader10()
                    }

                    item {
                        Loader11()
                    }

                    item {
                        Loader12()
                    }
                }
            }
        }
    }
}

