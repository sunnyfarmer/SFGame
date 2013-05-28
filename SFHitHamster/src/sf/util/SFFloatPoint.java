package sf.util;

public class SFFloatPoint {
	public SFFloatPoint(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public SFFloatPoint() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public float x;
	public float y;

	public boolean isInRect(float x, float y, float width, float height) {
		if (this.x > x && this.x < x+width && this.y > y && this.y < y+height) {
			return true;
		}
		return false;
	}
}
