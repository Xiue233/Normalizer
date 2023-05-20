package io.github.xiue233.normalizer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.xiue233.normalizer.ui.theme.getColorScheme

@Preview
@Composable
fun MessageTopBarPreview() {
    MessageTopBar(text = "MatePad正在发送消息...")
}

@Composable
fun MessageTopBar(
    text: String,
    elevation: Dp = 2.dp,
    backgroundColor: Color = getColorScheme().primary,
    contentColor: Color = contentColorFor(backgroundColor),
    icon: ImageVector = Icons.Filled.Info
) {
    TopAppBar(
        elevation = elevation,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 0.dp)
                .requiredHeight(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Top bar icon",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = TextStyle(color = Color.White, fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}