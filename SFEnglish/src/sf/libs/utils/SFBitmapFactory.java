package sf.libs.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

import sf.libs.activity.BaseApp;
import sf.libs.log.SFLog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SFBitmapFactory<T> {
	public static final String TAG = "SFBitmapFactory";

	public LinkedHashMap<T, Bitmap> bitmapArray = new LinkedHashMap<T, Bitmap>();
	public static long bitmapBytesCount = 0;
	public static final long MAX_BITMAP_BYTES_COUNT = 64 * 1024 * 1024;

	public static final int MAX_REFERENCE_BITMAP_WIDTH = 512;
	public static final int MAX_REFERENCE_BITMAP_HEIGHT = 512;

	/**
	 * 清理Bitmap内存
	 */
	private void clearBitmap() {
		//每次清理一半
		SFLog.i(TAG, "clearBitmap... > " + bitmapBytesCount + " ? " + MAX_BITMAP_BYTES_COUNT);
		if (bitmapBytesCount >= MAX_BITMAP_BYTES_COUNT) {
			SFLog.i(TAG, "clearBitmap.begin.........");
			int bitmapCount = bitmapArray.size();
			Set<T> keyset = bitmapArray.keySet();
			ArrayList<T> keyList = new ArrayList<T>();
			for (T t : keyset) {
				keyList.add(t);
			}
			int cot = 0;
			for (T t : keyList) {
				Bitmap bitmap = bitmapArray.get(t);
				bitmapArray.remove(t);
				bitmap.recycle();
				cot++;
				if (cot >= bitmapCount/2) {
					break;
				}
			}
			this.updateBitmapBytesCount();
		}
	}
	private static void addBitmapBytes(Bitmap bitmap) {
		long bytesCount = bitmapBytesCount(bitmap);
		bitmapBytesCount += bytesCount;
		SFLog.d(TAG, bytesCount + ">>>");
	}
	private void updateBitmapBytesCount() {
		Set<T> keyArray = bitmapArray.keySet();
		bitmapBytesCount = 0;
		for (T t : keyArray) {
			Bitmap bm = bitmapArray.get(t);
			long bytesCount = bitmapBytesCount(bm);
			bitmapBytesCount += bytesCount;
		}
	}
	protected Bitmap getBitmapFromArray(T t) {
		Bitmap bitmap = bitmapArray.get(t);
		if (bitmap!=null) {
			//将刚取过的图片放在顶端,最后才释放
			bitmapArray.remove(t);
			bitmapArray.put(t, bitmap);
		}
		return bitmap;
	}
//	private static void printKeySet(Set<Object> keyset) {
//		String text = "";
//		for (Object object : keyset) {
//			text += object.toString()+",";
//		}
//		SFLog.d(TAG, text);
//	}
	protected void putBitmapIntoArray(T t, Bitmap bitmap) {
		clearBitmap();
		bitmapArray.put(t, bitmap);
		SFLog.d(TAG, "<<< " + t);
		addBitmapBytes(bitmap);
	}

	/**
	 * 保存Bitmap至应用文件目录中
	 * @param app
	 * @param bitmap
	 * @param cargoId
	 */
	public static void saveBitmap(BaseApp app, Bitmap bitmap,
			String filename) {
		if (bitmap != null) {
			FileOutputStream fos;
			try {
				fos = app.openFileOutput(filename, Context.MODE_PRIVATE);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			} catch (FileNotFoundException e) {
				SFLog.e(TAG, e.getMessage(), e);
			}
		}
	}
	/**
	 * 从应用文件目录中删除Bitmap
	 * @param cargoId
	 * @param app
	 */
	public static void removeBitmap(int cargoId, BaseApp app) {
		String filename = String.format(Locale.US, "%d.png", cargoId);
		app.deleteFile(filename);
	}

	/**
	 * 计算Bitmap所占内存
	 * @param bitmap
	 * @return
	 */
	@SuppressLint("NewApi")
	public static long bitmapBytesCount(Bitmap bitmap) {
		long bytesCount = 0;
		if (SFUtils.sdkVersion() >= 12) {
			bytesCount = bitmap.getByteCount();
		} else {
			bytesCount = bitmap.getRowBytes() * bitmap.getHeight();
		}
		return bytesCount;
	}

	public static Bitmap loadBitmap(Resources res, int resId, int reqWidth, int reqHeight) {
	    // First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		SFLog.i(TAG, "inSampleSize : " + options.inSampleSize);

	    // Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
		return bitmap;
	}
	public static Bitmap loadBitmap(String filePath, int reqWidth, int reqHeight) {
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(filePath, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(filePath, options);
	}
	public static Bitmap loadBitmap(FileInputStream fis, int reqWidth, int reqHeight) {
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeStream(fis, null, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeStream(fis, null, options);
	}
	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    SFLog.i(TAG, String.format("req (%d,%d), fact (%d,%d)", reqWidth, reqHeight, width, height));
	    if (height > reqHeight || width > reqWidth) {
	        // Calculate ratios of height and width to requested height and width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);
	
	        // Choose the smallest ratio as inSampleSize value, this will guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }

	    return inSampleSize;
	}
}
