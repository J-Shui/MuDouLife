package com.mudoulife.util;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mudoulife.R;
import com.mudoulife.common.Globals;

/**
 * Created by weiheling on 2016/11/10
 * 自定义的 toast
 */

public class ToastUtil {

    private static final String TAG = "ToastUtils";
    /**
     * toast 开关， true 打开， false 关闭
     */
    private static boolean toastFlag = true;
    private static Context mContext;
    private static int TOAST_TEXT_SIZE = 42;
    private static int TOAST_TEXT_HORIZONTAL_MARGIN = 42;
    private static int TOAST_TEXT_VERTICAL_MARGIN = 30;
    private static int TOAST__MARGIN_BOTTOM = 400;

    /**
     * @param context 上下文
     * @param content 内容
     */
    public static void makeText(Context context, String content){
        mContext = context.getApplicationContext();
//        if(toastFlag){
//            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
//        }
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_simple_toast, null);
        TextView textView= (TextView) view.findViewById(R.id.toast_text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Globals.gScreenScale * TOAST_TEXT_SIZE);
        textView.setText(content);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.topMargin = (int) (Globals.gScreenScale * TOAST_TEXT_VERTICAL_MARGIN);
        params.bottomMargin = (int) (Globals.gScreenScale * TOAST_TEXT_VERTICAL_MARGIN);
        params.leftMargin = (int) (Globals.gScreenScale * TOAST_TEXT_HORIZONTAL_MARGIN);
        params.rightMargin = (int) (Globals.gScreenScale * TOAST_TEXT_HORIZONTAL_MARGIN);
        textView.setLayoutParams(params);

        if(mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.BOTTOM,0,(int) (Globals.gScreenScale * TOAST__MARGIN_BOTTOM));
//        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.show();
    }

    private static Toast mToast = null;

    public static void recycle() {
        if(mToast != null) {
            try {
                mContext = null;
                mToast.cancel();
                mToast = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
