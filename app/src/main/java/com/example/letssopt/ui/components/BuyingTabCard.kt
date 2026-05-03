package com.example.letssopt.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.data.local.model.BuyingTabCardItem
import com.example.letssopt.ui.theme.AsBg
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun BuyingTabCard(
    modifier: Modifier = Modifier,
    item: BuyingTabCardItem,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(2f/3f)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp, end = 6.dp)
                    .size(28.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(AsBg)
                    .clickable(onClick = onClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ticket),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.Start),
            text = item.text,
            style = MaterialTheme.typography.bodyMedium.copy(color = AsWhite),
        )
    }
}

@Composable
@Preview
fun BuyingTabCardPreview() {
    LETSSOPTTheme {
        BuyingTabCard(
            item = BuyingTabCardItem(
                image = R.drawable.colmjg1,
                text = "먼작귀 109화"
            ),
            onClick = {}
        )
    }
}
