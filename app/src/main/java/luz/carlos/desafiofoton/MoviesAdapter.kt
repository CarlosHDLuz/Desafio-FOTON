import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import luz.carlos.desafiofoton.R
import luz.carlos.desafiofoton.TMDB_SMALL_POSTER_URL
import luz.carlos.desafiofoton.data.Movie
import luz.carlos.desafiofoton.movieDetail.TMDB_POSTER_URL

class MoviesAdapter(private val onClick: (Movie) -> Unit) :
        ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback) {

    /* ViewHolder for Movie, takes in the inflated view and the onClick behavior. */
    class MovieViewHolder(itemView: View, val onClick: (Movie) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

        private val movieTextView: TextView = itemView.findViewById(R.id.movie_text)
        private val movieImageView: ImageView = itemView.findViewById(R.id.movie_image)
        private var currentMovie: Movie? = null

        init {
            itemView.setOnClickListener {
                currentMovie?.let {
                    onClick(it)
                }
            }
        }

        /* Bind Movie name and image. */
        fun bind(movie: Movie) {
            currentMovie = movie

            movieTextView.text = movie.title
            if (movie.poster_path != null) {
//                movieImageView.setImageResource(movie.poster_path)
                Picasso.get().load(
                    TMDB_SMALL_POSTER_URL + movie.poster_path
                ).placeholder(R.drawable.place_holder).into(movieImageView)
            } else {
                movieImageView.setImageResource(R.drawable.rose)
            }
        }
    }

    /* Creates and inflates view and return MovieViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, onClick)
    }

    /* Gets current Movie and uses it to bind view. */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)

    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}