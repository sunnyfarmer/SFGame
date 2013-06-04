package sf.game.hithamster.view;

import sf.game.hithamster.R;
import sf.game.hithamster.model.GameController;
import sf.util.SFLogger;
import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class HithamsterView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener{
	public static final String TAG = "HithamsterView";
	public static enum RENDER_STATE {
		RENDER_STATE_START,
		RENDER_STATE_STOP
	};
	private RENDER_STATE renderState = RENDER_STATE.RENDER_STATE_STOP;
	private HithamsterRenderThread renderThread = null;

	private GameController gc = null;

	//Test
	private MediaPlayer mp = null;

	public HithamsterView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);

		this.gc = new GameController(context);

		mp = MediaPlayer.create(this.getContext(), R.raw.viva_la_vida);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		SFLogger.d(TAG, "surfaceCreated");
		this.renderState = RENDER_STATE.RENDER_STATE_START;
		this.renderThread().start();

		mp.start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		SFLogger.d(TAG, "surfaceChanged");
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		SFLogger.d(TAG, "surfaceDestroyed");
		this.renderState = RENDER_STATE.RENDER_STATE_STOP;
	}

	public HithamsterRenderThread renderThread() {
		if (this.renderThread==null) {
			this.renderThread = new HithamsterRenderThread(this.getHolder(), this.getContext());
		}
		return this.renderThread;
	}

	//游戏线程
	private class HithamsterRenderThread extends Thread {
		private SurfaceHolder holder = null;
		private Context context = null;
		private int fps = 0;
		private long fpsTime = 0;
		public HithamsterRenderThread(SurfaceHolder holder, Context context) {
			this.holder = holder;
			this.context = context;
		}
		@Override
		public void run() {
			super.run();
			while (renderState==RENDER_STATE.RENDER_STATE_START) {

				//循环播放 音乐
				int length = mp.getDuration();
				if (mp.getCurrentPosition() == length-10) {
					mp.seekTo(0);
				}
				SFLogger.i(TAG, String.format("music : %d, %d", mp.getCurrentPosition(), length));
				
				Canvas canvas = this.holder.lockCanvas();

				//控制游戏流程
				HithamsterView.this.gc.gameProcess();
				//控制游戏渲染
				HithamsterView.this.gc.render(canvas);

				this.holder.unlockCanvasAndPost(canvas);

				//计算fps
				this.calculateFps();
			}
		}
		private void calculateFps() {
			java.util.Date d = new java.util.Date();
			if (d.getTime() - fpsTime > 1000) {
				this.fpsTime = d.getTime();
				SFLogger.i(TAG, String.format("fps : %d", fps));
				this.fps = 0;
			} else {
				this.fps++;
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return HithamsterView.this.gc.onTouch(v, event);
	}
}
