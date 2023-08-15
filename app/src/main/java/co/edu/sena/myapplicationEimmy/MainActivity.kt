package co.edu.sena.myapplicationEimmy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.sena.myapplicationEimmy.ui.theme.MyApplicationEimmyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Conversation(SampleData.conversationSample)
        }
    }
}

data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {

    Row (modifier = Modifier.padding(all = 8.dp)) {
        Image(painter = painterResource(R.drawable.profile_photo),
                contentDescription = "Contact profile picture",
                modifier = Modifier

                    .size(60.dp)
                    .clip(CircleShape)
                    .border(2.4.dp, Color.Blue, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {mutableStateOf(false)}
        val surfaceColor by animateColorAsState(
            if (isExpanded) Color.Cyan else Color.Gray, label = ""
        )

        Column (modifier =Modifier.clickable{ isExpanded = !isExpanded}) {
            Text(
                    text=msg.author,
                    color=Color.Blue,
                    style=MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color=surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground=true)
@Composable
fun MessageCardPreview() {
    MyApplicationEimmyTheme {
        Surface {
            MessageCard(
                    msg= Message("Collegue","Take a look at Jetpack Compose, it's great!")
            )
        }
    }


}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{
        items(messages){ message ->
            MessageCard(message)
        }
    }
}

@Preview(showBackground = true )
@Composable
fun ConversationPreview(){
    MyApplicationEimmyTheme {
        Conversation(SampleData.conversationSample)
    }

}