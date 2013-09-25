package sf.game.hithamster.view.element;

import sf.util.SFSystem;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;

public abstract class SFElement {
	protected Context context = null;
	protected int contextWidth = 0;
	protected int contextHeight = 0;
	protected Paint paint = new Paint();

	@SuppressLint("NewApi")
	public SFElement(Context context) {
		this.context = context;

		int apiLevel = SFSystem.apiLevel(context);
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		if (apiLevel >= 13) {
			Point point = new Point();
			display.getSize(point);
			this.contextWidth = point.x;
			this.contextHeight = point.y;
		} else {
			this.contextWidth = display.getWidth();
			this.contextHeight = display.getHeight();
		}

		this.paint.setAntiAlias(true);
	}

	public abstract void display(Canvas canvas);
}
