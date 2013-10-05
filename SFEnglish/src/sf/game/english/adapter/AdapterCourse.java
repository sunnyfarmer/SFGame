package sf.game.english.adapter;

import java.util.ArrayList;

import sf.game.english.ActivityPractise;
import sf.game.english.R;
import sf.game.english.TopActivity;
import sf.game.english.adapter.viewholder.VHAdapterCourse;
import sf.game.english.model.CourseObject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCourse extends SFBaseAdapter implements OnItemClickListener {
	public static final String TAg = "AdapterCourse";

	private ArrayList<CourseObject> mCourseObjectArray = null;

	public AdapterCourse(TopActivity activity, ArrayList<CourseObject> courseObjectArray) {
		super(activity);
		this.mCourseObjectArray = courseObjectArray;
	}

	@Override
	public int getCount() {
		if (this.mCourseObjectArray!=null) {
			return this.mCourseObjectArray.size();
		}
		return 0;
	}

	@Override
	public CourseObject getItem(int position) {
		if (this.mCourseObjectArray!=null) {
			return this.mCourseObjectArray.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		VHAdapterCourse vhAdapterCourse = null;
		if (convertView==null) {
			convertView = LayoutInflater.from(this.mActivity).inflate(R.layout.adapter_course, null);
			vhAdapterCourse = new VHAdapterCourse();
			vhAdapterCourse.ivCourse = (ImageView) convertView.findViewById(R.id.ivCourse);
			vhAdapterCourse.tvCourse = (TextView) convertView.findViewById(R.id.tvCourse);

			convertView.setTag(vhAdapterCourse);
		} else {
			vhAdapterCourse = (VHAdapterCourse) convertView.getTag();
		}

		final CourseObject courseObject = this.getItem(position);
		vhAdapterCourse.ivCourse.setImageBitmap(courseObject.getmObjectBitmap(this.mActivity));
		vhAdapterCourse.tvCourse.setText(courseObject.getmObjectText());

		return convertView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		this.mActivity.toActivity(ActivityPractise.class);
	}

}
