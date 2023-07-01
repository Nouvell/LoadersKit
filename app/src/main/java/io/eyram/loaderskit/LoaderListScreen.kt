package io.eyram.loaderskit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.eyram.loaders.Loader01
import io.eyram.loaders.Loader04
import io.eyram.loaders.Loader06
import io.eyram.loaders.Loader08
import io.eyram.loaders.Loader09
import io.eyram.loaders.Loader10
import io.eyram.loaders.Loader11
import io.eyram.loaders.Loader12
import io.eyram.loaders.Loader14
import io.eyram.loaders.Loader15
import io.eyram.loaders.Loader17
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaderskit.ui.theme.IBMPlexFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoaderListScreen(modifier: Modifier = Modifier) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Loaders Kit",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(24.dp),
        ) {
            itemsIndexed(loaderPreviewList) { index, item ->
                val id = index + 1
                PreviewCard(
                    id = buildString {
                        if (id < 10) append("0")
                        append(id)
                    },
                    name = item.name,
                    animation = item.animation
                )

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun PreviewCard(
    id: String,
    name: String,
    animation: @Composable (Modifier, LoaderColor) -> Unit,
    modifier: Modifier = Modifier,
    basis: String = "Loaders Kit 2.0",
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFF292929),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFF333333))
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .paddingFromBaseline(top = 40.dp, bottom = 80.dp),
                text = id,
                color = Color.White.copy(alpha = 0.75F),
                style = MaterialTheme.typography.headlineSmall,
            )

            animation.invoke(
                Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                LoaderColor.White
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .paddingFromBaseline(top = 114.dp, bottom = 108.dp),
                text = name,
                style = MaterialTheme.typography.displaySmall
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawWithContent {
                        val strokeWidth = 2.dp.toPx()
                        val dash = strokeWidth * 4
                        val gap = strokeWidth * 2

                        drawLine(
                            start = Offset.Zero,
                            end = Offset(size.width, 0F),
                            color = Color(0xFF333333),
                            strokeWidth = strokeWidth,
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(dash, gap), 0F
                            )
                        )
                    }
            )

            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .paddingFromBaseline(top = 28.dp, bottom = 24.dp),
                text = buildString {
                    append("Based on ")
                    append(basis)
                },
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.75F)
            )
        }
    }
}


data class Loader(
    val name: String,
    val animation: @Composable (Modifier, LoaderColor) -> Unit,
)


val loaderPreviewList = listOf(
    Loader(
        name = "Scale-dulum",
        animation = { modifier, color -> Loader12(modifier, color) }
    ),
    Loader(
        name = "Atomic",
        animation = { modifier, color -> Loader10(modifier, color) }
    ),
    Loader(
        name = "Bullet Train",
        animation = { modifier, color -> Loader14(modifier, color) }
    ),
    Loader(
        name = "Crop Circles",
        animation = { modifier, color -> Loader09(modifier, color) }
    ),
    Loader(
        name = "Caterpillar",
        animation = { modifier, color -> Loader04(modifier, color) }
    ),
    Loader(
        name = "Twirl",
        animation = { modifier, color -> Loader01(modifier, color) }
    ),
    Loader(
        name = "Burst",
        animation = { modifier, color -> Loader15(modifier, color) }
    ),
    Loader(
        name = "Fidgety",
        animation = { modifier, color -> Loader08(modifier, color) }
    ),
    Loader(
        name = "Rotor",
        animation = { modifier, color -> Loader06(modifier, color) }
    ),
    Loader(
        name = "Bead",
        animation = { modifier, color -> Loader11(modifier, color) }
    ),

    Loader(
        name = "Twin Circles",
        animation = { modifier, color -> Loader17(modifier, color) }
    ),
)