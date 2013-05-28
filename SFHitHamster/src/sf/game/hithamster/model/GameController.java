package sf.game.hithamster.model;

import java.util.ArrayList;

import sf.game.hithamster.view.element.Background;
import sf.game.hithamster.view.element.HamsterHole;
import sf.util.SFFloatPoint;
import sf.util.SFMath;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameController {
	public static final String TAG = "GameController";
	public static enum GAME_STATE {
		GAME_STATE_READY,
		GAME_STATE_PLAY,//游戏开始
		GAME_STATE_PAUSE,//游戏暂停
		GAME_STATE_FINISH//游戏结束
	};

	private GAME_STATE state = GAME_STATE.GAME_STATE_PLAY;
	private Context context = null;

	private Background elBackground = null;

	private HamsterController hamsterController = null;

	public GameController(Context context) {
		this.context = context;
	}

	public Background getElBackground() {
		if (this.elBackground==null) {
			this.elBackground = new Background(this.context);
		}
		return this.elBackground;
	}

	public HamsterController getHamsterController() {
		if (this.hamsterController==null) {
			this.hamsterController = new HamsterController();
		}
		return this.hamsterController;
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

		//控制地鼠的运动
		this.getHamsterController().controlHamsters();
	}

	public void render(Canvas canvas) {
		if (canvas==null) {
			return;
		}
		canvas.drawColor(Color.rgb(0, 0, 0));

		//获得 background
		Bitmap backgroundCopy = this.getElBackground().getBackgroundCopy();

		Canvas canvasOfBackground = new Canvas(backgroundCopy);
		//将hamster 画到背景上
		float widthPixel = 800.0f;
		float heightPixel = 480.0f;
		float xBegin = 90f/widthPixel * this.getElBackground().getBitmapBackground().getWidth();
		float yBegin = 160f/heightPixel * this.getElBackground().getBitmapBackground().getHeight();
		float xGap = 210f/widthPixel * this.getElBackground().getBitmapBackground().getWidth();
		float yGap = 80f/heightPixel * this.getElBackground().getBitmapBackground().getHeight();
		float xOffset = xBegin;
		float yOffset = yBegin;
		for (ModelHamster modelHamster : this.getHamsterController().getHamsterArray()) {
			modelHamster.render(canvasOfBackground, xOffset, yOffset);
			xOffset += xGap;
			if (xOffset > backgroundCopy.getWidth()-200) {
				xOffset = xBegin;
				yOffset += yGap;
			}
		}

		this.getElBackground().display(canvas, backgroundCopy);
	}

	public boolean onTouch(View v, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		this.elBackground.getHam().setScreenX(x);
		this.elBackground.getHam().setScreenY(y);

		this.hamsterController.hitHamsters(
				this.elBackground.screenToBackgroundPoint(
						new SFFloatPoint(x, y)		
				));
		return false;
	}

	private class HamsterController {
		public static final int HAMSTER_NUMBER = 6;
		private ArrayList<ModelHamster> hamsterArray = null;

		//上次弹出地鼠的时间
		private long lastActiveTime = 0;
		private static final long ACTIVE_TIME_GAP = 2000;

		public ArrayList<ModelHamster> getHamsterArray() {
			if (this.hamsterArray == null || this.hamsterArray.size() != HAMSTER_NUMBER) {
				this.hamsterArray = new ArrayList<ModelHamster>();
				for (int cot = 0; cot < HAMSTER_NUMBER; cot++) {
					ModelHamster modelHamster = new ModelHamster(GameController.this.context);
					this.hamsterArray.add(modelHamster);
				}
			}
			return this.hamsterArray;
		}

		public void controlHamsters() {
			//每秒冒出1个地鼠
			long now = new java.util.Date().getTime();
			if (now-this.lastActiveTime > ACTIVE_TIME_GAP) {
				//获得HIDED地鼠列表
				ArrayList<ModelHamster> hidedList = this.getHidedHamster();
				//随机冒出地鼠
				int randomIndex = SFMath.randomInt(0, hidedList.size()-1);
				ModelHamster modelHamster = hidedList.get(randomIndex);
				modelHamster.jump();

				this.lastActiveTime = now;
			}

			//检查地鼠状态
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				modelHamster.checkState();
			}
		}
		public void hitHamsters(SFFloatPoint point) {
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				modelHamster.isHitted(point);
			}
		}

		/**
		 * 获得露出来的地鼠的数量
		 * @return
		 */
		public int laughingHamsterCount() {
			int count = 0;
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				if (modelHamster.getState() != ModelHamster.HAMSTER_STATE.HIDED) {
					count ++;
				}
			}
			return count;
		}
		/**
		 * 获得藏起来的地鼠
		 * @return
		 */
		public ArrayList<ModelHamster> getHidedHamster() {
			ArrayList<ModelHamster> hidedList = new ArrayList<ModelHamster>();
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				if (modelHamster.getState() == ModelHamster.HAMSTER_STATE.HIDED) {
					hidedList.add(modelHamster);
				}
			}
			return hidedList;
		}
	}
}
