package sf.game.english;

import java.util.ArrayList;

import sf.game.english.adapter.AdapterCourse;
import sf.game.english.model.CourseObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

public class ActivityCourse extends TopActivity {
	public static final String TAG = "ActivityCourse";

	protected ImageView ivCourse = null;
	protected GridView gvObject = null;

	protected AdapterCourse mAdapterCourse = null;
	protected ArrayList<CourseObject> mCourseObjectArray = new ArrayList<CourseObject>();

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

		this.ivCourse = (ImageView) this.findViewById(R.id.ivCourse);
		this.gvObject = (GridView) this.findViewById(R.id.gvObject);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub
		super.setListener();
	}

	private void refreshData() {
		for (int cot = 0; cot < 10; cot++) {
			CourseObject co = new CourseObject();
			co.setmObjectTitle("title" + cot);
			co.setmObjectText("text" + cot);
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			co.setmObjectBitmap(bitmap);
			this.mCourseObjectArray.add(co);
		}
		this.mAdapterCourse = new AdapterCourse(this, mCourseObjectArray);
		this.gvObject.setAdapter(mAdapterCourse);
		this.gvObject.setOnItemClickListener(mAdapterCourse);
	}
}
