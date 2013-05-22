package sf.game.hithamster.view.element;

import java.util.ArrayList;

import sf.game.hithamster.R;
import sf.game.hithamster.view.HithamsterView;
import sf.util.SFLogger;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background extends SFElement{
	private Bitmap bitmapBackground = null;

	//仓鼠
	private ArrayList<HamsterHole> elHamsterHoleArrayList = null;
	private int hamsterNumber = 0;

	@SuppressLint("NewApi")
	public Background(Context context, int hamsterNumber) {
		super(context);
		this.hamsterNumber = hamsterNumber;
		this.bitmapBackground = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.background);
	}

	@Override
	public void display(Canvas canvas) {
		Bitmap bitmapCopy = this.bitmapBackground.copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvasOfBackground = new Canvas(bitmapCopy);
		//hamster hole
		float widthPixel = 800.0f;
		float heightPixel = 480.0f;
		float xBegin = 90f/widthPixel * bitmapCopy.getWidth();
		float yBegin = 160f/heightPixel *bitmapCopy.getHeight();
		float xGap = 210f/widthPixel * bitmapCopy.getWidth();
		float yGap = 80f/heightPixel * bitmapCopy.getHeight();
		float xOffset = xBegin;
		float yOffset = yBegin;
		for (HamsterHole hamsterHole : this.getHamsterHoles()) {
			hamsterHole.display(canvasOfBackground, xOffset, yOffset);
			xOffset += xGap;
			if (xOffset > this.width) {
				xOffset = xBegin;
				yOffset += yGap;
			}
		}

		Rect srcRect = new Rect(0, 0, bitmapCopy.getWidth(), bitmapCopy.getHeight());
		Rect dstRect = new Rect(0, 0, this.width, this.height);
		canvas.drawBitmap(bitmapCopy, srcRect, dstRect, paint);
	}

	public ArrayList<HamsterHole> getHamsterHoles() {
		if (this.elHamsterHoleArrayList==null || this.elHamsterHoleArrayList.size()<hamsterNumber) {
			this.elHamsterHoleArrayList = new ArrayList<HamsterHole>(); 
			for (int cot = 0; cot < hamsterNumber; cot++) {
				HamsterHole hole = new HamsterHole(this.context);
				this.elHamsterHoleArrayList.add(hole);
			}
		}
		return this.elHamsterHoleArrayList;
	}
}
