package sf.game.english;

import java.util.LinkedList;

import sf.game.english.adapter.AdapterCourse;
import sf.game.english.model.CourseObject;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class ActivityCourse extends TopActivity {
	public static final String TAG = "ActivityCourse";

	protected Button btnBack = null;
	protected ImageView ivCourse = null;
	protected GridView gvObject = null;

	protected AdapterCourse mAdapterCourse = null;
	protected LinkedList<CourseObject> mCourseObjectArray = new LinkedList<CourseObject>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_course);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.refreshData();
	}

	@Override
	protected void initData() {
		super.initData();
	}

	@Override
	protected void initView() {
		super.initView();

		this.btnBack = (Button) this.findViewById(R.id.btnBack);
		this.ivCourse = (ImageView) this.findViewById(R.id.ivCourse);
		this.gvObject = (GridView) this.findViewById(R.id.gvObject);

		this.ivCourse.setImageBitmap(this.mApp.getmStorageManager().getmSelectedCourse().getmCourseBitmapH(this.mApp));
	}

	@Override
	protected void setListener() {
		super.setListener();
	}

	private void refreshData() {
		this.mCourseObjectArray = this.mApp.getmStorageManager().getmSelectedCourse().getmCourseObjectArray();
		this.mAdapterCourse = new AdapterCourse(this, mCourseObjectArray);
		this.gvObject.setAdapter(mAdapterCourse);
		this.gvObject.setOnItemClickListener(mAdapterCourse);
	}
}
