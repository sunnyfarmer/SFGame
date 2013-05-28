package sf.game.hithamster;

import sf.game.hithamster.view.HithamsterView;
import sf.util.SFLogger;
import android.app.Activity;
import android.os.Bundle;

public class HithamsterActivity extends Activity {
	public static final String TAG = "HithamsterActivity";
    private HithamsterView surfaceView = null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		SFLogger.d(TAG, "onCreate");
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_hithamster);

	    this.findView();
	    this.setListener();
	}

	private void findView() {
		this.surfaceView = (HithamsterView) this.findViewById(R.id.hithamsterView);
	}

	private void setListener() {
		this.surfaceView.setOnTouchListener(this.surfaceView);
	}
}