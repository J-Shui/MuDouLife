package com.mudoulife.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mudoulife.R;
import com.mudoulife.common.Globals;

/**
 * Created by viennetta on 2016/10/20.
 */

public class DialogUtils {

    private static final String TAG = "DialogUtils";

    private static final int INFO_DIALOG_WIDTH = (int)(880 * Globals.gScreenScale);
    private static final int INFO_DIALOG_HEIGHT = (int)(436 * Globals.gScreenScale);
//    private static final int INFO_DIALOG_TITLE_HEIGHT = (int)(113 * Globals.gScreenScale);
//    private static final int INFO_DIALOG_MESSAGE_HEIGHT = (int)(152 * Globals.gScreenScale);
//
//    private static final int CHOICE_DIALOG_TV_WIDTH = (int)(714 * Globals.gScreenScale);
//    private static final int CHOICE_DIALOG_TV_HEIGHT = (int)(292 * Globals.gScreenScale);
//    private static final int CHOICE_DIALOG_BUTTON_TV_WIDTH = (int)(355 * Globals.gScreenScale);
//    private static final int CHOICE_DIALOG_BUTTON_TV_HEIGHT = (int)(77 * Globals.gScreenScale);
//    private static final int CHOICE_DIALOG_BUTTON_TV_MARGIN = (int)(3 * Globals.gScreenScale);
//
    private static final int CHOICE_DIALOG_MESSAGE_HEIGHT = (int)(291 * Globals.gScreenScale);
//
//    //quit dialog tv
//    private static final int QUIT_DIALOG_WIDTH = (int)(1374 * Globals.gScreenScale);
//    private static final int QUIT_DIALOG_HEIGHT = (int)(185 * Globals.gScreenScale);
//    private static final int QUIT_DIALOG_BUTTON_WIDTH = (int)(250 * Globals.gScreenScale);
//    private static final int QUIT_DIALOG_BUTTON_HEIGHT = (int)(120 * Globals.gScreenScale);
//    private static final int QUIT_DIALOG_CANCEL_RIGHT_MARGIN = (int)(70 * Globals.gScreenScale);
//    private static final int QUIT_DIALOG_CONFIRM_RIGHT_MARGIN = (int)(-7 * Globals.gScreenScale);
//
    private static final float INFO_DIALOG_TITLE_SIZE = 48f * Globals.gScreenScale;
    private static final float INFO_DIALOG_MESSAGE_SIZE = 48.0f * Globals.gScreenScale;
//    //quit dialog tv
//    private static final float QUIT_DIALOG_MESSAGE_SIZE = 49.0f * Globals.gScreenScale;
//
//    private static final float CHOICE_DIALOG_MESSAGE_TV_SIZE = 32.0f * Globals.gScreenScale;
    
    public interface OnDialogListener {
        void onPositiveListener();
        void onNegativeListener();
    }

    public interface OnDialogSureListener{
        void onSureListener();
    }

//    public static void showInfoDialog(Context context, String strMessage, final OnDialogSureListener listener) {
//        if(context != null) {
//            final RelativeLayout layout = (RelativeLayout) View.inflate(context,
//                    R.layout.layout_info_dialog,
//                    null);
//
//            if (layout != null) {
//                LayoutParams param = new LayoutParams();
//                param.width = INFO_DIALOG_WIDTH;
//                param.height = INFO_DIALOG_HEIGHT;
//                final Dialog dialog = new AlertDialog.Builder(context, R.style.Dialog).create();
//                dialog.show();
//                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialog) {
//                        layout.removeAllViews();
//                        dialog.dismiss();
//                    }
//                });
//                dialog.getWindow().setContentView(layout, param);
//                TextView titleView = (TextView) layout.findViewById(R.id.info_dialog_title_id);
//                RelativeLayout.LayoutParams lp =
//                        (RelativeLayout.LayoutParams) titleView.getLayoutParams();
//                lp.height = INFO_DIALOG_TITLE_HEIGHT;
//                titleView.setLayoutParams(lp);
//                titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_TITLE_SIZE);
//
//                TextView msgView = (TextView) layout.findViewById(R.id.info_dialog_message_id);
//                lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
//                lp.height = INFO_DIALOG_MESSAGE_HEIGHT;
//                msgView.setLayoutParams(lp);
//                msgView.setText(strMessage);
//                msgView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_MESSAGE_SIZE);
//
//                TextView buttonView = (TextView) layout.findViewById(R.id.info_dialog_confirm_id);
//                buttonView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_TITLE_SIZE);
//                buttonView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        dialog.setContentView(null);
//                        layout.removeAllViews();
//                        dialog.dismiss();
//                        if (listener != null) {
//                            listener.onSureListener();
//                        }
//                    }
//                });
//            }
//        }
//    }

    public static void showChoiceDialog(Context context, String strMessage, String strPositive, String strNegative, final OnDialogListener listener) {
        if(context != null) {
//            boolean bIsFocusMode = Globals.gDeviceFlag == Globals.FIT_FOR_DEVICE_TV;
            final RelativeLayout layout = (RelativeLayout) View.inflate(context,
                    R.layout.layout_choice_dialog,
                    null);

            if (layout != null) {
                LayoutParams param = new LayoutParams();
                param.width = INFO_DIALOG_WIDTH;
                param.height = INFO_DIALOG_HEIGHT;
                final Dialog dialog = new AlertDialog.Builder(context, R.style.Dialog).create();
                dialog.show();
                Window window = dialog.getWindow();
                if (window != null) {
                    LayoutParams attributes = window.getAttributes();
                    DisplayMetrics d = context.getResources().getDisplayMetrics();
                    attributes.x = (int) Utils.dp2px(24);
                    window.setAttributes(attributes);
                    window.setContentView(layout, param);
                }
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if(listener != null)
                            listener.onNegativeListener();
                        layout.removeAllViews();
                        dialog.dismiss();
                    }
                });

                TextView msgView = (TextView) layout.findViewById(R.id.choice_dialog_message_id);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) msgView.getLayoutParams();
                lp.height = CHOICE_DIALOG_MESSAGE_HEIGHT;
                msgView.setLayoutParams(lp);
                msgView.setText(strMessage);
                msgView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_MESSAGE_SIZE);

                Button positiveView = (Button) layout.findViewById(R.id.choice_dialog_positive_id);
//                if(bIsFocusMode)
//                    positiveView.setBackgroundResource(R.drawable.selector_choice_dialog_button_tv);
                positiveView.setText(strPositive);
                positiveView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_TITLE_SIZE);
                positiveView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        dialog.setContentView(null);
                        if(listener != null)
                            listener.onPositiveListener();
                        layout.removeAllViews();
                        dialog.dismiss();
                    }
                });

                Button negativeView = (Button) layout.findViewById(R.id.choice_dialog_negative_id);
//                if(bIsFocusMode) {
//                    negativeView.setBackgroundResource(R.drawable.selector_choice_dialog_button_tv);
//                    negativeView.requestFocus();
//                    negativeView.requestFocusFromTouch();
//                }
                negativeView.setText(strNegative);
                negativeView.setTextSize(TypedValue.COMPLEX_UNIT_PX, INFO_DIALOG_TITLE_SIZE);
                negativeView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        dialog.setContentView(null);
                        if(listener != null)
                            listener.onNegativeListener();
                        layout.removeAllViews();
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    public static Dialog showProgressDialog(Context context) {
        Dialog dialog = null;
        return dialog;
    }
}
