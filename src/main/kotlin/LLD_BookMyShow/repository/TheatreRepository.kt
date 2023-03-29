package LLD_BookMyTheatre.repository

import LLD_BookMyShow.model.Audi
import LLD_BookMyShow.model.Show
import LLD_BookMyShow.model.Theatre

interface TheatreRepository {

    fun createTheatre(
        theatreName: String,
        theatreAdd: String,
        theatreCity: String,
        audiList: List<Audi>
    )

    fun removeTheatre(theatre: Theatre)

    fun getAllTheatre(): List<List<Theatre>>

    fun getCityTheatre(city: String): List<Theatre>

    fun getById(theatreId: String): Theatre?
}