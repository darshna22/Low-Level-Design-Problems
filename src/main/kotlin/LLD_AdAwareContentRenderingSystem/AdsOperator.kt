package LLD_AdAwareContentRenderingSystem

import LLD_AdAwareContentRenderingSystem.ads_policy.AdsPolicy
import LLD_AdAwareContentRenderingSystem.ads_policy.EndAdsPolicy
import LLD_AdAwareContentRenderingSystem.ads_policy.IntervalAdsPolicy
import LLD_AdAwareContentRenderingSystem.ads_policy.StartAdsPolicy
import LLD_AdAwareContentRenderingSystem.enums.AdsPolicyType

class AdsOperator {

    fun getAdsPolicy(adsPolicyType: AdsPolicyType, addInterval: Int = 0): AdsPolicy {
        return when (adsPolicyType) {
            AdsPolicyType.INTERVAL_ADS_POLICY -> IntervalAdsPolicy(addInterval)
            AdsPolicyType.START_ADS_POLICY -> StartAdsPolicy()
            AdsPolicyType.END_ADS_POLICY -> EndAdsPolicy()
        }
    }
}