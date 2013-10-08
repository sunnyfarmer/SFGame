package sf.game.english;

import sf.libs.log.SFLog;
import sf.libs.view.listener.OnGestureListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityHome extends TopActivity {
	public static final String TAG = "ActivityHome";

	protected Button btnPrevious = null;
	protected Button btnNext = null;
	protected ImageView ivCourse = null;
	protected LinearLayout llHome = null;

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
		this.llHome = (LinearLayout) this.findViewById(R.id.llHome);
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
				boolean res = mApp.getmStorageManager().moveToPreviousCourse();
				if (res) {
					refreshView();
				} else {
					Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.btn_un_clickable);
					v.startAnimation(animation);
				}
			}
		});
		this.btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean res = mApp.getmStorageManager().moveToNextCourse();
				if (res) {
					refreshView();
				} else {
					Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.btn_un_clickable);
					v.startAnimation(animation);
				}
			}
		});
		this.ivCourse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation animation = AnimationUtils.loadAnimation(ActivityHome.this, R.anim.iv_go);
				animation.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(Animation animation) {
						SFLog.d(TAG, "onAnimationStart");
					}
					@Override
					public void onAnimationRepeat(Animation animation) {
						SFLog.d(TAG, "onAnimationRepeat");
					}
					@Override
					public void onAnimationEnd(Animation animation) {
						SFLog.d(TAG, "onAnimationEnd");
						toActivity(ActivityCourse.class);
					}
				});
				v.startAnimation(animation);
			}
		});
		this.llHome.setOnTouchListener(new OnGestureListener(this) {
			@Override
			public boolean onSwipeLeft() {
				btnNext.performClick();
				return true;
			}
			@Override
			public boolean onSwipeRight() {
				btnPrevious.performClick();
				return true;
			}
		});
		
	}
	protected void refreshView() {
		this.ivCourse.setImageBitmap(this.mApp.getmStorageManager().getmSelectedCourse().getmCourseBitmapV(this.mApp));
	}
}
