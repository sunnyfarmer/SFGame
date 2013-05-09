package sf.game.hithamster.view;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class HithamsterView extends SurfaceView implements SurfaceHolder.Callback{
	public static final String TAG = "HithamsterView";
	public static enum RENDER_STATE {
		RENDER_STATE_START,
		RENDER_STATE_STOP
	}; 
	private RENDER_STATE renderState = RENDER_STATE.RENDER_STATE_STOP;
	private HithamsterRenderThread renderThread = null;

	public HithamsterView(Context context) {
		super(context);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "surfaceCreated");
		this.renderState = RENDER_STATE.RENDER_STATE_START;
		this.renderThread().start();
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "surfaceChanged");
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed");
		this.renderState = RENDER_STATE.RENDER_STATE_STOP;
	}

	public HithamsterRenderThread renderThread() {
		if (this.renderThread==null) {
			this.renderThread = new HithamsterRenderThread();
		}
		return this.renderThread;
	}

	private class HithamsterRenderThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (renderState==RENDER_STATE.RENDER_STATE_START) {
				java.util.Date dt = new java.util.Date();
				Log.d(TAG, String.format("%l", dt.getTime()));
			}
		}
	}
}
