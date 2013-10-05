package sf.game.english.model;

import java.util.ArrayList;

import sf.game.english.SFEnglishApp;
import sf.game.english.utils.SFResourseManager;
import sf.game.english.utils.SFResourseManager.TCOURSE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Course {
	public static final String TAG = "Course";

	private TCOURSE mTCourse = null;
	private String mCourseName = null;
	private Bitmap mCourseBitmapH = null;
	private Bitmap mCourseBitmapV = null;
	private ArrayList<CourseObject> mCourseObjectArray = null;

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

	public void setmCourseObjectArray(ArrayList<CourseObject> mCourseObjectArray) {
		this.mCourseObjectArray = mCourseObjectArray;
	}

	public ArrayList<CourseObject> getmCourseObjectArray() {
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
}
