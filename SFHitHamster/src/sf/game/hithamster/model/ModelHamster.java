package sf.game.hithamster.model;

public class ModelHamster {
	public static final String TAG = "ModelHamster";

	public static enum HAMSTER_STATE {
		HIDED,
		JUMPING,
		LAUGHING,
		HIDING,
		DEAD
	};

	private static final int FULL_LIFE = 1;
	private int life = FULL_LIFE;

	private HAMSTER_STATE state= HAMSTER_STATE.HIDED;

	public void jump() {
		this.state = HAMSTER_STATE.JUMPING;
	}
	public void laugh() {
		this.state = HAMSTER_STATE.LAUGHING;
	}
	public void hurt() {
		this.life--;
		if (this.life <= 0) {
			this.state = HAMSTER_STATE.DEAD;
		}
	}
	public void hide() {
		this.state = HAMSTER_STATE.HIDING;
	}
	public void leave() {
		this.state = HAMSTER_STATE.HIDED;
	}

	public HAMSTER_STATE getState () {
		return this.state;
	}
}
