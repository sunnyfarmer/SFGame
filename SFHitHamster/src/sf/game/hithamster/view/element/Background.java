package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends SFElement{
	private Bitmap bitmapBackground = null;

	@SuppressLint("NewApi")
	public Background(Context context) {
		super(context);
		this.bitmapBackground = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.background);
	}

	public Bitmap getBitmapBackground() {
		return this.bitmapBackground;
	}

	@Override
	public void display(Canvas canvas) {
		Bitmap bitmapCopy = this.bitmapBackground.copy(Bitmap.Config.ARGB_8888, true);

		Rect srcRect = new Rect(0, 0, bitmapCopy.getWidth(), bitmapCopy.getHeight());
		Rect dstRect = new Rect(0, 0, this.width, this.height);
		canvas.drawBitmap(bitmapCopy, srcRect, dstRect, paint);
	}

	public Bitmap getBackgroundCopy() {
		Bitmap bitmapCopy = this.bitmapBackground.copy(Bitmap.Config.ARGB_8888, true);
		return bitmapCopy;
	}

	public void display(Canvas canvas, Bitmap backgroundCopy) {
		Rect srcRect = new Rect(0, 0, backgroundCopy.getWidth(), backgroundCopy.getHeight());
		Rect dstRect = new Rect(0, 0, this.width, this.height);
		canvas.drawBitmap(backgroundCopy, srcRect, dstRect, paint);		
	}
}
