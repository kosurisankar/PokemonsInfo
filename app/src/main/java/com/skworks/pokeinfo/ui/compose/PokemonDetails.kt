package com.skworks.pokeinfo.ui.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.skworks.pokeinfo.R
import com.skworks.pokeinfo.model.NamedApiResource
import com.skworks.pokeinfo.model.Pokemon
import com.skworks.pokeinfo.model.PokemonAbility
import com.skworks.pokeinfo.model.PokemonSpecies
import com.skworks.pokeinfo.model.PokemonStat
import com.skworks.pokeinfo.util.ChangeColor
import com.skworks.pokeinfo.util.convertDecimeters
import com.skworks.pokeinfo.util.convertHectograms
import com.skworks.pokeinfo.util.roundToTwoDecimals
import com.skworks.pokeinfo.viewmodel.GetPokemonDetailsViewModel
import com.skworks.pokeinfo.viewmodelfactory.GetPokemonDetailsViewModelFactory
import okhttp3.internal.wait

@Composable
fun OnPokemonNameClick(pokeId: Int, colorCode: Color) {
    val viewModel: GetPokemonDetailsViewModel =
        viewModel(factory = GetPokemonDetailsViewModelFactory(pokeId))
    val pokemonDetails: Pokemon? = viewModel.pokemoDetails.observeAsState(null).value
    val pokemonSpecies: PokemonSpecies? = viewModel.pokemoSpecies.observeAsState(null).value
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    if (pokemonDetails != null && pokemonSpecies != null) {
        val pokemonGenera = pokemonSpecies.genera.filter { it.language.name == "en" }
        val pokemonDescription =
            pokemonSpecies.flavorTextEntries.filter { it.language.name == "en" }.random().flavorText

        Column(
            modifier = Modifier
                .fillMaxSize()// Optional padding for the whole column
                .background(ChangeColor().getDarkerColor(colorCode))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(top = 42.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                colors = CardDefaults.cardColors(containerColor = colorCode),
            ) {
                TitleCardWithImage(
                    pokemonDetails,
                    pokemonGenera.first().genus,
                    ChangeColor().lightenColor(colorCode)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()) // Add vertical scrolling
                    .padding(10.dp)
            ) {
                PokemonSpeciesCard(pokemonDetails, pokemonDescription, colorCode)
                PokemonAbilityCard(pokemonDetails.abilities, colorCode)
                PokemonBaseStatsCard(pokemonDetails.stats, colorCode)
            }
        }
    }
}


@Composable
fun PokemonSpeciesCard(details: Pokemon, pokemonDesc: String, color: Color) {
    val darkColor = ChangeColor().getDarkerColor(color, 0.4f)
    val pokeHeightInFeet = convertDecimeters(details.height.toDouble()).second
    val pokeHeightInMeters = convertDecimeters(details.height.toDouble()).first
    val pokeWeightInPounds = convertHectograms(details.weight.toDouble()).second
    val pokeWeightInKgs = convertHectograms(details.weight.toDouble()).first
    val pokeCry = details.cries.latest
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        LargeText(
            text = "Species",
            modifier = Modifier.padding(8.dp),
            color = darkColor,
            fontWeight = FontWeight.Bold
        )
    }

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .border(
                        width = 0.2.dp,
                        color = darkColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(ChangeColor().lightenColor(color), RoundedCornerShape(8.dp))
                    .padding(2.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = pokemonDesc.replace("\n", " "),
                    textAlign = TextAlign.Center,
                    color = darkColor,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.species_desc_hint),
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.2.dp,
                                color = darkColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(2.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        MediumText(
                            text = "${
                                roundToTwoDecimals(pokeHeightInFeet).toString().replace(".", "'")
                            }\" ( $pokeHeightInMeters m)",
                            modifier = Modifier.padding(1.dp),
                            color = ChangeColor().getDarkerColor(color, 0.5f)
                        )
                    }
                    SmallText(text = "Height")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 0.2.dp,
                                color = darkColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(2.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        MediumText(
                            text = "${roundToTwoDecimals(pokeWeightInPounds)} lbs (${
                                roundToTwoDecimals(
                                    pokeWeightInKgs
                                )
                            } Kg)",
                            modifier = Modifier.padding(1.dp),
                            color = ChangeColor().getDarkerColor(color, 0.5f)
                        )
                    }
                    SmallText(text = "Weight")
                }
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .border(
                            width = 0.2.dp,
                            color = darkColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(2.dp)
                        .width(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    MusicButtonWithUrl(pokeCry, darkColor)
                }
                SmallText(text = "Cry")
            }
        }
    }
}

@Composable
fun PokemonAbilityCard(ability: List<PokemonAbility>, color: Color) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeText(
            text = "Abilities",
            modifier = Modifier.padding(8.dp),
            color = ChangeColor().getDarkerColor(color, 0.4f),
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier =
            if (ability.size >= 3) {
                Modifier.height(150.dp)
            } else {
                Modifier.height(110.dp)
            }
//                .wrapContentHeight()
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Spacing between items
            ) {
                if (ability.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            MediumText(
                                text = "No Abilityes found",
                                color = ChangeColor().getDarkerColor(color, 0.5f)
                            )
                        }
                    }
                } else {
                    items(ability) { eachAbility ->
                        Card(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = ChangeColor().getDarkerColor(color)
                            )
                        ) {
                            if (eachAbility.isHidden) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp) // Set a fixed height for the card (optional)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .border(
                                                width = 0.5.dp,
                                                color = ChangeColor().getDarkerColor(color, 0.5f),
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .wrapContentHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        SmallText(
                                            text = "Hidden",
                                            modifier = Modifier.padding(1.dp),
                                            color = ChangeColor().getDarkerColor(color, 0.5f)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(), // Set a fixed height for the card (optional)
                                        contentAlignment = Alignment.Center // Center content in the Box
                                    ) {
                                        MediumText(
                                            text = eachAbility.ability.name.replaceFirstChar { it.uppercase() },
                                            color = ChangeColor().getDarkerColor(color, 0.5f)
                                        )
                                    }
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp), // Set a fixed height for the card (optional)
                                    contentAlignment = Alignment.Center // Center content in the Box
                                ) {
                                    MediumText(
                                        text = eachAbility.ability.name.replaceFirstChar { it.uppercase() },
                                        color = ChangeColor().getDarkerColor(color, 0.5f)
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonBaseStatsCard(stats: List<PokemonStat>, color: Color) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LargeText(
            text = "Base Stats",
            modifier = Modifier.padding(8.dp),
            color = ChangeColor().getDarkerColor(color, 0.4f),
            fontWeight = FontWeight.Bold
        )
    }
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BarGraph(
                data = stats.map { it.baseStat.toFloat() },
                names = stats.map { it ->
                    it.stat.name.replace("special", "spl").replaceFirstChar { it.uppercase() }
                },
                color = color
            )
        }
    }
}

@Composable
fun BarGraph(
    data: List<Float>,
    names: List<String>,
    color: Color,
    barColors: List<Color> = List(data.size) { ChangeColor().getDarkerColor(color) },
    modifier: Modifier = Modifier,
    maxBarHeight: Dp = 200.dp,
) {
    val maxValue = data.maxOrNull() ?: 1f // Avoid divide-by-zero
    val density = LocalDensity.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(maxBarHeight + 60.dp), // Extra height for names
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEachIndexed { index, value ->
            val barHeight = with(density) { (value / maxValue * maxBarHeight.toPx()).toDp() }
            val animatedHeight = animateDpAsState(targetValue = barHeight, label = "bar")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxHeight()
            ) {
                // Display value above the bar
                Text(
                    text = value.toInt().toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = ChangeColor().getDarkerColor(color, 0.4f)
                )

                // Draw the bar
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(animatedHeight.value)
                        .background(
                            color = barColors[index],
                            shape = RoundedCornerShape(6.dp)
                        )
                )

                // Display name below the bar
                Text(
                    text = names.getOrElse(index) { "" },
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 6.dp, bottom = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    color = ChangeColor().getDarkerColor(color, 0.4f)
                )
            }
        }
    }
}


@Composable
fun MusicButtonWithUrl(audioUrl: String, color: Color) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var isCompleted by remember { mutableStateOf(false) }

    // Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(audioUrl)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    // Listen for playback state changes
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_ENDED -> {
                        isPlaying = false
                        isCompleted = true
                        isPaused = false
                    }
                }
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    // Play/Pause Logic
    IconButton(
        onClick = {
            if (isPlaying) {
                exoPlayer.stop()
                isPlaying = false
                isPaused = true
            } else {
                if (isCompleted || !isPaused) {
                    exoPlayer.seekTo(0) // Restart playback if completed
                    isCompleted = false
                }
                exoPlayer.play()
                isPlaying = true
                isPaused = false
            }
        },
        modifier = Modifier.size(28.dp)
    ) {
        Icon(
            painter = painterResource(id = if (isPlaying) R.drawable.baseline_stop_24 else android.R.drawable.ic_media_play),
            contentDescription = "Play or Pause",
            tint = color
        )
    }
}

@Composable
fun TitleCardWithImage(details: Pokemon, genera: String, color: Color) {
    var showPopup by remember { mutableStateOf(false) }
    val pokeImage = details.sprites.other?.dream_world?.front_default
        ?: details.sprites.other?.home?.front_default
    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {

        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(2F, false)
                .background(color, RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                LargeText(
                    text = details.name.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(3f)
                        .padding(8.dp),
                    color = ChangeColor().getDarkerColor(color, 0.5f)
                )
                MediumText(
                    text = "#" + details.id.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    color = ChangeColor().getDarkerColor(color, 0.5f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                MediumText(
                    text = genera,
                    modifier = Modifier
                        .padding(8.dp),
                    color = ChangeColor().getDarkerColor(color, 0.5f)
                )
            }
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                details.types.forEach { pokemonType ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = ChangeColor().getDarkerColor(color, 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(2.dp)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        SmallText(
                            text = pokemonType.type.name.uppercase(),
                            modifier = Modifier.padding(1.dp),
                            color = ChangeColor().getDarkerColor(color, 0.5f)
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                }

            }
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1F, true)
                .clickable { showPopup = true },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokeImage)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "pokemon image",
                modifier = Modifier
                    .weight(1F)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = color)
                    .padding(8.dp), alignment = Alignment.Center
            )
        }
        if (showPopup) {
            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            var rotationState by remember { mutableFloatStateOf(0f) }

            AlertDialog(
                onDismissRequest = { showPopup = false },
                title = {
                    LargeText(
                        text = details.name.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, zoom, rotation ->
                                    scale *= zoom
                                    offset = Offset(
                                        x = offset.x + pan.x,
                                        y = offset.y + pan.y
                                    )
                                    rotationState += rotation
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokeImage)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                            contentDescription = "pokemon image",
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    scaleX = maxOf(1f, scale),
                                    scaleY = maxOf(1f, scale),
                                    translationX = offset.x,
                                    translationY = offset.y,
                                    rotationZ = rotationState
                                ),
                            alignment = Alignment.Center
                        )
                    }
                },
                confirmButton = { }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewScreen() {
    val examplePokemonAbility =
//        emptyList<PokemonAbility>()
        listOf(
            PokemonAbility(
                isHidden = false,
                slot = 1,
                ability = NamedApiResource(
                    name = "overgrow",
                    url = "https://pokeapi.co/api/v2/ability/65/"
                )
            ),
            PokemonAbility(
                isHidden = false,
                slot = 1,
                ability = NamedApiResource(
                    name = "sankar",
                    url = "https://pokeapi.co/api/v2/ability/65/"
                )
            ),
            PokemonAbility(
                isHidden = true,
                slot = 2,
                ability = NamedApiResource(
                    name = "chlorophyll",
                    url = "https://pokeapi.co/api/v2/ability/34/"
                )
            )
        )
//    PokemonAbilityCard(examplePokemonAbility, colorResource(id = R.color.card_color_8))
    val sampleData = listOf(30f, 8f, 55f, 10f, 75f, 45f)
    val sampleNames = listOf("Jfsdfsfsdfdsdan", "Fdsfseb", "Mar", "Aprsfsd", "Mayjune", "June")
    val barColors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Yellow
    )
    BarGraph(
        data = sampleData,
        barColors = barColors,
        names = sampleNames,
        modifier = Modifier.fillMaxWidth(), color = Color.Cyan
    )
}