package sf.game.hithamster.view;

import sf.game.hithamster.model.GameController;
import sf.game.hithamster.view.element.Background;
import sf.util.SFLogger;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class HithamsterView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener{
	public static final String TAG = "HithamsterView";
	public static enum RENDER_STATE {
		RENDER_STATE_START,
		RENDER_STATE_STOP
	};
	private RENDER_STATE renderState = RENDER_STATE.RENDER_STATE_STOP;
	private HithamsterRenderThread renderThread = null;

	private GameController gc = null;

	public HithamsterView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);

		this.gc = new GameController(context);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		SFLogger.d(TAG, "surfaceCreated");
		this.renderState = RENDER_STATE.RENDER_STATE_START;
		this.renderThread().start();
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

	//刷新线程
	private class HithamsterRenderThread extends Thread {
		private SurfaceHolder holder = null;
		private Context context = null;
		public HithamsterRenderThread(SurfaceHolder holder, Context context) {
			this.holder = holder;
			this.context = context;
		}
		@Override
		public void run() {
			super.run();
			while (renderState==RENDER_STATE.RENDER_STATE_START) {
				Canvas canvas = this.holder.lockCanvas();

				HithamsterView.this.gc.gameProcess();
				HithamsterView.this.gc.render(canvas);

				this.holder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					SFLogger.e(TAG, e.getMessage(), e);
				}
			}
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return HithamsterView.this.gc.onTouch(v, event);
	}
}
