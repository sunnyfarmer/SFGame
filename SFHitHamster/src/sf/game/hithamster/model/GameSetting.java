package sf.game.hithamster.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GameSetting {
	private static boolean isSoundEnable = true;
	private static boolean isShakeEnable = true;

	public static final String SP_NAME = "sf_game_hithamster_setting";
	public static final String SP_KEY_SOUND = "sf_game_hithamster_setting_sound";
	public static final String SP_KEY_SHAKE = "sf_game_hithamster_setting_shake";

	public static SharedPreferences getSettingSP(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		return sp;
	}

	public static boolean soundSetting(Context context) {
		GameSetting.isSoundEnable = getSettingSP(context).getBoolean(SP_KEY_SOUND, true);
		return GameSetting.isSoundEnable;
	}
	public static boolean shakeSetting(Context context) {
		GameSetting.isShakeEnable = getSettingSP(context).getBoolean(SP_KEY_SHAKE, true);
		return GameSetting.isShakeEnable;
	}
	public static void toggleSoundSetting(Context context) {
		GameSetting.isSoundEnable = !GameSetting.isSoundEnable;
		Editor editor = getSettingSP(context).edit();
		editor.putBoolean(SP_KEY_SOUND, GameSetting.isSoundEnable);
		editor.commit();
	}
	public static void toggleShakeSetting(Context context) {
		GameSetting.isShakeEnable = !GameSetting.isShakeEnable;
		Editor editor = getSettingSP(context).edit();
		editor.putBoolean(SP_KEY_SHAKE, GameSetting.isShakeEnable);
		editor.commit();
	}
}
