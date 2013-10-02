package sf.game.english.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Course {
	public static final String TAG = "Course";

	private String mCourseName = null;
	private Bitmap mCourseBitmap = null;
	private ArrayList<CourseObject> mCourseObjectArray = null;

	public String getmCourseName() {
		return mCourseName;
	}

	public void setmCourseName(String mCourseName) {
		this.mCourseName = mCourseName;
	}

	public Bitmap getmCourseBitmap() {
		return mCourseBitmap;
	}

	public void setmCourseBitmap(Bitmap mCourseBitmap) {
		this.mCourseBitmap = mCourseBitmap;
	}

	public void setmCourseObjectArray(ArrayList<CourseObject> mCourseObjectArray) {
		this.mCourseObjectArray = mCourseObjectArray;
	}

	public ArrayList<CourseObject> getmCourseObjectArray() {
		return mCourseObjectArray;
	}
}
