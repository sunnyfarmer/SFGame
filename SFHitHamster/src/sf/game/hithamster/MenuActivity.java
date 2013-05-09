package sf.game.hithamster;

import sf.game.hithamster.model.GameSetting;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MenuActivity extends Activity {
	private Button btnStartGame = null;
	private Button btnExitGame = null;
	private Button btnSoundToggle = null;
	private Button btnShakeToggle = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		this.findView();
		this.setListener();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//实现状态保存
	}

	private void findView() {
		this.btnStartGame = (Button)this.findViewById(R.id.btnStartGame);
		this.btnExitGame = (Button)this.findViewById(R.id.btnExitGame);

		this.btnSoundToggle = (Button)this.findViewById(R.id.btnSoundToggle);
		this.setSoundSettingDisplay();
		this.btnShakeToggle = (Button)this.findViewById(R.id.btnShakeToggle);
		this.setShakeSettingDisplay();
	}
	private void setListener() {
		this.btnStartGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//start game
				MenuActivity.this.startGame();
			}
		});
		this.btnExitGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//exit game
				MenuActivity.this.exitGame();
			}
		});
		this.btnSoundToggle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//toggle sound setting
				MenuActivity.this.toggleSoundSetting();
			}
		});
		this.btnShakeToggle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//toggle shake setting
				MenuActivity.this.toggleShakeSetting();
			}
		});
	}

	private void startGame() {
		Intent intent = new Intent(this, HithamsterActivity.class);
		this.startActivity(intent);
	}
	private void exitGame() {
		
	}
	private void toggleSoundSetting() {
		GameSetting.toggleSoundSetting();
		this.setSoundSettingDisplay();
	}
	private void setSoundSettingDisplay() {
		if (GameSetting.soundSetting()) {
			this.btnSoundToggle.setText(R.string.menu_sound_disable);
		} else {
			this.btnSoundToggle.setText(R.string.menu_sound_enable);
		}
	}
	private void toggleShakeSetting() {
		GameSetting.toggleShakeSetting();
		this.setShakeSettingDisplay();
	}
	private void setShakeSettingDisplay() {
		if (GameSetting.shakeSetting()) {
			this.btnShakeToggle.setText(R.string.menu_shake_disable);
		} else {
			this.btnShakeToggle.setText(R.string.menu_shake_enable);
		}
	}
}