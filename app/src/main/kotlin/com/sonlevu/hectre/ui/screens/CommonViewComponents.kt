package com.sonlevu.hectre.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import com.sonlevu.hectre.ui.theme.PrimaryColorRed
import com.sonlevu.hectre.ui.theme.Purple40

@Composable
fun TopBarViewWithBack(navigateBack: () -> Unit, @StringRes idString: Int) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = idString),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier.padding(top = 24.dp),
        backgroundColor = PrimaryColorRed,
        navigationIcon = {
            IconButton(
                onClick = navigateBack,
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        })
}

@Composable
fun AvatarFromName(name: String) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .padding(3.dp)
            .background(shape = CircleShape, color = Purple40),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name[0].toString(),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration? = null,// = TextDecoration.Underline,
    hyperlinks: List<String> = listOf("https://elvis.com"),
    fontSize: TextUnit = TextUnit.Unspecified,
    isLockClick: Boolean = false
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = modifier,
        style = TextStyle(textAlign = TextAlign.Center),
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    if (!isLockClick)
                        uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Composable
fun OrchardRow(text: String, isPartiallyDone: Boolean = false, isAssigned: Boolean = true) {
    ConstraintLayout(Modifier.size(24.dp)) {
        val (row, dot) = createRefs()
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(shape = RoundedCornerShape(3.dp))
                .background(color = if (isAssigned) Color.Blue.copy(alpha = .7f) else Color.LightGray.copy(alpha = .5f))
                .constrainAs(row) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = fillToConstraints
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White)
        }
        if (isPartiallyDone) {
            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .size(5.dp)
                    .clip(shape = CircleShape)
                    .constrainAs(dot) {
                        top.linkTo(parent.top, margin = 2.dp)
                        end.linkTo(parent.end, margin = 2.dp)
                    }
            )
        }
    }
}