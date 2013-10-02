package sf.game.english.model;

import android.graphics.Bitmap;

public class CourseObject {
	public static final String TAG = "CourseObject";

	private String mObjectTitle = null;
	private String mObjectText = null;
	private Bitmap mObjectBitmap = null;

	public String getmObjectTitle() {
		return mObjectTitle;
	}

	public void setmObjectTitle(String mObjectTitle) {
		this.mObjectTitle = mObjectTitle;
	}

	public String getmObjectText() {
		return mObjectText;
	}

	public void setmObjectText(String mObjectText) {
		this.mObjectText = mObjectText;
	}

	public Bitmap getmObjectBitmap() {
		return mObjectBitmap;
	}

	public void setmObjectBitmap(Bitmap mObjectBitmap) {
		this.mObjectBitmap = mObjectBitmap;
	}
}
