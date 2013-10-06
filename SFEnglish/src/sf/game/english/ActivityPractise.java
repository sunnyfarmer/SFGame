package sf.game.english;

import sf.game.english.model.CourseObject;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityPractise extends TopActivity {
	public static final String TAG = "ActivityPractise";

	protected Button btnBack = null;
	protected TextView tvCourseObject = null;
	protected ImageView ivCourseObject = null;
	protected Button btnListen = null;
	protected Button btnSay = null;

	protected CourseObject mCourseObject = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_practice);
	    super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.refreshView();
	}

	@Override
	protected void initView() {
		super.initView();

		this.btnBack = (Button) this.findViewById(R.id.btnBack);
		this.tvCourseObject = (TextView) this.findViewById(R.id.tvCourseObject);
		this.ivCourseObject = (ImageView) this.findViewById(R.id.ivCourseObject);
		this.btnListen = (Button) this.findViewById(R.id.btnListen);
		this.btnSay = (Button) this.findViewById(R.id.btnSay);
	}

	protected void refreshView() {
		this.mCourseObject = this.mApp.getmStorageManager().getmSelectedCourse().getmSelectedCourseObject();
		this.tvCourseObject.setText(this.mCourseObject.getmObjectText());
		this.ivCourseObject.setImageBitmap(this.mCourseObject.getmObjectBitmap(this.mApp));
	}
}
