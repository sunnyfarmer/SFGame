package sf.game.english;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_introduction);
	    super.onCreate(savedInstanceState);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Animation animationA = AnimationUtils.loadAnimation(ActivityInroduction.this, android.R.anim.slide_in_left);
			ivLifeA.startAnimation(animationA);
			Animation animationB = AnimationUtils.loadAnimation(ActivityInroduction.this, android.R.anim.slide_out_right);
			ivLifeB.startAnimation(animationB);		
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
		timer.schedule(tt, 2000);
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
}
