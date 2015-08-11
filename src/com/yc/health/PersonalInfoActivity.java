package com.yc.health;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import com.yc.health.fragment.PersonalPopupWindow;
import com.yc.health.fragment.QuitPopupWindow;
import com.yc.health.manager.ActivityManager;
import com.yc.health.util.Method;

import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;

public class PersonalInfoActivity extends KJActivity implements OnGestureListener{

	@BindView(id = R.id.personalinfo_back, click = true)
	private ImageView backBtn;
	@BindView(id = R.id.personalinfo_headimg, click = true)
	private ImageView headImg;
	@BindView(id = R.id.personalinfo_name)
	private TextView nameText;
	@BindView(id = R.id.personalinfo_account)
	private TextView accountText;
	@BindView(id = R.id.personalinfo_sex)
	private TextView sexText;
	@BindView(id = R.id.personalinfo_quit, click = true)
	private ImageView quitBtn;
	
	private PersonalPopupWindow menuWindow = null;
	private GestureDetector gestureDetector;
	
	@Override
	public void setRootView() {
		setContentView(R.layout.personalinfo);
	}

	@Override
	public void initData() {
		super.initData();
		
		ActivityManager.getInstace().addActivity(aty);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void initWidget() {
		super.initWidget();
		
		gestureDetector = new GestureDetector(this); // 手势滑动
	}

	@Override
	public void widgetClick(View v) {
		super.widgetClick(v);
		
		switch(v.getId()) {
		case R.id.personalinfo_back:
			this.finish();
			break;
		case R.id.personalinfo_quit:
			QuitPopupWindow menuWindow = new QuitPopupWindow(aty);//显示窗口  
            menuWindow.showAtLocation(this.findViewById(R.id.personal_info), 
            		Gravity.BOTTOM, 0, 0); 
            menuWindow.setOnDismissListener(new OnDismissListener(){
				@Override
				public void onDismiss() {
					Method method = new Method(aty);
					method.backgroundAlpha(1f);
				}
            });
			break;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		gestureDetector.onTouchEvent(ev);
		super.dispatchTouchEvent(ev);
		return false;
	}
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,
			float arg3) {
		if ( (e2.getX() - e1.getX()) > 80 && Math.abs(e2.getY() - e1.getY()) < 80 ) {
			if ( menuWindow == null ) {
				menuWindow = new PersonalPopupWindow(aty);//显示窗口  
	            menuWindow.showAtLocation(this.findViewById(R.id.personal_info), 
	            		Gravity.LEFT | Gravity.BOTTOM, 0, 0); 
	            menuWindow.setOnDismissListener(new OnDismissListener(){
					@Override
					public void onDismiss() {
						Method method = new Method(aty);
						method.backgroundAlpha(1f);
						menuWindow = null;
					}
	            });
			}
		}
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}
}
