package com.example.radiobuttonsremakelab5.controllers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.radiobuttonsremakelab5.R
import com.example.radiobuttonsremakelab5.models.Radio
import com.example.radiobuttonsremakelab5.views.RadioViewModel

@Composable
fun RadioController(viewModel: RadioViewModel = viewModel()) {
    val selectedRadio = viewModel.selectedRadio.collectAsState().value
    val isPlaying = viewModel.isPlaying.collectAsState().value
    val volume = viewModel.volume.collectAsState().value

    Column {
        val radios = listOf(
            Radio("Minion 1", "https://stream.streamgenial.stream/87eu2988rm0uv", R.drawable.radio1), //bossa nova
            Radio("Minion 2", "http://mbn-channel-03.akacast.akamaistream.net/7/31/233452/v1/ibb.akacast.akamaistream.net/mbn_channel_03.m3u", R.drawable.radio2), //egyptian
            Radio("Minion 3", "https://stream.0nlineradio.com/brazil", R.drawable.radio3), //brazilian
            Radio("Minion 4", "http://perseus.shoutca.st:8020/stream", R.drawable.radio4), //old school
            Radio("Minion 5", "http://99.198.118.250:8383/stream", R.drawable.radio5) //stevie nicks
        )

        radios.forEach { radio ->
            Row {
                Image(painter = painterResource(id = radio.imageResId), contentDescription = null)
                Text(text = radio.name)
                Button(onClick = { viewModel.selectRadio(radio); viewModel.startStopRadio() }) {
                    Text(text = if (isPlaying && selectedRadio == radio) "Pause" else "Sing")
                }
            }
        }
        Row {
            Button(onClick = { viewModel.decreaseVolume() }) {
                Text(text = "quieter")
            }
            Slider(
                value = volume.toFloat(),
                onValueChange = { newVolume -> viewModel.setVolume(newVolume.toInt()) },
                valueRange = 0f..100f
            )
            Button(onClick = { viewModel.increaseVolume() }) {
                Text(text = "louder")
            }
        }
    }
}

