package sf.game.hithamster.audio;

import sf.game.hithamster.R;
import sf.game.hithamster.model.GameSetting;
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

	public void init() {
		if (GameSetting.soundSetting()) {
			// 初始化声音资源
			mp = MediaPlayer.create(this.context, R.raw.lgbe_na_shit_);
			mp.setOnCompletionListener(new OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.seekTo(0);
					mp.start();
				}
			});
		}
	}

	public void play() {
		if (GameSetting.soundSetting()) {
			this.mp.start();
		}
	}
	public void pause() {
		if (GameSetting.soundSetting()) {
			this.mp.pause();
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
