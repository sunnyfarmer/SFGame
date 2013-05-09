package sf.game.hithamster.model;

public class GameSetting {
	private static boolean isSoundEnable = true;
	private static boolean isShakeEnable = true;

	public static boolean soundSetting() {
		return GameSetting.isSoundEnable;
	}
	public static boolean shakeSetting() {
		return GameSetting.isShakeEnable;
	}
	public static void toggleSoundSetting() {
		GameSetting.isSoundEnable = !GameSetting.isSoundEnable;
	}
	public static void toggleShakeSetting() {
		GameSetting.isShakeEnable = !GameSetting.isShakeEnable;
	}
}
