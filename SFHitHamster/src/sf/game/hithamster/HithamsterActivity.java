package sf.game.hithamster;

import sf.game.hithamster.view.HithamsterView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HithamsterActivity extends Activity {
	public static final String TAG = "HithamsterActivity";
    private HithamsterView surfaceView = null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
	    super.onCreate(savedInstanceState);
	    this.setContentView(R.layout.activity_hithamster);

	    this.findView();
	}

	private void findView() {
		this.surfaceView = (HithamsterView) this.findViewById(R.id.hithamsterView);

	}
}