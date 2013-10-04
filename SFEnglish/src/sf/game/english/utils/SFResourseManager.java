package sf.game.english.utils;

import java.lang.reflect.Field;

import sf.game.english.R;
import sf.libs.log.SFLog;

public class SFResourseManager {
	public static final String TAG = "ResourseManager";

	//COURSE
	public static enum TCOURSE {
		COURSE_ANIMAL,
		COURSE_ALPHET,
		COURSE_COLOR,
		COURSE_FRUIT,
		COURSE_VEGETABLE
	};
	public static int courseImageId(TCOURSE course) {
		int resId = 0;
		switch (course) {
		case COURSE_ALPHET:
			resId = R.drawable.title_alphet;
			break;
		case COURSE_ANIMAL:
			resId = R.drawable.title_animal;
			break;
		case COURSE_COLOR:
			resId = R.drawable.title_color;
			break;
		case COURSE_FRUIT:
			resId = R.drawable.title_fruit;
			break;
		case COURSE_VEGETABLE:
			resId = R.drawable.title_vegetable;
			break;
		default:
			break;
		}
		return resId;
	}

	//COURSE OBJECT
	public static int courseObjectArrayId(TCOURSE course) {
		int resId = 0;
		switch (course) {
		case COURSE_ALPHET:
			resId = R.array.alphet;
			break;
		case COURSE_ANIMAL:
			resId = R.array.animal;
			break;
		case COURSE_COLOR:
			resId = R.array.color;
			break;
		case COURSE_FRUIT:
			resId = R.array.fruit;
			break;
		case COURSE_VEGETABLE:
			resId = R.array.vegetable;
			break;
		default:
			break;
		}
		return resId;
	}
	public static int courseObjectBitmapId(String courseObjectName) {
		int resId = 0;
		Class<R.drawable> classObject = R.drawable.class;
		try {
			Field field = classObject.getField(courseObjectName);
			Object courseObjectId = field.get(classObject);
			resId = Integer.valueOf(courseObjectId.toString());
		} catch (NoSuchFieldException e) {
			SFLog.e(TAG, e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			SFLog.e(TAG, e.getMessage(), e);
		} catch (IllegalAccessException e) {
			SFLog.e(TAG, e.getMessage(), e);
		}
		return resId;
	}

	//COURSE CONTENT PICTURES
	//COURSE CONTENT INTRODUCTION
}
