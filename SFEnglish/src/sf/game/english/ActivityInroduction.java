package sf.game.english;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import sf.libs.log.SFLog;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInroduction extends TopActivity {
	public static final String TAG = "ActivityIntroduction";

	protected Button btnBack = null;
	protected ImageView ivLifeA = null;
	protected ImageView ivLifeB = null;
	protected TextView tvDescription = null;

	private ArrayList<Integer> mResIdArray = new ArrayList<Integer>();
	private int mDisplayingIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_introduction);
	    super.onCreate(savedInstanceState);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			SFLog.d(TAG, "schedule");
			switchImageView();
		};
	};

	@Override
	protected void onResume() {
		super.onResume();

		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				mHandler.sendEmptyMessage(1);
			}
		};
		timer.schedule(tt, 0, 4000);
		this.ivLifeB.setImageResource(this.currentResId());
	}

	@Override
	protected void initData() {
		super.initData();
		this.mResIdArray = new ArrayList<Integer>();
		this.mResIdArray.add(R.drawable.tiger);
		this.mResIdArray.add(R.drawable.bear);
		this.mResIdArray.add(R.drawable.bat);
	}

	@Override
	protected void initView() {
		super.initView();
		this.btnBack = (Button) this.findViewById(R.id.btnBack);
		this.ivLifeA = (ImageView) this.findViewById(R.id.ivLifeA);
		this.ivLifeB = (ImageView) this.findViewById(R.id.ivLifeB);
		this.tvDescription = (TextView) this.findViewById(R.id.tvDescription);
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
	}

	private void switchImageView() {
		if (this.mResIdArray==null || this.mResIdArray.size()==0) {
			return;
		}
		ImageView ivATemp = null; // invisible ImageView
		ImageView ivBTemp = null; // visible ImageView
		if (this.ivLifeA.getVisibility() == View.VISIBLE) {
			ivATemp = this.ivLifeB;
			ivBTemp = this.ivLifeA;
		} else {
			ivATemp = this.ivLifeA;
			ivBTemp = this.ivLifeB;
		}
		final ImageView ivA = ivATemp;
		final ImageView ivB = ivBTemp;
		ivA.setVisibility(View.VISIBLE);
		ivB.setVisibility(View.VISIBLE);
		ivA.setImageResource(this.nextResId());
		ivB.setImageResource(this.currentResId());
		
		Animation animationA = AnimationUtils.loadAnimation(ActivityInroduction.this, android.R.anim.fade_in);
		animationA.setDuration(2000);
		ivA.startAnimation(animationA);
		Animation animationB = AnimationUtils.loadAnimation(ActivityInroduction.this, android.R.anim.fade_out);
		animationB.setDuration(2000);
		animationB.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				ivB.setVisibility(View.INVISIBLE);
				mDisplayingIndex = nextIndex();
			}
		});
		ivB.startAnimation(animationB);
	}
	private int nextIndex(){ 
		int nextIndex = this.mDisplayingIndex+1;
		nextIndex = nextIndex % this.mResIdArray.size();
		return nextIndex;
	}
	private int nextResId() {
		int nextIndex = this.nextIndex();
		return this.mResIdArray.get(nextIndex);
	}
	private int currentResId() {
		return this.mResIdArray.get(this.mDisplayingIndex);
	}
}
