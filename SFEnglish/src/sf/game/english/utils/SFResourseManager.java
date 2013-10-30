package sf.game.english.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import sf.game.english.R;
import sf.libs.log.SFLog;

public class SFResourseManager {
	public static final String TAG = "ResourseManager";

	//COURSE
	/**
	 * 课程分类
	 */
	public static enum TCOURSE {
		COURSE_ALPHET,
		COURSE_COLOR,
		COURSE_ANIMAL,
		COURSE_FRUIT,
		COURSE_VEGETABLE
	};
	/**
	 * 课程中文名
	 * @param course
	 * @return
	 */
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
	/**
	 * 课程图标
	 * @param course
	 * @param isVertical
	 * @return
	 */
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
	/**
	 * 课程单词库
	 * @param course
	 * @return
	 */
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
	/**
	 * 课程单词中文库
	 * @param course
	 * @return
	 */
	public static int courseObjectChineseArrayId(TCOURSE course) {
		int resId = 0;
		switch (course) {
		case COURSE_ALPHET:
			resId = R.array.alphet_chinese;
			break;
		case COURSE_ANIMAL:
			resId = R.array.animal_chinese;
			break;
		case COURSE_COLOR:
			resId = R.array.color_chinese;
			break;
		case COURSE_FRUIT:
			resId = R.array.fruit_chinese;
			break;
		case COURSE_VEGETABLE:
			resId = R.array.vegetable_chinese;
			break;
		default:
			break;
		}
		return resId;
	}
	/**
	 * 
	 * @param courseObjectName
	 * @return
	 */
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
	public static ArrayList<Integer> courseObjectLifeImageFileNames(String courseObjectName) {
		ArrayList<Integer> resIdArray = new ArrayList<Integer>();
		for (int cot = 1; true; cot++) {
			int resId = 0;
			Class<R.drawable> classObject = R.drawable.class;
			try {
				String fieldName = courseObjectName + "_" + cot;
				Field field = classObject.getField(fieldName);
				Object courseObjectId = field.get(classObject);
				resId = Integer.valueOf(courseObjectId.toString());
			} catch (NoSuchFieldException e) {
				SFLog.e(TAG, e.getMessage(), e);
				break;
			} catch (IllegalArgumentException e) {
				SFLog.e(TAG, e.getMessage(), e);
				break;
			} catch (IllegalAccessException e) {
				SFLog.e(TAG, e.getMessage(), e);
				break;
			}
			resIdArray.add(resId);
		}
		return resIdArray;
	}
	//COURSE CONTENT INTRODUCTION
	public static int courseObjectDescription(String courseObjectName) {
		int resId = 0;
		Class<R.string> classObject = R.string.class;
		try {
			String fieldName = courseObjectName + "_des";
			Field field = classObject.getField(fieldName);
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
}
