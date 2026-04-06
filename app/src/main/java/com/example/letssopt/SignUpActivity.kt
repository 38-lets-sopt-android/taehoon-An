package com.example.letssopt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.components.AsButton
import com.example.letssopt.ui.components.AsTextField
import com.example.letssopt.ui.theme.*
import com.example.letssopt.ui.theme.LETSSOPTTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignupContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SignupContent(modifier: Modifier = Modifier) {
    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }
    var textPwCheck by remember { mutableStateOf("") }

    //전반적인 레이아웃
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = AsBg)
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
        ) {
        //위쪽 컴포넌트 정렬을 위한 2차 레이아웃 wrapper
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
            ) {
            Text(style = MaterialTheme.typography.titleLarge.copy(fontSize = 36.sp, color = AsPrimary),
                text = "watcha",
                modifier = Modifier.padding(top = 40.dp).align(Alignment.CenterHorizontally))
            Text(style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                text = "회원가입",
                modifier = Modifier.padding(top = 20.dp))
            Text(style = MaterialTheme.typography.labelSmall,
                text = "이메일",
                modifier = Modifier.padding(top = 28.dp))
            AsTextField(text = textId,
                onValueChange = { newText ->
                    textId = newText
                },
                "이메일 주소를 입력하세요",
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
            Text(style = MaterialTheme.typography.labelSmall,
                text = "비밀번호",
                modifier = Modifier.padding(top = 12.dp))
            AsTextField(text = textPw,
                onValueChange = { newText ->
                    textPw = newText
                },
                "비밀번호를 입력하세요",
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
            Text(style = MaterialTheme.typography.labelSmall,
                text = "비밀번호 확인",
                modifier = Modifier.padding(top = 12.dp))
            AsTextField(text = textPwCheck,
                onValueChange = { newText ->
                    textPwCheck = newText
                },
                "비밀번호를 다시 입력하세요",
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
        }
        // 이쪽 부분에선
        AsButton("회원가입", onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp))

    }

}

@Preview(showBackground = true)
@Composable
fun SignupContentPreview() {
    LETSSOPTTheme {
        SignupContent()
    }
}