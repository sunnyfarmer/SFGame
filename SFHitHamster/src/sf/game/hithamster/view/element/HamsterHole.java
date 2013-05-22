package sf.game.hithamster.view.element;

import java.util.ArrayList;

import sf.game.hithamster.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class HamsterHole extends SFElement {
	private Bitmap bitmapHamsterHoleAbove = null;
	private Bitmap bitmapHamsterHoleDown = null;
	private float x = 0; 
	private float y = 0;

	private Hamster hamster = null;

	public HamsterHole(Context context) {
		super(context);
		this.bitmapHamsterHoleAbove = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hole_above_half);
		this.bitmapHamsterHoleDown = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.hole_down_half);

//		//清理为扩大图片空间 而画到图片中的黑点
//		for (int x = 0; x < this.bitmapHamsterHoleAbove.getWidth(); x++) {
//			for (int y = 0; y < 10; y++) {
//				int pixel = this.bitmapHamsterHoleAbove.getPixel(x, y);
//				if (pixel!=0) {
//					this.bitmapHamsterHoleAbove.setPixel(x, y, 0);
//				}
//			}
//		}
	}
	@Override
	public void display(Canvas canvas) {
		//上半个地鼠洞与地鼠
		Bitmap bitmapCopyOfAboveHole = this.bitmapHamsterHoleAbove.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvasOfHamsterHole = new Canvas(bitmapCopyOfAboveHole);
//		//使仓鼠放在洞的中间
		float xOfHamster = (bitmapCopyOfAboveHole.getWidth()-this.getHamster().getWidth()) / 2.0f;
		float yOfHamster = bitmapCopyOfAboveHole.getHeight();
		this.getHamster().display(canvasOfHamsterHole, xOfHamster, yOfHamster);

		Matrix matrixOfAboveHole = new Matrix();
		matrixOfAboveHole.postTranslate(this.x, this.y);
		canvas.drawBitmap(bitmapCopyOfAboveHole, matrixOfAboveHole, paint);

		//下半个地鼠洞
		Bitmap bitmapCopyOfDownHole = this.bitmapHamsterHoleDown.copy(Bitmap.Config.ARGB_8888, true);
		Matrix matrixOfDownHole = new Matrix();
		matrixOfDownHole.setTranslate(this.x, this.y+bitmapCopyOfAboveHole.getHeight());
		canvas.drawBitmap(bitmapCopyOfDownHole, matrixOfDownHole, paint);
	}

	public void display(Canvas canvas, float x, float y) {
		this.x = x;
		this.y = y;
		this.display(canvas);
	}

	public float width() {
		return this.bitmapHamsterHoleAbove.getWidth();
	}
	public float height() {
		return this.bitmapHamsterHoleAbove.getHeight();
	}

	public Hamster getHamster() {
		if (this.hamster==null) {
			this.hamster = new Hamster(context);
		}
		return this.hamster;
	}
}
