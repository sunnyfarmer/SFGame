package sf.game.hithamster;

import java.util.Timer;
import java.util.TimerTask;

import sf.game.hithamster.audio.GameSound;
import sf.game.hithamster.view.HithamsterView;
import sf.util.SFAdvertisement;
import sf.util.SFLogger;
import android.app.Activity;
import android.os.Bundle;

public class HithamsterActivity extends Activity {
	public static final String TAG = "HithamsterActivity";
    private HithamsterView surfaceView = null;

    private GameSound gameSound = null;

    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		SFLogger.d(TAG, "onCreate");
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_hithamster);

	    this.findView();
	    this.setListener();

	    this.gameSound = new GameSound(this);
	}
	@Override
	protected void onResume() {
		super.onResume();

		this.gameSound.play();
		this.beginAdTimer();
	}
	@Override
	protected void onPause() {
		super.onPause();

		this.gameSound.pause();
		this.surfaceView.setRenderState(HithamsterView.RENDER_STATE.RENDER_STATE_STOP);
		this.stopAdTimer();
	}
	@Override
	protected void onStop() {
		super.onStop();

		this.gameSound.release();
		this.surfaceView.setRenderState(HithamsterView.RENDER_STATE.RENDER_STATE_STOP);
	}
	private void findView() {
		this.surfaceView = (HithamsterView) this.findViewById(R.id.hithamsterView);
	}

	private void setListener() {
		this.surfaceView.setOnTouchListener(this.surfaceView);
	}

	private void beginAdTimer() {
		this.stopAdTimer();
		if (this.mTimer==null) {
			this.mTimer = new Timer();
			this.mTimerTask = new TimerTask() {
				@Override
				public void run() {
					SFAdvertisement.showSmartBanner(HithamsterActivity.this);
				}
			};
			this.mTimer.schedule(mTimerTask, 10000);
		}
	}
	private void stopAdTimer() {
		if (this.mTimer!=null) {
			this.mTimer.cancel();
			this.mTimer = null;
		}
	}
}