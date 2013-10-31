package sf.game.english;

import java.util.Locale;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import sf.game.english.utils.SFBitmapManager;
import sf.game.english.utils.SFStorageManager;
import sf.libs.activity.BaseApp;
import sf.libs.log.SFLog;

public class SFEnglishApp extends BaseApp implements OnInitListener{
	public static final String TAG = "SFEnglishApp";

	protected SFStorageManager mStorageManager = null;
	protected SFBitmapManager mBitmapManager = null;

	protected TextToSpeech mTts = null;
	protected SpeechRecognizer mSpeechRecognizer = null;
	protected RecognitionListener mRecognitionListener = null;

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
		if (this.mTts==null) {
			mTts = new TextToSpeech(this, this);
		}
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

	//初始化SpeechRecognizer
	public void initSpeechRecognizer() {
		this.mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
	}
	//设置RecognitionListener
	public void setRecognitionListener(RecognitionListener listener){
		SFLog.d(TAG, "setRecognitionListener begin");
		if (this.mSpeechRecognizer!=null) {
			this.mRecognitionListener = listener;
			this.mSpeechRecognizer.setRecognitionListener(listener);
		}
		SFLog.d(TAG, "setRecognitionListener end");
	}
	public void startSpeechRecoginze() {
		SFLog.d(TAG, "startSpeechRecoginze begin");
		if (this.mRecognitionListener!=null) {
			this.mSpeechRecognizer.setRecognitionListener(this.mRecognitionListener);
			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
			intent.putExtra("calling_package", "VoiceIME");
			intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
			this.mSpeechRecognizer.startListening(intent);
			SFLog.d(TAG, "startSpeechRecoginze end");
		}
	}
	public void stopSpeechRecognize() {
		SFLog.d(TAG, "stopSpeechRecognize begin");
		if (this.mRecognitionListener!=null) {
			this.mSpeechRecognizer.stopListening();
			SFLog.d(TAG, "stopSpeechRecognize end");
		}
	}
}
