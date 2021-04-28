package luz.carlos.desafiofoton.data

import org.json.JSONObject

data class MovieDetail (
        val id: Int,
        val title: String,
        val poster_path: String?,
        val runtime: Int?,
        val genres: MutableList<Genre>,
        val overview: String?
        )