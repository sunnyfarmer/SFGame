package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

public class Timebar extends SFElement {
	public static final String TAG = "Timerbar";

	private Bitmap bitmapTimebarBg = null;
	private Bitmap bitmapTimebarApple = null;
	private Bitmap bitmapTimebarBgBlackbar = null;
	private Bitmap bitmapTimebarBgGreenbar = null;
	private float x = 0.0f;
	private float y = 0.0f;

	private int percent = 90; // from 0 to 100
	private static final int MAX_PERCENT = 100;

	public Timebar(Context context) {
		super(context);
		this.bitmapTimebarBg = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.timebar_bg);
		this.bitmapTimebarApple = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.timebar_apple);
		this.bitmapTimebarBgBlackbar = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.timebar_bg_blackbar);
		this.bitmapTimebarBgGreenbar = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.timebar_bg_greenbar);
	}

	public void setPercent(float percent) {
		this.percent = (int)(MAX_PERCENT * percent);
	}

	@Override
	public void display(Canvas canvas) {
		percent ++;
		if (percent>100) 
			percent = 0;

		Bitmap bgCopy = this.bitmapTimebarBg.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvasOfBg = new Canvas(bgCopy);

		//渲染timebar black bar
		Bitmap blackbarCopy = this.bitmapTimebarBgBlackbar.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvasOfBlackbar = new Canvas(blackbarCopy);
		//渲染timebar green bar
		Matrix matrixOfGreenbar = new Matrix();
		matrixOfGreenbar.setTranslate(-this.bitmapTimebarBgGreenbar.getWidth() * (MAX_PERCENT-percent) / MAX_PERCENT, 0);
		canvasOfBlackbar.drawBitmap(this.bitmapTimebarBgGreenbar, matrixOfGreenbar, paint);
		Matrix matrixOfBlackbar = new Matrix();
		matrixOfBlackbar.setTranslate(20, 30);
		canvasOfBg.drawBitmap(blackbarCopy, matrixOfBlackbar, paint);

		//渲染timebar apple
		Matrix matrixOfApple = new Matrix();
		matrixOfApple.setTranslate( (bgCopy.getWidth()-this.bitmapTimebarApple.getWidth()) * percent / MAX_PERCENT, 0);
		canvasOfBg.drawBitmap(bitmapTimebarApple, matrixOfApple, paint);

		//渲染timebar
		Matrix matrix = new Matrix();
		matrix.postTranslate(this.x, this.y);
		canvas.drawBitmap(bgCopy, matrix, paint);
	}
	public void display(Canvas canvas, float x, float y) {
		this.x = x;
		this.y = y;
		this.display(canvas);
	}
}
