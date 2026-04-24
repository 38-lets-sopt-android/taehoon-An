package com.example.letssopt.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.data.local.model.WatchPartyItemDTO
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsSurface
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun WatchaPartyItem(
    modifier: Modifier = Modifier,
    item: WatchPartyItemDTO,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .size(196.dp, 185.dp)
            .fillMaxSize()
            .paint(
                painterResource(id = item.image),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp, end = 4.dp)
                .size(35.dp)
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(AsWhite)
                .clickable { onClick },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_fillring),
                contentDescription = "ring button",
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(color = AsSurface)
                .padding(start = 8.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.startLabel,
                style = MaterialTheme.typography.labelSmall.copy(color = AsPrimary)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                item.hashTag.forEach { item ->
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "# $item",
                        style = MaterialTheme.typography.labelSmall.copy(color = AsWhite, fontWeight = Bold)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WatchaPartyItemPreview() {
    LETSSOPTTheme {
        WatchaPartyItem(
            item = WatchPartyItemDTO(
                image = R.drawable.lastimg1,
                startLabel = "오늘 22:15분에 시작",
                hashTag = listOf("치이카와", "먼작귀")
            ),
            onClick = {}
        )
    }
}
