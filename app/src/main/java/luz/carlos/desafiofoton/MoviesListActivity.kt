package luz.carlos.desafiofoton

import MoviesAdapter
import MoviesListViewModel
import MoviesListViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import luz.carlos.desafiofoton.data.Genre
import luz.carlos.desafiofoton.data.Movie
import luz.carlos.desafiofoton.data.MovieDetail
import luz.carlos.desafiofoton.movieDetail.MOVIE_ID
import luz.carlos.desafiofoton.movieDetail.MovieDetailActivity
import org.json.JSONObject


const val API_KEY = "ec3d3e5207c711ae852e5fec3470458d"
const val TMDB_POPULAR_URL = "https://api.themoviedb.org/3/movie/popular"
const val TMDB_DETAIL_URL = "https://api.themoviedb.org/3/movie/" // + {movie_id}
const val TMDB_SMALL_POSTER_URL = "https://image.tmdb.org/t/p/w185/"

class MoviesListActivity : AppCompatActivity() {

    private val TAG = "MoviesListActivity"

//    private val newMovieActivityRequestCode = 1

    private val moviesListViewModel by viewModels<MoviesListViewModel> {
        MoviesListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

//        val responseRequest = intent.extras?.get("RequestResponse").toString()

        // Instantiates moviesAdapter
        val moviesAdapter = MoviesAdapter { movie -> adapterOnClick(movie) }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = moviesAdapter

        moviesListViewModel.moviesLiveData.observe(this, {
            it?.let {
                moviesAdapter.submitList(it as MutableList<Movie>)
            }
        })

        val queue = Volley.newRequestQueue(this)
        val url = "$TMDB_POPULAR_URL?api_key=$API_KEY"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    val movieJson = JSONObject(response).getJSONArray("results")

                    moviesListViewModel.insertMovie(movieJson)
                },
                {
                    Log.e(TAG, "ERROR: Volley Error")
                }
        )
        queue.add(stringRequest)
    }

    /* Opens MovieDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(movie: Movie) {

//        val movieId = movie.id
//        val url = "$TMDB_DETAIL_URL$movieId?api_key=$API_KEY"
//
//        // Request a string response from the provided URL.
//        val stringRequest = StringRequest(
//                Request.Method.GET, url,
//                { response ->
//                    val movieDetailJsonObject = JSONObject(response)
//                    val id = movieDetailJsonObject.getInt("id")
//                    val title = movieDetailJsonObject.getString("title")
//                    val poster_path = movieDetailJsonObject.getString("poster_path")
//                    val runtime = movieDetailJsonObject.getInt("runtime")
//                    val overview = movieDetailJsonObject.getString("overview")
//
//                    val genresJsonArray = movieDetailJsonObject.getJSONArray("genres")
//                    val genres = mutableListOf<Genre>()
//                    for ( i in 0 until genresJsonArray.length() ) {
//                        val genre = genresJsonArray.getJSONObject(i)
//                        val genreId = genre.getInt("id")
//                        val genreName = genre.getString("name")
////                        genres.add( Genre(genreId, genreName) )
//                    }
//                    intent.putExtra("movieId", id)
//                    intent.putExtra("movieTitle", title)
//                    intent.putExtra("poster_path", poster_path)
//                    intent.putExtra("runtime", runtime)
////                    intent.putExtra("genres", genres)
//                    intent.putExtra("overview", overview)
//                },
//                {
//                    Log.e(TAG, "ERROR: Volley Error")
//                }
//        )
//        queue.add(stringRequest)




        val intent = Intent(this, MovieDetailActivity()::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
//    private fun fabOnClick() {
//        val intent = Intent(this, AddMovierActivity::class.java)
//        startActivityForResult(intent, newMovieActivityRequestCode)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
//        super.onActivityResult(requestCode, resultCode, intentData)
//
//        /* Inserts Movie into viewModel. */
//        if (requestCode == newMovieActivityRequestCode && resultCode == Activity.RESULT_OK) {
//            intentData?.let { data ->
//                val movieName = data.getStringExtra(MOVIE_TITLE)
//                val movieDescription = data.getStringExtra(MOVIE_OVERVIEW)
//
//                MoviesListViewModel.insertMovie(movieName, movieDescription)
//            }
//        }
//    }
}