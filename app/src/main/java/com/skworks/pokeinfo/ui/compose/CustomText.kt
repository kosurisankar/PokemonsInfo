package com.skworks.pokeinfo.ui.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String = "",
    modifier: Modifier = Modifier,
    textSize: TextUnit = 16.sp,
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: TextUnit = 20.sp,
    fontFamily: FontFamily? = FontFamily.Default,
    style: TextStyle = typography.bodySmall,
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = textSize,
        color = textColor,
        fontWeight = fontWeight,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        lineHeight = lineHeight,
        fontFamily = fontFamily,
        style = style
    )
}

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    CustomText(
        text = text,
        modifier = modifier,
        style = typography.headlineLarge,
        textAlign = TextAlign.Justify,
        fontWeight = fontWeight,
        fontFamily = FontFamily.Monospace,
        textSize = 20.sp,
        textColor = color
    )
}

@Composable
fun LargeText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    CustomText(
        text = text,
        modifier = modifier,
        style = typography.bodyLarge,
        textAlign = TextAlign.Justify,
        fontWeight = fontWeight,
        fontFamily = FontFamily.Default,
        textSize = 16.sp,
        textColor = color
    )
}

@Composable
fun MediumText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    CustomText(
        text = text,
        modifier = modifier,
        style = typography.bodyMedium,
        textAlign = TextAlign.Justify,
        textSize = 12.sp,
        fontWeight = fontWeight,
        textColor = color
    )
}

@Composable
fun SmallText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    CustomText(
        text = text,
        modifier = modifier,
        style = typography.bodySmall,
        textAlign = TextAlign.Justify,
        textSize = 10.sp,
        fontWeight = fontWeight,
        textColor = color
    )
}

@Composable
fun ExtraSmallText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Black
) {
    CustomText(
        text = text,
        modifier = modifier,
        style = typography.bodySmall,
        textAlign = TextAlign.Justify,
        textSize = 8.sp,
        fontWeight = fontWeight,
        textColor =color
    )
}

@Composable
fun MultilineText(text: String) {
    CustomText(
        text = text,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        textSize = 14.sp,
        textColor = Color.Black
    )
}
@Composable
@Preview(showBackground = true)
fun PreviewText5() {
    HeaderText(text = "This is ExtraSmall Text Preview")
}

@Composable
@Preview(showBackground = true)
fun PreviewText1() {
    LargeText(text = "This is Large Text Preview")
}

@Composable
@Preview(showBackground = true)
fun PreviewText2() {
    MediumText(text = "This is Medium Text Preview")
}

@Composable
@Preview(showBackground = true)
fun PreviewText3() {
    SmallText(text = "This is Small Text Preview", modifier = Modifier.padding(2.dp))
}

@Composable
@Preview(showBackground = true)
fun PreviewText4() {
    ExtraSmallText(text = "This is ExtraSmall Text Preview")
}
