package sf.game.english.model;

import sf.game.english.SFEnglishApp;
import sf.game.english.utils.SFResourseManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CourseObject {
	public static final String TAG = "CourseObject";

	private int mIndex = 0;
	private String mObjectText = null;
	private Bitmap mObjectBitmap = null;
	private boolean mIsOpened = false;
	private int mCorrectTimes = 0;

	public CourseObject(int index, String objectText) {
		this.setmIndex(index);
		this.setmObjectText(objectText);
	}

	public int getmIndex() {
		return mIndex;
	}

	public void setmIndex(int mIndex) {
		this.mIndex = mIndex;
	}

	public String getmObjectText() {
		return mObjectText;
	}

	public void setmObjectText(String mObjectText) {
		this.mObjectText = mObjectText;
	}

	public Bitmap getmObjectBitmap(SFEnglishApp app) {
		if (this.mObjectBitmap==null) {
			int resId = SFResourseManager.courseObjectBitmapId(mObjectText);
			this.mObjectBitmap = app.getmBitmapManager().getBitmap(resId, 64, 64);
					//BitmapFactory.decodeResource(context.getResources(), resId);
		}
		return mObjectBitmap;
	}

	public boolean ismIsOpened() {
		return mIsOpened;
	}

	public void setmIsOpened(boolean mIsOpened) {
		this.mIsOpened = mIsOpened;
	}

	public int getmCorrectTimes() {
		return mCorrectTimes;
	}

	public void setmCorrectTimes(int mCorrectTimes) {
		this.mCorrectTimes = mCorrectTimes;
	}
}