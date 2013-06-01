package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import sf.util.SFFloatPoint;
import sf.util.SFLogger;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends SFElement{
	public static final String TAG = "Background";

	private Bitmap bitmapBackground = null;
	private Ham ham = null;
	private Timebar timebar = null;

	@SuppressLint("NewApi")
	public Background(Context context) {
		super(context);
		this.bitmapBackground = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.background);
	}

	public Bitmap getBitmapBackground() {
		return this.bitmapBackground;
	}
	public Ham getHam() {
		if (this.ham==null) {
			this.ham = new Ham(context);
		}
		return this.ham;
	}
	public Timebar getTimebar() {
		if (this.timebar==null) {
			this.timebar = new Timebar(this.context);
		}
		return this.timebar;
	}

	@Override
	public void display(Canvas canvas){
		try {
			throw new Exception("弃用了");
		} catch (Exception e) {
			SFLogger.e(TAG, "Background.display(Canvas canvas)已经弃用", e);
		}
	}

	public Bitmap getBackgroundCopy() {
		Bitmap bitmapCopy = this.bitmapBackground.copy(Bitmap.Config.ARGB_8888, true);
		return bitmapCopy;
	}

	public void display(Canvas canvas, Bitmap backgroundCopy) {
		Canvas canvasOfBackground = new Canvas(backgroundCopy);
		//锤子
		float xScale = backgroundCopy.getWidth() / (1.0f * this.contextWidth);
		float yScale = backgroundCopy.getHeight() / (1.0f * this.contextHeight);
		this.getHam().display(canvasOfBackground, xScale, yScale);

		//时间栏
		this.getTimebar().display(canvasOfBackground, 50, 20);

		//将背景渲染到屏幕
		Rect srcRect = new Rect(0, 0, backgroundCopy.getWidth(), backgroundCopy.getHeight());
		Rect dstRect = new Rect(0, 0, this.contextWidth, this.contextHeight);
		canvas.drawBitmap(backgroundCopy, srcRect, dstRect, paint);		
	}

	/**
	 * 将屏幕坐标转化为Background坐标
	 * @param screenPoint
	 * @return
	 */
	public SFFloatPoint screenToBackgroundPoint(SFFloatPoint screenPoint) {
		// 
		SFFloatPoint backgroundPoint = new SFFloatPoint();
		float xScale = this.bitmapBackground.getWidth() / (1.0f * this.contextWidth);
		float yScale = this.bitmapBackground.getHeight() / (1.0f * this.contextHeight);

		backgroundPoint.x = screenPoint.x * xScale;
		backgroundPoint.y = screenPoint.y * yScale;

		return backgroundPoint;
	}
}
