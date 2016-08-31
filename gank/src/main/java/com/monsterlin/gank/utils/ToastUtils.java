package com.monsterlin.gank.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * Created by monsterLin on 2016/2/16.
 */
public class ToastUtils {
	
	private static Toast mToast;
	
	/**
	 * 显示Toast
	 */
	public static void showToast(Context context, CharSequence text, int duration) {
		if(mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		mToast.show();
	}

}
