package sf.game.english;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityInroduction extends TopActivity {
	public static final String TAG = "ActivityIntroduction";

	protected Button btnBack = null;
	protected ImageView ivLifeA = null;
	protected ImageView ivLifeB = null;
	protected TextView tvDescription = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.setContentView(R.layout.activity_introduction);
	    super.onCreate(savedInstanceState);
	}

	@Override
	protected void initView() {
		super.initView();
		this.btnBack = (Button) this.findViewById(R.id.btnBack);
		this.ivLifeA = (ImageView) this.findViewById(R.id.ivLifeA);
		this.ivLifeB = (ImageView) this.findViewById(R.id.ivLifeB);
		this.tvDescription = (TextView) this.findViewById(R.id.tvDescription);
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
	}
}
