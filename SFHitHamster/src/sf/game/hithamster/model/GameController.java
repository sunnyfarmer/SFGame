package sf.game.hithamster.model;

import java.util.ArrayList;

import sf.game.hithamster.model.GameProcessController.GAME_STATE;
import sf.game.hithamster.view.element.Background;
import sf.util.SFFloatPoint;
import sf.util.SFMath;
import sf.util.SFSystem;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

public class GameController {
	public static final String TAG = "GameController";

	private Context context = null;

	private Background elBackground = null;

	private HamsterController hamsterController = null;

	private GameProcessController gameProcessController = null;

	public GameController(Context context) {
		this.context = context;
		this.gameProcessController = new GameProcessController();
	}

	public GameProcessController getGameProcessController() {
		if (this.gameProcessController==null) {
			this.gameProcessController = new GameProcessController();
		}
		return this.gameProcessController;
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

	public void gameProcess() {
		//控制游戏时间
		this.gameProcessController.process();

		if (this.gameProcessController.getGameState() != GameProcessController.GAME_STATE.GAME_STATE_PLAY) {
			return;
		}
		//控制地鼠的运动
		this.getHamsterController().controlHamsters();

		this.gameProcessController.isGameOver(this.getElBackground().hamsterHitNumber, this.getElBackground().hamsterMiss);
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
		this.elBackground.getTimebar().setPercent(this.gameProcessController.getGameProcess());

		if (this.gameProcessController.getGameState().equals(GAME_STATE.GAME_STATE_OVER)) {
			this.getElBackground().isGameOver = true;
		}
		if (this.gameProcessController.getGameState().equals(GAME_STATE.GAME_STATE_FINISH)) {
			this.getElBackground().isGameUpgrading = true;
			this.getElBackground().upgradeProcess = this.gameProcessController.upgradeWatingTimeCount;
		} else {
			this.getElBackground().isGameUpgrading = false;
		}

		this.getElBackground().display(canvas, backgroundCopy);
	}

	public boolean onTouch(View v, MotionEvent event) {
		if (this.getGameProcessController().getGameState().equals(GAME_STATE.GAME_STATE_OVER)) {
			((Activity)this.context).finish();
		}

		if (this.getGameProcessController().getGameState().equals(GAME_STATE.GAME_STATE_PLAY)) {
			float x = event.getX();
			float y = event.getY();
			this.elBackground.getHam().setScreenX(x);
			this.elBackground.getHam().setScreenY(y);
	
			this.hamsterController.hitHamsters(
					this.elBackground.screenToBackgroundPoint(
							new SFFloatPoint(x, y)		
					));
		}
		return false;
	}

	public interface OnLeaveListener {
		public void onLeave(ModelHamster modelHamster);
	}

	private class HamsterController implements OnLeaveListener{
		public static final int HAMSTER_NUMBER = 6;
		private ArrayList<ModelHamster> hamsterArray = null;

		//上次弹出地鼠的时间
		private long lastActiveTime = 0;

		public ArrayList<ModelHamster> getHamsterArray() {
			if (this.hamsterArray == null || this.hamsterArray.size() != HAMSTER_NUMBER) {
				this.hamsterArray = new ArrayList<ModelHamster>();
				for (int cot = 0; cot < HAMSTER_NUMBER; cot++) {
					ModelHamster modelHamster = new ModelHamster(GameController.this.context);
					modelHamster.setOnLeaveListener(this);
					this.hamsterArray.add(modelHamster);
				}
			}
			return this.hamsterArray;
		}

		public void controlHamsters() {
			//每秒冒出1个地鼠
			long now = new java.util.Date().getTime();
			if (now-this.lastActiveTime > GameController.this.gameProcessController.getActiveTimeGap()) {
				//获得HIDED地鼠列表
				ArrayList<ModelHamster> hidedList = this.getHidedHamster();
				if (hidedList.size() >= 1) {
					//随机冒出地鼠
					int randomIndex = SFMath.randomInt(0, hidedList.size()-1);
					ModelHamster modelHamster = hidedList.get(randomIndex);
					modelHamster.jump();
	
					this.lastActiveTime = now;
				}
			}

			//检查地鼠状态
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				modelHamster.checkState();
			}
		}
		public void hitHamsters(SFFloatPoint point) {
			for (ModelHamster modelHamster : this.getHamsterArray()) {
				if (modelHamster.isHitted(point)) {
					GameController.this.elBackground.hamsterHitNumber++;
					SFSystem.vibrate(GameController.this.context, 50);
					break;
				}
			}
		}

		/**
		 * 获得露出来的地鼠的数量
		 * @return
		 */
		@SuppressWarnings("unused")
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

		@Override
		public void onLeave(ModelHamster modelHamster) {
			GameController.this.elBackground.hamsterMiss++;
		}
	}
}
