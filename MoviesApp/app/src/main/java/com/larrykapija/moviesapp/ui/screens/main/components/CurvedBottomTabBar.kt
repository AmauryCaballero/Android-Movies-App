package com.larrykapija.moviesapp.ui.screens.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.larrykapija.moviesapp.R


@Composable
fun CurvedBottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val icons = listOf(
        R.drawable.ic_home,
        R.drawable.ic_search,
        R.drawable.ic_bookmark
    )

    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Draw the curve
        CurvedBottomBarCanvas(
            modifier = Modifier.matchParentSize(),
            colorScheme = MaterialTheme.colorScheme
        )

        // Place navigation items

        NavigationBar(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
                .fillMaxWidth(),
            containerColor = colorScheme.background.copy(alpha = 0f)
        ) {
            icons.forEachIndexed { index, iconRes ->
                val iconPainter = painterResource(id = iconRes)
                val isSelected = selectedIndex == index
                val iconColor = if (isSelected) colorScheme.primary else colorScheme.tertiary

                NavigationBarItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .drawBehind {
                                    if (isSelected) {
                                        val glowColor = colorScheme.primary.copy(alpha = 0.4f)
                                        val glowColor2 = colorScheme.primary.copy(alpha = 0.05f)
                                        val centerX = size.width / 2
                                        val centerY = size.height / 2

                                        drawCircle(
                                            brush = Brush.radialGradient(
                                                colors = listOf(glowColor,glowColor2, Color.Transparent, Color.Transparent),
                                                center = Offset(centerX, centerY),
                                                radius = size.minDimension / 1.3f,
                                            )
                                        )
                                    }
                                }
                                .graphicsLayer { clip = false },
                        ) {
                            Icon(
                                painter = iconPainter,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(30.dp)
                            )
                        }
                    },
                    selected = isSelected,
                    onClick = { onItemSelected(index) },
                    alwaysShowLabel = false,
                    modifier = Modifier,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorScheme.primary,
                        unselectedIconColor = colorScheme.tertiary,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }





    }
}

@Composable
private fun CurvedBottomBarCanvas(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme = MaterialTheme.colorScheme
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        // Define the curve
        val path = Path().apply {

            moveTo(0f, height)
            lineTo(width, height)
            lineTo(width, 0f)

            val curveDepth = height * 0.6f // Depth of the curve
            val curveStartX = 0f

            // Draw the quadratic bezier curve for the concave top part
            // The control point is now above the top line to create the concavity
            quadraticBezierTo(
                x1 = width / 2, y1 = -curveDepth, // Control point in the center but above for concavity
                x2 = curveStartX, y2 = 0f // End of the curve, which is the start of the rectangle
            )

            close()
        }

        drawPath(
            path = path,
            color = colorScheme.secondary,
        )
    }
}
