package id.my.sidhiartha.tenjinandroidsdkexploratory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tenjin.android.TenjinSDK
import id.my.sidhiartha.tenjinandroidsdkexploratory.ui.theme.TenjinAndroidSDKExploratoryTheme


class MainActivity : ComponentActivity() {
    lateinit var tenjinInstance: TenjinSDK;

    override fun onResume() {
        super.onResume()

        tenjinInstance = TenjinSDK.getInstance(this, "YOUR_TENJIN_API_KEY")
        tenjinInstance.setAppStore(TenjinSDK.AppStoreType.googleplay)
        tenjinInstance.connect()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TenjinAndroidSDKExploratoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        tenjinInstance,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, tenjinInstance: TenjinSDK? = null) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = {
            tenjinInstance?.eventWithName("swipe_right")
        }) {
            Text("Send eventWithName")
        }
        Button(onClick = {
            tenjinInstance?.eventWithNameAndValue("item", 100)
        }) {
            Text("Send eventWithNameAndValue")
        }
        Button(onClick = {
            tenjinInstance?.transaction(
                "productId",
                "USD",
                1,
                3.80,
                "androidPurchaseData",
                "androidDataSignature",
            )
        }) {
            Text("Send Transaction with Receipt")
        }
        Button(onClick = {
            tenjinInstance?.transaction(
                "productId",
                "USD",
                1,
                3.80,
            )
        }) {
            Text("Send Transaction")
        }
        Button(onClick = {
            tenjinInstance?.customerUserId = "test_user_id"
        }) {
            Text("Set Customer Id")
        }
        Button(onClick = {
            print(tenjinInstance?.customerUserId)
        }) {
            Text("Get Customer Id")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TenjinAndroidSDKExploratoryTheme {
        Greeting("Android")
    }
}
