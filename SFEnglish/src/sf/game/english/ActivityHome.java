package sf.game.english;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityHome extends TopActivity {
	public static final String TAG = "ActivityHome";

	protected Button btnPrevious = null;
	protected Button btnNext = null;
	protected ImageView ivCourse = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}
	@Override
	protected void initView() {
		super.initView();
		this.btnPrevious = (Button) this.findViewById(R.id.btnPrevious);
		this.ivCourse = (ImageView) this.findViewById(R.id.ivCourse);
		this.btnNext = (Button) this.findViewById(R.id.btnNext);
	}
	@Override
	protected void setListener() {
		super.setListener();
		this.btnPrevious.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.btn_un_clickable);
				v.startAnimation(animation);
			}
		});
		this.btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.btn_un_clickable);
				v.startAnimation(animation);
			}
		});
		this.ivCourse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.iv_go);
				v.startAnimation(animation);
			}
		});
	}
}
