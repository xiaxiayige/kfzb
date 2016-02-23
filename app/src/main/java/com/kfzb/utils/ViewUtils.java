package com.kfzb.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/22 0022.
 */
public class ViewUtils {

    public static void Toast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
}
