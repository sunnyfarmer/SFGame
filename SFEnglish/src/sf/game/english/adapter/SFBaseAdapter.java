package sf.game.english.adapter;

import sf.game.english.SFEnglishApp;
import sf.game.english.TopActivity;
import android.widget.BaseAdapter;

public abstract class SFBaseAdapter extends BaseAdapter {
	public static final String TAG = "SFBaseAdapter";

	protected TopActivity mActivity = null;
	protected SFEnglishApp mApp = null;

	public SFBaseAdapter(TopActivity activity) {
		this.mActivity = activity;
		this.mApp = this.mActivity.getmApp();
	}
}
