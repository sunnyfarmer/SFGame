package sf.game.english;

import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import sf.game.english.utils.SFBitmapManager;
import sf.game.english.utils.SFStorageManager;
import sf.libs.activity.BaseApp;

public class SFEnglishApp extends BaseApp implements OnInitListener{
	public static final String TAG = "SFEnglishApp";

	protected SFStorageManager mStorageManager = null;
	protected SFBitmapManager mBitmapManager = null;

	protected TextToSpeech mTts = null;

	@Override
	public void onCreate() {
		super.onCreate();
		this.mStorageManager = new SFStorageManager(this);
		this.mBitmapManager = new SFBitmapManager(this);
	}

	public SFStorageManager getmStorageManager() {
		return mStorageManager;
	}

	public SFBitmapManager getmBitmapManager() {
		return this.mBitmapManager;
	}

	//初始化TTS引擎
	public void initTTS() {
		mTts = new TextToSpeech(this, this);
	}
	@Override
	public void onInit(int status) {
		// TTS Engine初始化完成
		if (status == TextToSpeech.SUCCESS) {
			this.setTTSLanguage(Locale.US);
		}
	}
	public boolean setTTSLanguage(Locale locale) {
		boolean rs = false;
		if (this.mTts!=null) {
			int status = this.mTts.setLanguage(locale);
			if (status == TextToSpeech.LANG_MISSING_DATA ||
					status == TextToSpeech.LANG_NOT_SUPPORTED) {
				rs = false;
			} else {
				rs = true;
			}
		}
		return rs;
	}
	public void ttsSpeak(String text) {
		this.mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

}
