package luz.carlos.desafiofoton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import luz.carlos.desafiofoton.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val informaUserTextView = findViewById<TextView>(R.id.whats_happening)
        // TODO: informaUserTextView.text = o que tá acontecendo

        informaUserTextView.text = getString(R.string.instanciando_request_queue)
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.themoviedb.org/3" + "/movie/popular" + "?api_key=ec3d3e5207c711ae852e5fec3470458d"

        informaUserTextView.text = getString(R.string.creating_request)
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                // Display the first 500 characters of the response string.
                informaUserTextView.text = getString(R.string.success_answer_for_request)

                val intent = Intent(this, MovieListActivity::class.java)
                intent.putExtra("RequestResponse", response)
                startActivity(intent)
            },
            { informaUserTextView.text = getString(R.string.failed_answer_for_request) })

        informaUserTextView.text = getString(R.string.add_request_to_queue)
        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        informaUserTextView.text = getString(R.string.waiting_for_answer)
    }
}