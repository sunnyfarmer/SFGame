package sf.game.hithamster.model;

import sf.game.hithamster.view.element.Background;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class GameController {
	public static final String TAG = "GameController";
	public static final int HAMSTER_NUMBER = 6;
	public static enum GAME_STATE {
		GAME_STATE_READY,
		GAME_STATE_PLAY,//游戏开始
		GAME_STATE_PAUSE,//游戏暂停
		GAME_STATE_FINISH//游戏结束
	};

	private GAME_STATE state = GAME_STATE.GAME_STATE_READY;
	private Context context = null;

	private Background elBackground = null;

	public GameController(Context context) {
		this.context = context;
	}

	public Background getElBackground() {
		if (this.elBackground==null) {
			this.elBackground = new Background(this.context, HAMSTER_NUMBER);
		}
		return this.elBackground;
	}

	public void begin() {
		this.state = GAME_STATE.GAME_STATE_PLAY;
	}
	public void pause() {
		this.state = GAME_STATE.GAME_STATE_PAUSE;
	}
	public void finish() {
		this.state = GAME_STATE.GAME_STATE_FINISH;
	}

	public void gameProcess() {
		if (this.state != GAME_STATE.GAME_STATE_PLAY) {
			return;
		}
		
	}

	public void render(Canvas canvas) {
		if (canvas==null) {
			return;
		}
		canvas.drawColor(Color.rgb(0, 0, 0));

		//background
		this.getElBackground().display(canvas);
	}
}
