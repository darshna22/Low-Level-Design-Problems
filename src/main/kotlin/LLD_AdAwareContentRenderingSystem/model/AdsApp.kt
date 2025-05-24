package LLD_AdAwareContentRenderingSystem.model

import LLD_AdAwareContentRenderingSystem.AdsOperator
import LLD_AdAwareContentRenderingSystem.enums.AdsPolicyType

fun main() {
    val movieList =
        listOf(
            Movie("Movie1", 12, "1"),
            Movie("Movie2", 12, "2"),
            Movie("Movie3", 12, "3"),
            Movie("Movie4", 12, "4"),
            Movie("Movie5", 12, "5")
        )
    val adsList =
        listOf(
            MovieAd("Ads1", 12, "1"),
            MovieAd("Ads2", 12, "2"),
            MovieAd("Ads3", 12, "3"),
            MovieAd("Ads4", 12, "4")
        )
    val movieItemList = movieList.map {
        MainContentItem.MovieItem(it)
    }

    val adsItemList = adsList.map {
        MainContentItem.AdsItem(it)
    }


    val resultList = AdsOperator().getAdsPolicy(AdsPolicyType.INTERVAL_ADS_POLICY, 1)
        .applyPolicy(movieItemList, adsItemList)

    resultList.forEach { data ->
        when (data) {
            is MainContentItem.MovieItem -> println(data.movie.name)
            is MainContentItem.AdsItem -> println(data.ads.name)
        }
    }

    println("================================================================")

    val resultList1 = AdsOperator().getAdsPolicy(AdsPolicyType.START_ADS_POLICY)
        .applyPolicy(movieItemList, adsItemList)

    resultList1.forEach { data ->
        when (data) {
            is MainContentItem.MovieItem -> println(data.movie.name)
            is MainContentItem.AdsItem -> println(data.ads.name)
        }
    }

    println("================================================================")
    val resultList3 = AdsOperator().getAdsPolicy(AdsPolicyType.END_ADS_POLICY)
        .applyPolicy(movieItemList, adsItemList)

    resultList3.forEach { data ->
        when (data) {
            is MainContentItem.MovieItem -> println(data.movie.name)
            is MainContentItem.AdsItem -> println(data.ads.name)
        }
    }
}