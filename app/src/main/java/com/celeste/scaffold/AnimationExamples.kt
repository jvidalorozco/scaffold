package com.celeste.scaffold


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {
    var showBox by rememberSaveable { mutableStateOf(true) }
    var secondColor by rememberSaveable { mutableStateOf(false) }
    val secondRealColor by animateColorAsState(
        targetValue = if (secondColor)
            Color.Red
        else
            Color.Yellow,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = { showBox = false }
    )
    if (showBox) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(secondRealColor)
                .clickable { secondColor = !secondColor }
        )
    }
}

@Composable
fun SizeAnimation() {
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(
        targetValue = if (smallSize) 50.dp else 100.dp,
        animationSpec = tween(durationMillis = 500),
        finishedListener = {
            if (!smallSize) {
            }
        }
    )
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Cyan)
            .clickable { smallSize = !smallSize }
    )
}

@Composable
fun VisibilityAnimation() {
    var isVisible by rememberSaveable { mutableStateOf(true) }

    Column(
        Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Mostrar/Ocultar")
        }

        Spacer(modifier = Modifier.size(50.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(),
            exit = slideOutHorizontally()
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun CrossFadeExampleAnimation() {
    var myComponentType: ComponentType by rememberSaveable {
        mutableStateOf(ComponentType.Text)
    }
    Column(
        Modifier.fillMaxWidth()
    ) {
        Button(onClick = { myComponentType = getComponentTypeRandom()}) {
            Text(text = "Cambiar componente")
        }
        Crossfade(targetState = myComponentType) {
            when(it) {
                ComponentType.Image -> Icon(Icons.Default.ShoppingCart, contentDescription = "")
                ComponentType.Text -> Text(text = "Soy un componente")
                ComponentType.Box -> Box(modifier = Modifier
                    .size(150.dp)
                    .background(Color.Red))
                ComponentType.Error -> Icon(Icons.Default.ShoppingCart, contentDescription = "")
            }
        }

    }
}

fun getComponentTypeRandom(): ComponentType {
    return when(nextInt(from = 0, until = 3)){
        0 -> ComponentType.Image
        1 -> ComponentType.Text
        2 -> ComponentType.Box
        else -> {
            ComponentType.Error
        }
    }
}

enum class ComponentType() {
    Image, Text, Box, Error
}