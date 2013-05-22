package sf.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.SettingNotFoundException;

public class SFSystem {

	public static int apiLevel(Context context) {
		int versioncode = VERSION.SDK_INT;
		return versioncode;
	}
}
