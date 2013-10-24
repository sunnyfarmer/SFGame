package sf.game.english.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import sf.game.english.SFEnglishApp;
import sf.game.english.utils.SFGlobal;
import sf.game.english.utils.SFResourseManager;
import sf.game.english.utils.SFResourseManager.TCOURSE;

import android.graphics.Bitmap;

public class Course {
	public static final String TAG = "Course";

	private TCOURSE mTCourse = null;
	private String mCourseName = null;
	private Bitmap mCourseBitmapH = null;
	private Bitmap mCourseBitmapV = null;
	private LinkedList<CourseObject> mCourseObjectArray = null;
	private CourseObject mSelectedCourseObject = null;

	public Course(TCOURSE tcourse, String courseName) {
		this.setmTCourse(tcourse);
		this.setmCourseName(courseName);
	}

	public TCOURSE getmTCourse() {
		return mTCourse;
	}

	public void setmTCourse(TCOURSE mTCourse) {
		this.mTCourse = mTCourse;
	}

	public String getmCourseName() {
		return mCourseName;
	}

	public void setmCourseName(String mCourseName) {
		this.mCourseName = mCourseName;
	}

	public void setmCourseObjectArray(LinkedList<CourseObject> mCourseObjectArray) {
		this.mCourseObjectArray = mCourseObjectArray;
		Collections.sort(this.mCourseObjectArray, new Comparator<CourseObject>() {
			@Override
			public int compare(CourseObject lhs, CourseObject rhs) {
				if (lhs.getmIndex() > rhs.getmIndex()) {
					return 1;
				} else if (lhs.getmIndex() < rhs.getmIndex()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	public LinkedList<CourseObject> getmCourseObjectArray() {
		return mCourseObjectArray;
	}

	public Bitmap getmCourseBitmapH(SFEnglishApp app) {
		//TODO
		if (this.mCourseBitmapH==null) {
			int resId = SFResourseManager.courseImageId(mTCourse, false);
			this.mCourseBitmapH = app.getmBitmapManager().getBitmap(resId, 1024, 1024);
					//BitmapFactory.decodeResource(context.getResources(), resId);
		}
		return mCourseBitmapH;
	}

	public Bitmap getmCourseBitmapV(SFEnglishApp app) {
		//TODO 
		if (this.mCourseBitmapV==null) {
			int resId = SFResourseManager.courseImageId(mTCourse, true);
			this.mCourseBitmapV = app.getmBitmapManager().getBitmap(resId, 1024, 1024);
					//BitmapFactory.decodeResource(context.getResources(), resId);
		}
		return mCourseBitmapV;
	}

	public CourseObject getmSelectedCourseObject() {
		if (this.mSelectedCourseObject==null && this.getmCourseObjectArray()!=null && this.getmCourseObjectArray().size()>0) {
			this.mSelectedCourseObject = this.getmCourseObjectArray().getFirst();
		}
		return mSelectedCourseObject;
	}

	public void setmSelectedCourseObject(CourseObject mSelectedCourseObject) {
		this.mSelectedCourseObject = mSelectedCourseObject;
	}

	public boolean moveToNextCourseObject() {
		if (this.mSelectedCourseObject.getmCorrectTimes() < SFGlobal.CORRECT_TIMES_TO_UNLOCK_OBJECT) {
			//这个Object尚未完全解锁
			return false;
		}
		int indexOfCourseObject = this.mCourseObjectArray.indexOf(mSelectedCourseObject);
		int nextIndex = indexOfCourseObject;
		if (indexOfCourseObject==(this.mCourseObjectArray.size()-1)) {
			nextIndex = 0;
		} else {
			nextIndex++;
		}
		this.setmSelectedCourseObject(this.mCourseObjectArray.get(nextIndex));
		return true;
	}
	public boolean moveToPreviousCourseObject() {
		int indexOfCourseObject = this.mCourseObjectArray.indexOf(this.mSelectedCourseObject);
		int nextIndex = indexOfCourseObject;
		if (indexOfCourseObject == 0) {
			CourseObject lastCourseObject = this.getmCourseObjectArray().getLast();
			if (!lastCourseObject.isUnLocked()) {
				//最后一个Object尚未解锁，不能倒着翻回去
				return false;
			}
			nextIndex = this.getmCourseObjectArray().size()-1;
		} else {
			nextIndex--;
		}
		this.setmSelectedCourseObject(this.mCourseObjectArray.get(nextIndex));
		return true;
	}
}
