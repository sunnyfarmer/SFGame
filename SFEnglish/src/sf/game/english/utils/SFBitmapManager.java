package sf.game.english.utils;

import sf.game.english.SFEnglishApp;
import sf.libs.log.SFLog;
import sf.libs.utils.SFBitmapFactory;
import android.graphics.Bitmap;

public class SFBitmapManager extends SFBitmapFactory<Integer>{
	public static final String TAG = "SFBitmapManager";

	private SFEnglishApp mApp = null;

	public SFBitmapManager(SFEnglishApp app) {
		this.mApp = app;
	}

	/**
	 * 获得Cargo的Bitmap
	 * @param cargoId
	 * @param app
	 * @return
	 */
	public Bitmap getBitmap(int resId, int reqWidth, int reqHeight) {
		Bitmap bitmap = null;
		try {
			bitmap = getBitmapFromArray(resId);
			if (bitmap==null) {
				bitmap = SFBitmapFactory.loadBitmap(this.mApp.getResources(), resId, reqWidth, reqHeight);
				putBitmapIntoArray(resId, bitmap);
			}
		} catch (Exception e) {
			SFLog.e(TAG, "decode bitmap error : " + resId);
		}
		return bitmap;
	}

}
