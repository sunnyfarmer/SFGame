package sf.game.english;

import java.util.Locale;
import java.util.Set;

import sf.game.english.model.CourseObject;
import sf.libs.log.SFLog;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ActivityPractise extends TopActivity implements RecognitionListener{
	public static final String TAG = "ActivityPractise";

	protected Button btnBack = null;
	protected TextView tvCourseObject = null;
	protected TextView tvCourseObjectChinese = null;
	protected ImageView ivCourseObject = null;
	protected ImageView ivMask = null;
	protected Button btnListen = null;
	protected Button btnSay = null;
	protected RadioGroup rgPronunciation = null;
	protected RadioButton rbUS = null;
	protected RadioButton rbUK = null;

	protected CourseObject mCourseObject = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_practice);
	    super.onCreate(savedInstanceState);
	    this.mApp.initTTS();
	    this.mApp.initSpeechRecognizer();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		this.refreshView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.mApp.setRecognitionListener(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		this.mApp.setRecognitionListener(null);
	}

	@Override
	protected void initView() {
		super.initView();

		this.btnBack = (Button) this.findViewById(R.id.btnBack);
		this.tvCourseObject = (TextView) this.findViewById(R.id.tvCourseObject);
		this.tvCourseObjectChinese = (TextView) this.findViewById(R.id.tvCourseObjectChinese);
		this.ivCourseObject = (ImageView) this.findViewById(R.id.ivCourseObject);
		this.ivMask = (ImageView) this.findViewById(R.id.ivMask);
		this.btnListen = (Button) this.findViewById(R.id.btnListen);
		this.btnSay = (Button) this.findViewById(R.id.btnSay);

		this.rgPronunciation = (RadioGroup) this.findViewById(R.id.rgPronunciation);
		this.rbUS = (RadioButton) this.findViewById(R.id.rbUS);
		this.rbUK = (RadioButton) this.findViewById(R.id.rbUK);
	}

	@Override
	protected void setListener() {
		super.setListener();
		this.btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		this.ivCourseObject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toActivity(ActivityInroduction.class);
			}
		});
		this.btnListen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mApp.ttsSpeak(mCourseObject.getmObjectText());
			}
		});
		this.btnSay.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch(action) {
				case MotionEvent.ACTION_DOWN:
					SFLog.d(TAG, "down");
					mApp.startSpeechRecoginze();
					break;
				case MotionEvent.ACTION_UP:
					SFLog.d(TAG, "up");
					mApp.stopSpeechRecognize();
					break;
				}
				return false;
			}
		});
		this.rgPronunciation.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				SFLog.d(TAG, "check change:" + checkedId + " & " + R.id.rbUS + " & " + R.id.rbUK);
				if (checkedId == R.id.rbUS) {
					if (rbUS.isPressed()) {
						mApp.setTTSLanguage(Locale.US);
					}
				} else if (checkedId == R.id.rbUK) {
					if (rbUK.isPressed()) {
						mApp.setTTSLanguage(Locale.UK);
					}
				}
			}
		});
	}

	protected void refreshView() {
		this.rgPronunciation.check(R.id.rbUS);

		this.mCourseObject = this.mApp.getmStorageManager().getmSelectedCourse().getmSelectedCourseObject();
		this.tvCourseObject.setText(this.mCourseObject.getmObjectText());
		this.tvCourseObjectChinese.setText(this.mCourseObject.getmObjectChinese());
		this.ivCourseObject.setImageBitmap(this.mCourseObject.getmObjectBitmap(this.mApp, 280, 280));
		this.refreshMask();
	}
	protected void refreshMask() {
		float height = (CourseObject.CORRECT_TIMES_TO_UNLOCK-this.mCourseObject.getmCorrectTimes()) * 
				this.ivCourseObject.getHeight() / 
				CourseObject.CORRECT_TIMES_TO_UNLOCK;
		SFLog.i(TAG, "height : " + height);
		LayoutParams params = (LayoutParams) this.ivMask.getLayoutParams();
		params.width = this.ivCourseObject.getWidth();
		params.height = (int)height;
		this.ivMask.setLayoutParams(params);
	}

	@Override
	public void onReadyForSpeech(Bundle params) {
		SFLog.d(TAG, "onReadyForSpeech");
		Set<String> keyset = params.keySet();
		for (String key : keyset) {
			String value = params.get(key).toString();
			SFLog.d(TAG, value);
		}
	}
	@Override
	public void onBeginningOfSpeech() {
		SFLog.d(TAG, "onBeginningOfSpeech");		
	}
	@Override
	public void onRmsChanged(float rmsdB) {
		SFLog.d(TAG, "onRmsChanged : " + rmsdB);
	}
	@Override
	public void onBufferReceived(byte[] buffer) {
		SFLog.d(TAG, "onBufferReceived : " + String.valueOf(buffer));
	}
	@Override
	public void onEndOfSpeech() {
		SFLog.d(TAG, "onEndOfSpeech");		
	}
	@Override
	public void onError(int error) {
		SFLog.d(TAG, "onError : " + error);		
	}
	@Override
	public void onResults(Bundle results) {
		SFLog.d(TAG, "onResults");
		Set<String> keyset = results.keySet();
		for (String key : keyset) {
			Object obj = results.get(key);
			String value = results.get(key).toString();
			SFLog.d(TAG, value + ":" + this.mCourseObject.getmObjectText());
//			String value = results.getString(key);
			if (value.contains(this.mCourseObject.getmObjectText())) {
				mCourseObject.setmCorrectTimes(mCourseObject.getmCorrectTimes()+1);
				refreshMask();
				mApp.ttsSpeak("right");
				break;
			}
		}
	}
	@Override
	public void onPartialResults(Bundle partialResults) {
		SFLog.d(TAG, "onPartialResults");
		Set<String> keyset = partialResults.keySet();
		for (String key : keyset) {
			String value = partialResults.get(key).toString();
			SFLog.d(TAG, value);
		}		
	}
	@Override
	public void onEvent(int eventType, Bundle params) {
		// TODO Auto-generated method stub
		
	}
}
