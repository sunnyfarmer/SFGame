package sf.game.english.utils;

import java.util.Locale;

import sf.game.english.SFEnglishApp;
import sf.libs.log.SFLog;
import sf.libs.utils.SFBitmapFactory;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;

public class SFBitmapManager extends SFBitmapFactory<String>{
	public static final String TAG = "SFBitmapManager";

	private SFEnglishApp mApp = null;

	public SFBitmapManager(SFEnglishApp app) {
		this.mApp = app;
	}

	/**
	 * 获得Bitmap
	 * @param resId
	 * @param app
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public Bitmap getBitmap(int resId, int reqWidth, int reqHeight) {
		Bitmap bitmap = null;
		try {
			String key = String.format(Locale.US, "%d_%d_%d", resId, reqWidth, reqHeight);
			bitmap = getBitmapFromArray(key);
			if (bitmap==null) {
				bitmap = SFBitmapFactory.loadBitmap(this.mApp.getResources(), resId, reqWidth, reqHeight);
				putBitmapIntoArray(key, bitmap);
			}
		} catch (Exception e) {
			SFLog.e(TAG, e.getMessage(), e);
			SFLog.e(TAG, "decode bitmap error : " + resId);
		}
		return bitmap;
	}

}
