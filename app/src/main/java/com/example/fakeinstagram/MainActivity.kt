package com.example.fakeinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakeinstagram.ui.theme.FakeInstagramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeInstagramTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(
    imagenes: List<Int> = listOf(
        R.drawable.thispersondoesnotexist,
        R.drawable.thispersondoesnotexist1,
        R.drawable.thispersondoesnotexist2,
    ),
    stories: List<Int> = listOf(
        R.drawable.thispersondoesnotexist,
        R.drawable.thispersondoesnotexist1,
        R.drawable.thispersondoesnotexist2,
        R.drawable.thispersondoesnotexist2,
        R.drawable.thispersondoesnotexist1,
        R.drawable.thispersondoesnotexist,
    ),
    nombres: List<String> = listOf("Maria Unpajote", "Dolores Delano", "Solomeo Paredes")
){
    val shouldShowOnboarding = rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding.value) {
        Login(onContinueClicked = { shouldShowOnboarding.value = false })
    } else {
        Foro(imagenes, stories, nombres)
    }
}

@Composable
fun Foro (imagenes: List<Int>, stories: List<Int>, nombres: List<String>) {
    ListaStories(stories)
    ListaImagenes(imagenes, nombres)
}

@Composable
fun ListaImagenes(imagenes: List<Int>, nombres: List<String>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 125.dp)) {
        items(items = imagenes.zip(nombres)){ (imagen, nombre) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(imagen),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Transparent, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = nombre,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text("Hace 3 minutos")
                    }
                }
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "3 puntos"
                    )
                }
            }
        Image(
            painter = painterResource(id = imagen),
            contentDescription = "Imagen",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Outlined.FavoriteBorder,
                            contentDescription = "Corazón"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_comment_24),
                            contentDescription = "Comentario"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Outlined.Send,
                            contentDescription = "Enviar"
                        )
                    }
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Guardar"
                    )
                }
            }
            Text(
                text = "Les gusta a $nombre y a cientos de personas más",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 14.dp)
            )
            Row {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(nombre)
                        }
                        append(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis accumsan auctor sapien, nec fermentum quam tristique at. In sagittis diam quis massa aliquam suscipit.")
                    },
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
            }
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun ListaStories(stories: List<Int>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(stories) { imagenStorie ->
            Box(modifier = Modifier
                .padding(22.dp)
                .clip(CircleShape)
                .size(85.dp)
                    ) {
                Image(
                    painter = painterResource(id = imagenStorie),
                    contentDescription = "Storie",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(2.dp, Color.Magenta, CircleShape)
                )
            }
        }
    }
}

@Composable
fun Login (onContinueClicked: () -> Unit){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.tuenti),
                contentDescription = "Logo de instagram"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            EmailTextField()
            Spacer(modifier = Modifier.padding(8.dp))
            PasswordTextField()
            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = { onContinueClicked() }) {
                Text(text = "Iniciar sesión")
            }
        }
    }
}

@Composable
fun EmailTextField(){
    val email = rememberSaveable{mutableStateOf("")}
    TextField(
        value = email.value,
        onValueChange = { email.value = it },
        placeholder = { Text(text = "Email")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordTextField(){
    val password = rememberSaveable{mutableStateOf("")}
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        placeholder = { Text(text = "Password")},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}