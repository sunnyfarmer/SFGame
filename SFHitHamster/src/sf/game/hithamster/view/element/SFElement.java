package sf.game.hithamster.view.element;

import sf.util.SFSystem;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

public abstract class SFElement {
	protected Context context = null;
	protected int width = 0;
	protected int height = 0;
	protected Paint paint = new Paint();

	@SuppressLint("NewApi")
	public SFElement(Context context) {
		this.context = context;

		int apiLevel = SFSystem.apiLevel(context);
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		if (apiLevel >= 13) {
			Point point = new Point();
			display.getSize(point);
			this.width = point.x;
			this.height = point.y;
		} else {
			this.width = display.getWidth();
			this.height = display.getHeight();
		}

		this.paint.setAntiAlias(true);
	}

	public abstract void display(Canvas canvas);
}
