package sf.game.hithamster;

import sf.game.hithamster.view.HithamsterView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

public class HithamsterActivity extends Activity {
	public static final String TAG = "HithamsterActivity";
    private SurfaceView surfaceView = null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
	    super.onCreate(savedInstanceState);

	    this.surfaceView = new HithamsterView(this);
	    this.setContentView(this.surfaceView);
	}

}