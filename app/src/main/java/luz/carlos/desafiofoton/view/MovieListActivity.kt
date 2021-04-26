package luz.carlos.desafiofoton.view

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import luz.carlos.desafiofoton.R

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        val respostaTextView = findViewById<TextView>(R.id.resposta_textview)
        val responseRequest = intent.extras?.get("RequestResponse").toString()

        respostaTextView.text = responseRequest
    }
}