package sf.game.hithamster.model;

public class GameProcessController {
	public static final String TAG = "GameProcessController";
	public static enum GAME_STATE {
		GAME_STATE_READY,
		GAME_STATE_PLAY,//游戏开始
		GAME_STATE_PAUSE,//游戏暂停
		GAME_STATE_FINISH,//游戏完成一关
		GAME_STATE_OVER//游戏结束
	};

	private GAME_STATE state = GAME_STATE.GAME_STATE_PLAY;

	private int processCount = 0;
	private static final int PROCESS_SUM = 1000;

	private int level = 1;
	private static long[] ACTIVE_TIME_GAP_LIST = {1000, 900, 800, 700, 600};
	private long active_time_gap = ACTIVE_TIME_GAP_LIST[0];

	public static final int FULL_POWER = 5;

	public static final int UPGRADE_WAITING_TIME = 50;
	public int upgradeWatingTimeCount = UPGRADE_WAITING_TIME;

	public GAME_STATE getGameState() {
		return this.state;
	}
	public long getActiveTimeGap() {
		return this.active_time_gap;
	}
	public void begin() {
		this.processCount = 0;
		this.state = GAME_STATE.GAME_STATE_PLAY;
	}
	public void pause() {
		this.state = GAME_STATE.GAME_STATE_PAUSE;
	}
	public void finish() {
		this.state = GAME_STATE.GAME_STATE_FINISH;
	}
	public void gameOver() {
		this.state = GAME_STATE.GAME_STATE_OVER;
	}
	private static long getTimeGapByLevel(int level) {
		//level 	: 0   1   2   3   4   5   6   7   8   9   10...
		//gap index : 0   1   2   3   2   1   0   1   2   3   2 ...
		int temp = (level-1)%((ACTIVE_TIME_GAP_LIST.length-1)*2);// n%6 得到temp为[0,5]
		int gapIndex = temp>=ACTIVE_TIME_GAP_LIST.length ? 
					(ACTIVE_TIME_GAP_LIST.length-1)*2 - temp :
					temp;
		return ACTIVE_TIME_GAP_LIST[gapIndex];
	}
	public void gotoNextLevel() {
		this.level++;
		this.active_time_gap = getTimeGapByLevel(this.level);//this.active_time_gap /this.level;
		this.begin();
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
			this.upgradeWatingTimeCount--;
			if (this.upgradeWatingTimeCount<0) {
				this.gotoNextLevel();
				this.upgradeWatingTimeCount = GameProcessController.UPGRADE_WAITING_TIME;
			}
			break;
		case GAME_STATE_OVER:
			break;
		default:
			break;
		}
	}
	public float getGameProcess() {
		return this.processCount * 1.0f / PROCESS_SUM;
	}

	public void isGameOver(int hamsterHitted, int hamsterMiss) {
		if (hamsterMiss > FULL_POWER) {
			this.gameOver();
		}
	}
}
