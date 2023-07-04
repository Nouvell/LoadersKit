package io.nouvell.loaderskit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import io.nouvell.loaderskit.ui.theme.LoadersKitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadersKitTheme(darkTheme = true) {
                Surface {
                    //  Loader18(Modifier.padding(32.dp))
                    LoaderListScreen()
                   // Loader17()

                    //Loader13()
                }
            }
        }
    }
}

