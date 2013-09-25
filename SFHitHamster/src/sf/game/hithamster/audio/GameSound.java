package sf.game.hithamster.audio;

import sf.game.hithamster.R;
import sf.game.hithamster.model.GameSetting;
import sf.util.SFMath;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class GameSound {
	private Context context = null;

	// Test
	private MediaPlayer mp = null;

	public GameSound(Context context) {
		this.context = context;
		this.init();
	}

	public static final int[] SOUND_FILE_LIST = {
		R.raw.lgbe_na_shit_
	};

	public static final int randomSound() {
		int length = SOUND_FILE_LIST.length;
		int index = SFMath.randomInt(0, length-1);
		return SOUND_FILE_LIST[index];
	}

	public void init() {
		if (GameSetting.soundSetting(this.context)) {
			// 初始化声音资源
			int resId = GameSound.randomSound();
			mp = MediaPlayer.create(this.context, resId);
			mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					GameSound.this.release();
					GameSound.this.init();
					GameSound.this.play();
//					mp.seekTo(0);
//					mp.start();
				}
			});
		}
	}

	public void play() {
		if (GameSetting.soundSetting(this.context)) {
			if (this.mp==null) {
				this.init();
			}
			this.mp.start();
		}
	}
	public void pause() {
		if (GameSetting.soundSetting(this.context)) {
			if (this.mp!=null) {
				this.mp.pause();
			}
		}
	}
	public void release() {
		if (this.mp!=null) {
			this.mp.stop();
			this.mp.release();
			this.mp = null;
		}
	}
}
