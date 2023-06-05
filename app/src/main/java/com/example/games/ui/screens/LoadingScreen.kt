package com.example.games.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.games.R

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
Box(    
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
){
    Image(
        painter = painterResource(id = (R.drawable.loading_img)),
        contentDescription = stringResource(id = R.string.loading)
    )
}
}