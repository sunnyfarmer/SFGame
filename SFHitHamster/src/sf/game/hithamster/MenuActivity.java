package sf.game.hithamster;

import sf.game.hithamster.model.GameSetting;
import sf.util.SFAdvertisement;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;

public class MenuActivity extends Activity {
	private ImageView ivSound = null;
	private ImageView ivShake = null;
	private boolean isGameTriggered = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		this.findView();
		this.setListener();

		SFAdvertisement.init(this);
		SFAdvertisement.showSmartBanner(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		this.isGameTriggered = false;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//实现状态保存
	}

	private void findView() {
		this.ivSound = (ImageView) this.findViewById(R.id.ivSound);
		this.ivShake = (ImageView) this.findViewById(R.id.ivShake);

		this.setSoundSettingDisplay();
		this.setShakeSettingDisplay();
	}
	private void setListener() {
		this.ivSound.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//toggle sound setting
				MenuActivity.this.toggleSoundSetting();
			}
		});
		this.ivShake.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//toggle shake setting
				MenuActivity.this.toggleShakeSetting();
			}
		});

		this.getWindow().getDecorView().setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				MenuActivity.this.startGame();
				return false;
			}
		});
	}

	private void startGame() {
		if (!isGameTriggered) {
			Intent intent = new Intent(this, HithamsterActivity.class);
			this.startActivity(intent);
			isGameTriggered = true;
		}
	}
	private void toggleSoundSetting() {
		GameSetting.toggleSoundSetting(this);
		this.setSoundSettingDisplay();
	}
	private void setSoundSettingDisplay() {
		if (GameSetting.soundSetting(this)) {
			this.ivSound.setImageResource(R.drawable.sound_enable);
		} else {
			this.ivSound.setImageResource(R.drawable.sound_disable);
		}
	}
	private void toggleShakeSetting() {
		GameSetting.toggleShakeSetting(this);
		this.setShakeSettingDisplay();
	}
	private void setShakeSettingDisplay() {
		if (GameSetting.shakeSetting(this)) {
			this.ivShake.setImageResource(R.drawable.shake_enable);
		} else {
			this.ivShake.setImageResource(R.drawable.shake_disable);
		}
	}
}