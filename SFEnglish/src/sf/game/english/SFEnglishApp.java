package sf.game.english;

import sf.game.english.utils.SFBitmapManager;
import sf.game.english.utils.SFStorageManager;
import sf.libs.activity.BaseApp;

public class SFEnglishApp extends BaseApp {
	public static final String TAG = "SFEnglishApp";

	protected SFStorageManager mStorageManager = null;
	protected SFBitmapManager mBitmapManager = null;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mStorageManager = new SFStorageManager(this);
		this.mBitmapManager = new SFBitmapManager(this);
	}

	public SFStorageManager getmStorageManager() {
		return mStorageManager;
	}

	public SFBitmapManager getmBitmapManager() {
		return this.mBitmapManager;
	}
}
