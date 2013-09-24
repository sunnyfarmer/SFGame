package sf.util;

import net.youmi.android.AdManager;
import net.youmi.android.smart.SmartBannerManager;
import net.youmi.android.spot.SpotDialogLinstener;
import net.youmi.android.spot.SpotManager;
import android.content.Context;

public class SFAdvertisement {
	public static final String TAG = "SFAdvertisement";

	public static void init(Context context) {
		AdManager.getInstance(context).init("a9fb72cb594861c6","86768b92a6414c67", false);
	}

	public static void showSmartBanner(Context context) {
		SmartBannerManager.init(context);
		SmartBannerManager.show(context);
	}

	public static void showSpotAd(Context context) {
		SpotManager.getInstance(context).loadSpotAds();
		SpotManager.getInstance(context).showSpotAds(context, new SpotDialogLinstener() {
			@Override
			public void onShowSuccess() {
				SFLogger.d(TAG, "success youmi");
			}
			@Override
			public void onShowFailed() {
				SFLogger.d(TAG, "fail youmi");
			}
			
			@Override
			public void onClicked() {
				SFLogger.d(TAG, "click youmi");
			}
		});
	}
}
