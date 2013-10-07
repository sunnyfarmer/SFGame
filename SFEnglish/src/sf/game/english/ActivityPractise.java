package sf.game.english;

import sf.game.english.model.CourseObject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
	    this.mApp.initTTS();
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

	@Override
	protected void setListener() {
		super.setListener();
		this.btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		this.ivCourseObject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toActivity(ActivityInroduction.class);
			}
		});
		this.btnListen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mApp.ttsSpeak(mCourseObject.getmObjectText());
			}
		});
		this.btnSay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	protected void refreshView() {
		this.mCourseObject = this.mApp.getmStorageManager().getmSelectedCourse().getmSelectedCourseObject();
		this.tvCourseObject.setText(this.mCourseObject.getmObjectText());
		this.ivCourseObject.setImageBitmap(this.mCourseObject.getmObjectBitmap(this.mApp));
	}
}
