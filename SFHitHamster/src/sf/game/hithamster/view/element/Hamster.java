package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import sf.game.hithamster.model.ModelHamster;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Hamster extends SFElement {
	private Bitmap bitmapHamster = null;
	private Bitmap bitmapHamsterWound = null;
	private float x = 0.0f;
	private float y = 0.0f;

	private int step = 0;
	private float stepPixel = 25.0f;

	private ModelHamster modelHamster = null;

	public Hamster(Context context, ModelHamster modelHamster) {
		super(context);
		this.bitmapHamster = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hamster);
		this.bitmapHamsterWound = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hittedhamster);

		this.modelHamster = modelHamster;
	}

	@Override
	public void display(Canvas canvas) {
		Matrix matrix = new Matrix();
		float xOffset = this.x;
		float yOffset = this.y;

		this.paint.setARGB(255, 255, 255, 255);
		this.paint.setTextSize(50);
		this.paint.setTypeface(this.tp);
		canvas.drawText(String.valueOf(this.modelHamster.life), this.x-10, this.y, this.paint);

		switch(this.modelHamster.getState()) {
		case HIDING:
			//计算上移的步数
			step++;
			matrix = new Matrix();
			xOffset = this.x;
			yOffset = step*stepPixel;
			if (yOffset>=this.y) {
				yOffset = this.y;
				this.modelHamster.leave();
			}
			matrix.postTranslate(xOffset, yOffset);
			canvas.drawBitmap(this.bitmapHamster, matrix, paint);
			break;
		case JUMPING:
			//计算上移的步数
			step++;
			matrix = new Matrix();
			xOffset = this.x;
			yOffset = this.y-step*stepPixel;
			if (yOffset<=0.0f) {
				yOffset = 0.0f;
				this.modelHamster.laugh();
			}
			matrix.postTranslate(xOffset, yOffset);
			canvas.drawBitmap(this.bitmapHamster, matrix, paint);
			break;
		case LAUGHING:
			step=0;
			yOffset = 0.0f;
			matrix.postTranslate(xOffset, yOffset);
			canvas.drawBitmap(this.bitmapHamster, matrix, paint);
			break;
		case WOUND:
		case DEAD:
			step=0;
			yOffset = -10.0f;
			matrix.postTranslate(xOffset, yOffset);
			canvas.drawBitmap(this.bitmapHamsterWound, matrix, paint);
			break;
		case HIDED:
			step=0;
			//draw nothing
			break;
		default:
			break;
		}
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
