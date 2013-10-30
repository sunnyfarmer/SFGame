package sf.game.english.model;

import sf.game.english.SFEnglishApp;
import sf.game.english.utils.SFGlobal;
import sf.game.english.utils.SFResourseManager;
import android.graphics.Bitmap;

public class CourseObject {
	public static final String TAG = "CourseObject";

	private int mIndex = 0;
	private String mObjectText = null;
	private String mObjectChinese = null;
	private Bitmap mObjectBitmap = null;
	private int mCorrectTimes = 0;

	public static final int CORRECT_TIMES_TO_UNLOCK = 3;

	public CourseObject(int index, String objectText, String objectChinese) {
		this.setmIndex(index);
		this.setmObjectText(objectText);
		this.setmObjectChinese(objectChinese);
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

	public String getmObjectChinese() {
		return mObjectChinese;
	}

	public void setmObjectChinese(String mObjectChinese) {
		this.mObjectChinese = mObjectChinese;
	}

	public Bitmap getmObjectBitmap(SFEnglishApp app, int reqWidth, int reqHeight) {
		int resId = SFResourseManager.courseObjectBitmapId(mObjectText);
		this.mObjectBitmap = app.getmBitmapManager().getBitmap(resId, reqWidth, reqHeight);
					//BitmapFactory.decodeResource(context.getResources(), resId);
		return mObjectBitmap;
	}

	public int getmCorrectTimes() {
		return mCorrectTimes;
	}

	public void setmCorrectTimes(int mCorrectTimes) {
		this.mCorrectTimes = mCorrectTimes;
		if (this.mCorrectTimes>=CourseObject.CORRECT_TIMES_TO_UNLOCK) {
			this.mCorrectTimes = CourseObject.CORRECT_TIMES_TO_UNLOCK;
		}
	}

	public boolean isUnLocked() {
		if (this.getmCorrectTimes() >= SFGlobal.CORRECT_TIMES_TO_UNLOCK_OBJECT) {
			return true;
		} else {
			return false;
		}
	}
}
