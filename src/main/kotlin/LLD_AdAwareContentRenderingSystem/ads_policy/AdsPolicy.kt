package LLD_AdAwareContentRenderingSystem.ads_policy

import LLD_AdAwareContentRenderingSystem.enums.AdsPolicyType
import LLD_AdAwareContentRenderingSystem.model.MainContentItem

interface AdsPolicy {
    fun applyPolicy(
        contentList: List<MainContentItem>,
        adsList: List<MainContentItem>
    ): List<MainContentItem>
}