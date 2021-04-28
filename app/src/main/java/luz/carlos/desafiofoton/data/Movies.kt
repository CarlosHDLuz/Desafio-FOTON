package luz.carlos.desafiofoton.data

import android.content.res.Resources
import luz.carlos.desafiofoton.R

/* Returns initial list of movies. */
fun movieList(resources: Resources): List<Movie> {
    return listOf(
        Movie(
            id = 1,
            title = resources.getString(R.string.movie1_name),
            poster_path = R.drawable.rose.toString(),
        ),
//        Movie(
//            id = 2,
//            title = resources.getString(R.string.movie2_name),
//            poster_path = R.drawable.freesia.toString(),
//        ),
//        Movie(
//            id = 3,
//            title = resources.getString(R.string.movie3_name),
//            poster_path = R.drawable.lily.toString(),
//        ),
//        Movie(
//            id = 4,
//            title = resources.getString(R.string.movie4_name),
//            poster_path = R.drawable.sunflower.toString(),
//        ),
//        Movie(
//            id = 5,
//            title = resources.getString(R.string.movie5_name),
//            poster_path = R.drawable.peony.toString(),
//        ),
//        Movie(
//            id = 6,
//            title = resources.getString(R.string.movie6_name),
//            poster_path = R.drawable.daisy.toString(),
//        ),
//        Movie(
//            id = 7,
//            title = resources.getString(R.string.movie7_name),
//            poster_path = R.drawable.lilac.toString(),
//        ),
//        Movie(
//            id = 8,
//            title = resources.getString(R.string.movie8_name),
//            poster_path = R.drawable.marigold.toString(),
//        ),
//        Movie(
//            id = 9,
//            title = resources.getString(R.string.movie9_name),
//            poster_path = R.drawable.poppy.toString(),
//        ),
//        Movie(
//            id = 10,
//            title = resources.getString(R.string.movie10_name),
//            poster_path = R.drawable.daffodil.toString(),
//        ),
//        Movie(
//            id = 11,
//            title = resources.getString(R.string.movie11_name),
//            poster_path = R.drawable.dahlia.toString(),
//        )
    )
}