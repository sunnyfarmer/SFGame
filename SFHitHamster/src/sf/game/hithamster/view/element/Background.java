package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import sf.util.SFFloatPoint;
import sf.util.SFLogger;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.widget.Toast;

public class Background extends SFElement{
	public static final String TAG = "Background";

	private Bitmap bitmapGameover = null;
	private Bitmap bitmapProgress = null;
	private Bitmap bitmapLife = null;
	private Bitmap bitmapBackground = null;
	private Ham ham = null;
	private Timebar timebar = null;
	public int missChance = 6;
	public int hamsterHitNumber = 0;
	public boolean isGameOver = false;
	public boolean isGameUpgrading = false;//游戏正处于升级阶段
	public int upgradeProcess = 0;
	public int gameLevel = 0;


	@SuppressLint("NewApi")
	public Background(Context context) {
		super(context);
		this.bitmapGameover = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.gameover);
		this.bitmapProgress = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.progress);
		this.bitmapLife = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.life);
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

		//画上红心
		for (int cot = 0; cot < this.missChance; cot++) {
			canvasOfBackground.drawBitmap(this.bitmapLife, 410+cot*64, 30, this.paint);			
		}

		//画上等级
		this.paint.setARGB(255, 255, 0, 0);
		this.paint.setTextSize(50);
		this.paint.setTypeface(this.tp);
		canvasOfBackground.drawText(String.valueOf(this.gameLevel), 5, 40, this.paint);

		if (isGameOver) {
			Matrix matrix = new Matrix();
			//scale (1.0~2.0) 2 second
			float step = System.currentTimeMillis()%500*1.0f/500.0f;
			float scale = (float) (0.8 + 0.2*step);
			matrix.setScale(1.0f, scale);
			canvasOfBackground.drawBitmap(bitmapGameover, matrix, this.paint);
		}
		if (this.isGameUpgrading) {
			Matrix matrix = new Matrix();
			//width (-100~100) 2 second
			float step = System.currentTimeMillis()%2000*1.0f/2000.0f;
			int xOffset = (int) (100 - 200*step);
			matrix.setTranslate(xOffset, 0);
			canvasOfBackground.drawBitmap(this.bitmapProgress, matrix, this.paint);
		}

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
