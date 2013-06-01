package sf.game.hithamster.model;

public class GameProcessController {
	public static enum GAME_STATE {
		GAME_STATE_READY,
		GAME_STATE_PLAY,//游戏开始
		GAME_STATE_PAUSE,//游戏暂停
		GAME_STATE_FINISH//游戏结束
	};

	private GAME_STATE state = GAME_STATE.GAME_STATE_PLAY;

	private int processCount = 0;
	private static final int PROCESS_SUM = 1000;

	public GAME_STATE getGameState() {
		return this.state;
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

	public void process() {
		switch (this.state) {
		case GAME_STATE_READY:
			break;
		case GAME_STATE_PAUSE:
			break;
		case GAME_STATE_PLAY:
			if (this.processCount <= PROCESS_SUM) {
				this.processCount++;
			} else {
				this.finish();
			}
			break;
		case GAME_STATE_FINISH:
			break;
		default:
			break;
		}
	}
	public float getGameProcess() {
		return this.processCount * 1.0f / PROCESS_SUM;
	}
}
