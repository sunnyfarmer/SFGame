package sf.game.hithamster.model;

import java.util.Date;

import sf.game.hithamster.view.element.HamsterHole;
import sf.util.SFFloatPoint;

import android.content.Context;
import android.graphics.Canvas;

/**
 * 地鼠
 * @author user
 * 
 */
public class ModelHamster {
	public static final String TAG = "ModelHamster";

	// 1 秒
	public static final long REBORN_TIME = 1000;
	public static final long LAUGHING_TIME = 1000;
	/**
	 * 地鼠生命周期：
	 *     HIDED    ： 藏在地洞里，看不见
	 *     JUMPING  ： 往外冒，可以打中
	 *     LAUGHING ： 露出头，可以打中
	 *     HIDING	： 正在往里缩，可以打中
	 *     WOUND	： 打中，受伤，冒星星
	 *     DEAD		： 打死了，不能再打了，1秒后重生在地洞里HIDED
	 * @author user
	 *
	 */
	public static enum HAMSTER_STATE {
		HIDED,		//藏了起来
		JUMPING,	//正在上升
		LAUGHING,	//露出来停住
		HIDING,		//正在隐藏
		WOUND,  	//受伤
		DEAD    	//死翘翘
	};

	private static final int FULL_LIFE = 1;
	private int life = FULL_LIFE;

	private HAMSTER_STATE state = HAMSTER_STATE.HIDED;
	private long hamsterStateChangedTime = Long.MIN_VALUE;

	private Context context = null;
	private HamsterHole hamsterHole = null;

	private float x;
	private float y;

	public ModelHamster(Context context) {
		this.context = context;
		this.hamsterHole = new HamsterHole(context, this);
	}

	private void freshStateTime() {
		this.hamsterStateChangedTime = new java.util.Date().getTime();
	}
	//起来
	public void jump() {
		this.state = HAMSTER_STATE.JUMPING;
		this.freshStateTime();
	}
	//停顿
	public void laugh() {
		this.state = HAMSTER_STATE.LAUGHING;
		this.freshStateTime();
	}
	//受伤
	public void hurt() {
		this.life--;
		if (this.life <= 0) {
			this.state = HAMSTER_STATE.DEAD;
		}
		this.freshStateTime();
	}
	//下去
	public void hide() {
		this.state = HAMSTER_STATE.HIDING;
		this.freshStateTime();
	}
	//藏起来
	public void leave() {
		this.state = HAMSTER_STATE.HIDED;
		this.freshStateTime();
	}
	//重生
	public void reborn() {
		this.life = ModelHamster.FULL_LIFE;
		this.state = HAMSTER_STATE.HIDED;
		this.freshStateTime();
	}
	//检查地鼠状态
	public void checkState() {
		java.util.Date date = new java.util.Date();
		if (this.state==HAMSTER_STATE.DEAD) {
			if (date.getTime() - this.hamsterStateChangedTime >= REBORN_TIME) {
				this.reborn();
			}
		} else if (this.state==HAMSTER_STATE.LAUGHING){
			if (date.getTime() - this.hamsterStateChangedTime >= REBORN_TIME) {
				this.hide();
			}
		}
	}
	//检查是否打中
	public void isHitted(SFFloatPoint point) {
		if (point.isInRect(x, y, this.hamsterHole.getWidth(), this.hamsterHole.getHeight())) {
			this.hurt();
		}
	}

	//渲染
	public void render(Canvas canvas, float x, float y) {
		this.x = x;
		this.y = y;
		this.hamsterHole.display(canvas, x, y);
	}

	public HAMSTER_STATE getState () {
		return this.state;
	}
}
