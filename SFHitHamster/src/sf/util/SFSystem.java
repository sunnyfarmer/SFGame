package sf.util;

import sf.game.hithamster.model.GameSetting;
import android.content.Context;
import android.os.Vibrator;
import android.os.Build.VERSION;

public class SFSystem {

	public static int apiLevel(Context context) {
		int versioncode = VERSION.SDK_INT;
		return versioncode;
	}

	public static void vibrate(Context context, long milliseconds) {
		if (GameSetting.shakeSetting(context)) {
			Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(milliseconds);
		}
	}
}
