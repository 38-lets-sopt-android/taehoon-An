package com.example.letssopt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.theme.AsPlaceholder
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsSurface
import com.example.letssopt.ui.theme.AsWhite
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun AsButton(text: String, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = MaterialTheme.shapes.small,
        colors = ButtonDefaults.buttonColors(
            containerColor = AsPrimary,
            disabledContainerColor = AsSurface,
            contentColor = AsWhite,
            disabledContentColor = AsPlaceholder)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AsTextField(text: String, onValueChange: (String) -> Unit, hint: String, modifier: Modifier) {
    TextField(
        value = text,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier.height(52.dp),
        textStyle = MaterialTheme.typography.labelSmall.copy(color = AsWhite),
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.labelSmall.copy(color = AsPlaceholder)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = AsSurface,
            focusedContainerColor = AsPlaceholder,
            cursorColor = AsSurface,
            focusedIndicatorColor = AsWhite,
            unfocusedIndicatorColor = AsSurface,
            disabledIndicatorColor = AsSurface
        )

    )
}

@Preview(showBackground = true)
@Composable
fun AsButtonPreview() {
    LETSSOPTTheme {
        AsButton("임시 버튼", onClick = {}, Modifier.padding())
    }
}

@Preview(showBackground = true)
@Composable
fun AsTFPreview() {
    LETSSOPTTheme {
        AsTextField("", {}, "아이디 입력해주세요", Modifier.padding())
    }
}