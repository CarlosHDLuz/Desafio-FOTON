package luz.carlos.desafiofoton.movieDetail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import luz.carlos.desafiofoton.API_KEY
import luz.carlos.desafiofoton.R
import luz.carlos.desafiofoton.TMDB_DETAIL_URL
import org.json.JSONObject


const val MOVIE_ID = "movie id"
const val TMDB_POSTER_URL = "https://image.tmdb.org/t/p/w342/"


class MovieDetailActivity : AppCompatActivity() {

    private val MovieDetailViewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

//        supportActionBar?.title = "Detalhes"

        var currentMovieId: Int? = null

        /* Connect variables to UI elements. */
        val movieTitle: TextView = findViewById(R.id.movie_detail_title)
        val moviePoster: ImageView = findViewById(R.id.movie_detail_poster)
        val movieRuntime: TextView = findViewById(R.id.movie_detail_runtime)
        val movieGenres: TextView = findViewById(R.id.movie_detail_genres)
        val movieOverview: TextView = findViewById(R.id.movie_detail_overview)

        val progressBar: ProgressBar = findViewById(R.id.poster_progress_bar)


        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentMovieId = bundle.getInt(MOVIE_ID)
        }

        /* If currentMovieId is not null, get corresponding movie and set name, image and
        description */
//        currentMovieId?.let {
//            val currentMovie = MovieDetailViewModel.getMovieForId(it)
//            movieTitle.text = currentMovie?.title
//            if (currentMovie?.poster_path == null) {
//                moviePoster.setImageResource(R.drawable.rose)
//            } else {
////                movieImage.setImageResource(currentMovie.poster_path)
//                // Setar Pôster
//            }
////            movieDescription.text = currentMovie?.overview
//
//        }

        if (currentMovieId == null )
            finish()

        val url = "$TMDB_DETAIL_URL$currentMovieId?api_key=$API_KEY"

        // Request a string response from the provided URL.
        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val movieDetailJsonObject = JSONObject(response)
//                    val id = movieDetailJsonObject.getInt("id")
                    movieTitle.text = movieDetailJsonObject.getString("title")
                    supportActionBar?.title = movieTitle.text
                    val poster_path = movieDetailJsonObject.getString("poster_path")
                    movieRuntime.text = movieDetailJsonObject.getInt("runtime").toString()
                    movieOverview.text = movieDetailJsonObject.getString("overview")

                    val genresJsonArray = movieDetailJsonObject.getJSONArray("genres")
                    var genresString = ""
                    for ( i in 0 until genresJsonArray.length() ) {
                        val genre = genresJsonArray.getJSONObject(i)
                        genresString += genre.getString("name") + '\n'
                    }
                    movieGenres.text = genresString
                    Log.d("MovieDetailActivity", "DEBUG: $TMDB_POSTER_URL$poster_path")
                    Picasso.get().load("$TMDB_POSTER_URL$poster_path").placeholder(R.drawable.place_holder).into(moviePoster)

                    progressBar.visibility = ProgressBar.GONE
                },
                {
                    Log.e("MovieDetailActivity", "ERROR: Volley Error")
                }
        )
        queue.add(stringRequest)
        progressBar.visibility = ProgressBar.VISIBLE
    }
}