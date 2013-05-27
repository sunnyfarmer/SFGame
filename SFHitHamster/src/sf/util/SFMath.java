package sf.util;

public class SFMath {
	/**
	 * 获得 >=min <=max 的数字
	 * @param min
	 * @param max
	 * @return
	 */
	public static final int randomInt(int min, int max) {
		int randomInt = (int) (min + (max-min+1) * Math.random());
		return randomInt;
	}
}
