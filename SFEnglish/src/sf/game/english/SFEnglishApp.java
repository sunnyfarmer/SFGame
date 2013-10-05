package sf.game.english;

import sf.game.english.utils.SFStorageManager;
import sf.libs.activity.BaseApp;

public class SFEnglishApp extends BaseApp {
	public static final String TAG = "SFEnglishApp";

	protected SFStorageManager mStorageManager = null;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mStorageManager = new SFStorageManager(this);
	}

	public SFStorageManager getmStorageManager() {
		return mStorageManager;
	}
}
