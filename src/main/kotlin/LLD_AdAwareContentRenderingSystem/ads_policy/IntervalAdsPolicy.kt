package LLD_AdAwareContentRenderingSystem.ads_policy

import LLD_AdAwareContentRenderingSystem.model.MainContentItem

class IntervalAdsPolicy(private val interval: Int) : AdsPolicy {

    override fun applyPolicy(
        contentList: List<MainContentItem>,
        adsList: List<MainContentItem>
    ): List<MainContentItem> {
        if (interval <= 0 || contentList.isEmpty()) return contentList
        if (adsList.isEmpty()) return contentList
        val result = mutableListOf<MainContentItem>()
        var adIndex = 0
        contentList.forEachIndexed { index, content ->
            result.add(content)
            if ((index + 1) % interval == 0) {
                result.add(adsList[adIndex % adsList.size])
                adIndex++
            }
        }
        return result
    }
}
