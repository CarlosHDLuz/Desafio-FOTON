package luz.carlos.desafiofoton.data

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONArray

/* Handles operations on moviesLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialMovieList = movieList(resources)
    private val moviesLiveData = MutableLiveData(initialMovieList)

    /* Adds movie to liveData and posts value. */
//    fun addMovie(movie: Movie) {
//        val currentList = moviesLiveData.value
//        if (currentList == null) {
//            moviesLiveData.postValue(listOf(movie))
//        } else {
//            val updatedList = currentList.toMutableList()
//            updatedList.add(0, movie)
//            moviesLiveData.postValue(updatedList)
//        }
    fun addMovie(movieJson: JSONArray) {
        val movieList = mutableListOf<Movie>()
        for ( i in 0 until movieJson.length() ){
            val movie = movieJson.getJSONObject(i)
            val id = movie.getInt("id")
            val title = movie.getString("title")
            val posterPath = movie.getString("poster_path")
            Log.d("DataSource", "DEBUG: " + movieJson.length().toString() )
            movieList.add( Movie(id, title, posterPath) )
        }
        moviesLiveData.postValue(movieList)
    }

    /* Removes movie from liveData and posts value. */
    fun removeMovie(movie: Movie) {
        val currentList = moviesLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(movie)
            moviesLiveData.postValue(updatedList)
        }
    }

    /* Returns movie given an ID. */
    fun getMovieForId(id: Int): Movie? {
        moviesLiveData.value?.let { movies ->
            return movies.firstOrNull{ it.id == id }
        }
        return null
    }

    fun getMovieList(): LiveData<List<Movie>> {
        return moviesLiveData
    }

    /* Returns a random movie asset for movies that are added. */
//    fun getRandomMovieImageAsset(): Int? {
//        val randomNumber = (initialMovieList.indices).random()
//        return initialMovieList[randomNumber].poster_path
//    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}