package com.example.letssopt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.ui.theme.AsDisable
import com.example.letssopt.ui.theme.AsPlaceholder
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun DefaultButton(modifier: Modifier,
                  text: String,
                  onClick: () -> Unit,
                  btEnabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = AsPrimary,
            disabledContainerColor = AsDisable,
            contentColor = AsWhite,
            disabledContentColor = AsPlaceholder),
        enabled = btEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = Bold),
            color = LocalContentColor.current
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultButtonPreview() {
    LETSSOPTTheme {
        DefaultButton(Modifier, "임시 버튼", onClick = {}, false)
    }
}