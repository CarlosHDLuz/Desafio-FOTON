import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import luz.carlos.desafiofoton.data.DataSource
import luz.carlos.desafiofoton.data.Movie
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random

class MoviesListViewModel(val dataSource: DataSource) : ViewModel() {

    val moviesLiveData = dataSource.getMovieList()

    /* If the name and description are present, create new Movie and add it to the datasource */
//    fun insertMovie(movietitle: String?, movieOverview: String?) {
//        if (movietitle == null || movieOverview == null) {
//            return
//        }
//
//        val image = dataSource.getRandomMovieImageAsset()
//        val newMovie = Movie(
//                Random.nextLong(),
//                movietitle,
//                image,
//                movieOverview,
//        )
    fun insertMovie(movieJson: JSONArray) {
        Log.d("MoviesListViewModel", "DEBUG: " + movieJson.length().toString() )
        dataSource.addMovie(movieJson)
    }
}

class MoviesListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MoviesListViewModel(
                    dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}