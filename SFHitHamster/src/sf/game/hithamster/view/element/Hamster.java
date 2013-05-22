package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Hamster extends SFElement {
	private Bitmap bitmapHamster = null;
	private float x = 0.0f;
	private float y = 0.0f;

	private int step = 0;
	private float stepPixel = 10.0f;
	private int maxStep = 0;

	public Hamster(Context context) {
		super(context);
		this.bitmapHamster = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hamster);

		this.maxStep = (int) (this.bitmapHamster.getHeight() / this.stepPixel);
		this.maxStep *= 2;
	}

	@Override
	public void display(Canvas canvas) {
//		int[] bytes = 
//		bitmapCopy.getPixels(pixels, offset, stride, x, y, step, maxStep)

		//计算上移的步数
		if (step > maxStep) {
			step = 0;
		}
		step++;

//		//获得要画上去的bitmap
//		int displayHeight = (int) (step * stepPixel);
//		int displayWidth = this.bitmapHamster.getWidth();
//		int[] pixels = new int[displayWidth*displayHeight];
//		this.bitmapHamster.getPixels(pixels, 0, displayWidth, 0, 0, displayWidth, displayHeight);
//
//		for (int xIndex = 0; xIndex < displayHeight; xIndex++) {
//			for (int yIndex = 0; yIndex < displayWidth; yIndex++) {
//				int pixelIndex = xIndex*displayWidth + yIndex;
//				int pixel = pixels[pixelIndex];
//				
//			}
//		}
		Matrix matrix = new Matrix();
		float xOffset = this.x;
		float yOffset = this.y-step*stepPixel;
		if (yOffset<=0.0f) {
			yOffset = 0.0f;
		}
		matrix.postTranslate(xOffset, yOffset);
		canvas.drawBitmap(this.bitmapHamster, matrix, paint);
	}

	public void display(Canvas canvas, float x, float y) {
		this.x = x;
		this.y = y;
		this.display(canvas);
	}
	public int getWidth() {
		return this.bitmapHamster.getWidth();
	}
	public int getHeight() {
		return this.bitmapHamster.getHeight();
	}
}
