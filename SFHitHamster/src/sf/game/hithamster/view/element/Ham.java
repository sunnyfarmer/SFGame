package sf.game.hithamster.view.element;

import sf.game.hithamster.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Ham extends SFElement {
	public static final String TAG = "Ham";
	private Bitmap bitmapHam = null;
	private float x = 0.0f;
	private float y = 0.0f;
	private float screenX = -10000.0f;
	private float screenY = -10000.0f;

	public Ham(Context context) {
		super(context);
		this.bitmapHam = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ham);
	}

	@Override
	public void display(Canvas canvas) {
		Matrix matrix = new Matrix();
		matrix.postTranslate(this.x-90, this.y-110);
		canvas.drawBitmap(this.bitmapHam, matrix, paint);
	}
	public void display(Canvas canvas, float xScale, float yScale) {
		this.x = this.screenX *xScale;
		this.y = this.screenY *yScale;
		this.display(canvas);
	}
	public void setScreenX(float screenX) {
		this.screenX = screenX;
	}
	public void setScreenY(float screenY) {
		this.screenY = screenY;
	}
}
