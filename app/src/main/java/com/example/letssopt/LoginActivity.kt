package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.letssopt.ui.components.AsButton
import com.example.letssopt.ui.components.AsTextField
import com.example.letssopt.ui.theme.*
import com.example.letssopt.ui.theme.LETSSOPTTheme


lateinit var saveId : String
lateinit var savePw : String

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val intent = Intent(context, SignUpActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val intent2 = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    var textId by remember { mutableStateOf("") }
    var textPw by remember { mutableStateOf("") }

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
                text = "이메일로 로그인",
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
        }
        //여기 두번쨰 컬럼에서 지정하지도 않은 padding이 양쪽 너비에 들어가서 로그인 버튼이 이상한 패딩이 적용됨
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            TextButton(onClick = {
                context.startActivity(intent)
            }
            ) {
                Text(style = MaterialTheme.typography.labelSmall,
                    text = "아직 계정이 없으신가요? 회원가입")
            }
            AsButton("로그인", onClick = {
                context.startActivity(intent2)
                Toast.makeText(context, "로그인 성공. 환영합니다. $textId 님.", Toast.LENGTH_SHORT)

//                if(!::saveId.isInitialized || !::savePw.isInitialized) {
//                    Toast.makeText(context, "회원가입이 필요합니다.", Toast.LENGTH_LONG)
//                } else {
//                    if(saveId == textId && savePw != textPw) {
//                        Toast.makeText(context, "비밀번호가 틀렸습니다. 다시 확인해주세요.", Toast.LENGTH_SHORT)
//                    } else if(saveId == textId && savePw == textPw) {
//                        context.startActivity(intent2)
//                        Toast.makeText(context, "로그인 성공. 환영합니다. $textId 님.", Toast.LENGTH_SHORT)
//                    } else {
//                        Toast.makeText(context, "로그인 실패. 다시 시도해주세요", Toast.LENGTH_LONG)
//                    }
//                }

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LETSSOPTTheme {
        LoginContent()
    }
}