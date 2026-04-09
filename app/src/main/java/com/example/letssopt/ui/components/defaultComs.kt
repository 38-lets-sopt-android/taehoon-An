package com.example.letssopt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.ui.theme.AsDisable
import com.example.letssopt.ui.theme.AsPlaceholder
import com.example.letssopt.ui.theme.AsPrimary
import com.example.letssopt.ui.theme.AsSurface
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

@Composable
fun DefaultTextField(modifier: Modifier,
                     text: String,
                     onValueChange: (String) -> Unit,
                     hint: String,
                     tfVisible : Boolean,
                     keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                     keyboardActions: KeyboardActions = KeyboardActions.Default,
                ) {
    TextField(
        value = text,
        onValueChange = onValueChange,
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
            focusedContainerColor = AsSurface,
            cursorColor = AsWhite,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent
        ),
        visualTransformation = if (tfVisible) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions

    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultButtonPreview() {
    LETSSOPTTheme {
        DefaultButton(Modifier, "임시 버튼", onClick = {}, false)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultTFPreview() {
    LETSSOPTTheme {
        DefaultTextField(Modifier, "", {}, "아이디 입력해주세요", true, KeyboardOptions.Default, KeyboardActions.Default )
    }
}