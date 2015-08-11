package com.yc.health.fragment;

import org.kymjs.kjframe.KJActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.yc.health.LoginActivity;
import com.yc.health.R;
import com.yc.health.manager.ActivityManager;
import com.yc.health.util.Method;

public class QuitPopupWindow extends PopupWindow{

	private View mMenuView; 
	private Button quitBtn;
	private Button cancleBtn;
	
	private Context context;
	
	public QuitPopupWindow(Context context){
		this.context = context;
		init();
	}
	
	@SuppressWarnings("deprecation")
	private void init() {
		//加载布局
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		mMenuView = inflater.inflate(R.layout.quit_menu, null);  
		quitBtn = (Button) mMenuView.findViewById(R.id.quit_menu_quit);
		cancleBtn = (Button) mMenuView.findViewById(R.id.quit_menu_cancle);
		
        this.setContentView(mMenuView);  
        this.setWidth(LayoutParams.FILL_PARENT);  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        this.setFocusable(true);  
        ColorDrawable dw = new ColorDrawable(Color.WHITE);  
        this.setBackgroundDrawable(dw);  
        this.setAnimationStyle(R.style.quitAnimation);
        Method method = new Method(context);
        method.backgroundAlpha(0.5f);
        mMenuView.setOnTouchListener(new OnTouchListener() {  
            public boolean onTouch(View v, MotionEvent event) {  
                int height = mMenuView.findViewById(R.id.quit_menu).getTop();  
                int y=(int) event.getY();  
                if(event.getAction() == MotionEvent.ACTION_UP){  
                    if(y < height){  
                        dismiss();  
                    }  
                }                 
                return true;  
            }  
        });  
        
        quitBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				KJActivity activity = (KJActivity)context;
				activity.showActivity(activity, LoginActivity.class);
				ActivityManager.getInstace().enterHome();
			}
        });
        
        cancleBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dismiss();
			}
        });
	}
}