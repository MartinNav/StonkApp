package cz.martinnav.stonkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.material.icons
import androidx.compose.ui.res.painterResource
import cz.martinnav.stonkapp.ui.theme.StonkAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var searchTicker by remember{
                mutableStateOf("")
            }
            StonkAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column{
                        Card(modifier = Modifier.fillMaxWidth()) {
                            //Text(text = "Stonk App", modifier = Modifier.padding(5.dp,2.dp,0.dp,5.dp))
                            OutlinedTextField(value = searchTicker, onValueChange = {searchTicker = it}, modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),label= {Row {
                              Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search icon")
                                Text(text = "Search ticker", modifier = Modifier.padding(8.dp,0.dp,0.dp,0.dp))
                            }})
                        }
                        //TODO: Read and parse the data from yahoo finance api
                        Card (modifier = Modifier.padding(5.dp).fillMaxWidth().fillMaxHeight(0.4f)){
                            Text(text = "\$AMD", modifier = Modifier.padding(5.dp))
                            Text(text = "Here will be the chart")
                        }

                    }
                }
            }
        }
    }
}

