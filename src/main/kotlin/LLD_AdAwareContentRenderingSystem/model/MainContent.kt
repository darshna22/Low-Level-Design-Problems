package LLD_AdAwareContentRenderingSystem.model

sealed class MainContentItem {
    data class MovieItem(val movie: Movie) : MainContentItem()
    data class AdsItem(val ads: MovieAd) : MainContentItem()
}
