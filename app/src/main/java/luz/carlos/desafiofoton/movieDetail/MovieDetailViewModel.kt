package luz.carlos.desafiofoton.movieDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import luz.carlos.desafiofoton.data.DataSource
import luz.carlos.desafiofoton.data.Movie

class MovieDetailViewModel(private val datasource: DataSource) : ViewModel() {

    /* Queries datasource to returns a movie that corresponds to an id. */
    fun getMovieForId(id: Int) : Movie? {
        return datasource.getMovieForId(id)
    }

    /* Queries datasource to remove a movie. */
//    fun removeMovie(movie: Movie) {
//        datasource.removeMovie(movie)
//    }
}

class MovieDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}