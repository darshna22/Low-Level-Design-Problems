package LLD_AdAwareContentRenderingSystem.ads_policy

import LLD_AdAwareContentRenderingSystem.enums.AdsPolicyType
import LLD_AdAwareContentRenderingSystem.model.MainContentItem

class StartAdsPolicy() : AdsPolicy {
    override fun applyPolicy(
        contentList: List<MainContentItem>,
        adsList: List<MainContentItem>
    ): List<MainContentItem> {
        val result = mutableListOf<MainContentItem>()
        if (contentList.isNotEmpty() && adsList.isNotEmpty()) {
            result.addAll(adsList)
            result.addAll(contentList)
        } else if (adsList.isNotEmpty()) {
            result.addAll(contentList)
        }
        return result
    }
}