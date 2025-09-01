package LLD_BookMyShow.model

import java.util.UUID

data class Theater(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val audiList: MutableList<Audi>
) {
    fun addAudiToTheatre(audi: Audi? = null, audiList: MutableList<Audi>? = null) {
        when {
            audi != null -> this.audiList.add(audi)
            audiList != null -> this.audiList.addAll(audiList)
        }
    }
}
