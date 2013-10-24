package sf.game.english.utils;

import java.util.ArrayList;
import java.util.LinkedList;

import sf.game.english.SFEnglishApp;
import sf.game.english.model.Course;
import sf.game.english.model.CourseObject;
import sf.game.english.utils.SFResourseManager.TCOURSE;
import sf.libs.log.SFLog;

public class SFStorageManager {
	public static final String TAG = "SFStorageManager";

	protected ArrayList<Course> mCourseArray = null;
	protected Course mSelectedCourse = null;

	protected SFEnglishApp mApp = null;

	public SFStorageManager(SFEnglishApp app) {
		this.mApp = app;
	}

	/**
	 * 课程数组
	 * @return
	 */
	public ArrayList<Course> getCourseArray() {
		if (this.mCourseArray==null) {
			// TODO 从本地存储中取
	
			// TODO 否则，初始化数据
			this.mCourseArray = this.getInitCourseArray();
			this.mSelectedCourse = this.mCourseArray.get(0);
		}
		return this.mCourseArray;
	}
	/**
	 * 初始化课程数组
	 * @return
	 */
	protected ArrayList<Course> getInitCourseArray() {
		ArrayList<Course> courseArray = new ArrayList<Course>();
		TCOURSE[] tcourses = TCOURSE.values();
		for (TCOURSE tcourse : tcourses) {
			int resIdOfCourseString = SFResourseManager.courseStringId(tcourse);
			String courseString = this.mApp.getString(resIdOfCourseString);
			Course course = new Course(tcourse, courseString);

			int resIdOfCourseObjectArray = SFResourseManager
					.courseObjectArrayId(tcourse);
			String[] courseObjectStringArray = this.mApp.getResources()
					.getStringArray(resIdOfCourseObjectArray);
			LinkedList<CourseObject> courseObjectArray = new LinkedList<CourseObject>();
			for (int cot = 0; cot < courseObjectStringArray.length; cot++) {
				String courseObjectString = courseObjectStringArray[cot];
				CourseObject courseObject = new CourseObject(cot,
						courseObjectString);
				courseObjectArray.add(courseObject);
			}
			course.setmCourseObjectArray(courseObjectArray);

			courseArray.add(course);
		}
		return courseArray;
	}
	public Course getmSelectedCourse() {
		if (this.mSelectedCourse==null) {
			this.mSelectedCourse = this.getCourseArray().get(0);
		}
		return mSelectedCourse;
	}
	public boolean moveToNextCourse() {
		int index = this.getCourseArray().indexOf(this.mSelectedCourse);
		if (-1==index) {
			this.mSelectedCourse = this.getCourseArray().get(0);
			return true;
		} else if ((this.getCourseArray().size()-1)==index) {
			return false;
		} else {
			this.mSelectedCourse = this.getCourseArray().get(index+1);
			return true;
		}
	}
	public boolean moveToPreviousCourse() {
		int index = this.getCourseArray().indexOf(this.mSelectedCourse);
		if (-1==index) {
			this.mSelectedCourse = this.getCourseArray().get(0);
			return true;
		} else if (0==index) {
			return false;
		} else {
			this.mSelectedCourse = this.getCourseArray().get(index-1);
			return true;
		}
	}
}
