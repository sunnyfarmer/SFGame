package sf.game.english.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import sf.game.english.R;
import sf.libs.log.SFLog;

public class SFResourseManager {
	public static final String TAG = "ResourseManager";

	//COURSE
	public static enum TCOURSE {
		COURSE_ALPHET,
		COURSE_COLOR,
		COURSE_ANIMAL,
		COURSE_FRUIT,
		COURSE_VEGETABLE
	};
	public static int courseStringId(TCOURSE course) {
		int resId = 0;
		switch (course) {
		case COURSE_ALPHET:
			resId = R.string.alphet;
			break;
		case COURSE_ANIMAL:
			resId = R.string.animal;
			break;
		case COURSE_COLOR:
			resId = R.string.color;
			break;
		case COURSE_FRUIT:
			resId = R.string.fruit;
			break;
		case COURSE_VEGETABLE:
			resId = R.string.vegetable;
			break;
		default:
			break;
		}
		return resId;
	}
	public static int courseImageId(TCOURSE course, boolean isVertical) {
		int resId = 0;
		switch (course) {
		case COURSE_ALPHET:
			resId = isVertical ? R.drawable.title_alphet_v : R.drawable.title_alphet_h;
			break;
		case COURSE_ANIMAL:
			resId = isVertical ? R.drawable.title_animal_v : R.drawable.title_animal_h;
			break;
		case COURSE_COLOR:
			resId = isVertical ? R.drawable.title_color_v : R.drawable.title_color_h;
			break;
		case COURSE_FRUIT:
			resId = isVertical ? R.drawable.title_fruit_v : R.drawable.title_fruit_h;
			break;
		case COURSE_VEGETABLE:
			resId = isVertical ? R.drawable.title_vegetable_v : R.drawable.title_vegetable_h;
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
	/**
	 * 生活照片存储assets中
	 * @param courseObjectName
	 * @return
	 */
	public static ArrayList<String> courseObjectLifeImageFileNames(String courseObjectName) {
		return null;
	}
	//COURSE CONTENT INTRODUCTION
	public static String courseObjectDescription(String courseObjectName) {
		return "Description";
	}
}