package moder.com.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moder.com.mvp.R;

/**
 * Created by eCRF on 2017/7/7.
 */

public abstract class EasyActivity extends AppCompatActivity {

    public Context mContext;
    private OnLeftClickListener onLeftClickListener;                                // 左侧返回按钮点击事件
    private OnRightClickListener onRightClickListener;                              // 右侧标题点击事件
    private static final int defalutBackIconResID = 0;          // 左侧返回按钮的默认图片
    // toolbar标题显示位置
    protected static final int TOOLBAR_MODE_NONE = 0;                             // 不显示标题
    protected static final int TOOLBAR_MODE_LEFT = 1;                             // 标题居左侧
    protected static final int TOOLBAR_MODE_CENTER = 2;                           // 标题居右

    @Nullable
    public @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    public @BindView(R.id.tv_toolbar_title)
    TextView toolbarTitle;

    @Nullable
    public @BindView(R.id.tv_toolbar_right)
    TextView toolbarRight;
    /**
     * 获取layout的id，由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        // 设置应用为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //使用ButterKnife注入
        ButterKnife.bind(this);
        mContext = this;
        //EcrfApplication.getInstance().addActivity(this);
        // 初始化toolbar
        if (toolbar != null){
            //如果页面导入了toolbar则显示
            setSupportActionBar(toolbar);
            if (toolbarTitle != null) {
                //getTitle()的值是activity的android:lable属性值
                toolbarTitle.setText(getTitle());
                toolbarRight.setText("");
                //设置默认的标题不显示
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);//友盟统计
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);// 友盟统计
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EcrfApplication.getInstance().removeActivity(this);
    }

    /**
     * 初始化toolbar标题
     * @param titleMode     标题的位置模式
     * @param title          标题的内容
     */
    public void initToolbarTitle (int titleMode,String title) {
        switch (titleMode) {
            case TOOLBAR_MODE_NONE:
                if (toolbar != null){
                    toolbar.setTitle("");
                }
                toolbarTitle.setText("");
                break;
            case TOOLBAR_MODE_LEFT:
                if (toolbar != null){
                    toolbar.setTitle(title);
                }
                toolbarTitle.setText("");
                break;
            case TOOLBAR_MODE_CENTER:
                if (toolbar != null){
                    toolbar.setTitle("");
                }
                toolbarTitle.setText(title);
                break;
        }
    }

    /**
     * 初始化toolbar标题和左侧返回键
     * @param titleMode     标题的位置模式
     * @param title          标题的内容
     * @param backResId     左侧返回按钮的图标资源id
     */
    public void initToolbarWithBack(int titleMode,String title,int backResId,OnLeftClickListener leftClickListener){
        initToolbarTitle(titleMode,title);
        if (toolbar != null){
            int rid = (backResId == 0 ? defalutBackIconResID : backResId);
            toolbar.setNavigationIcon(rid);
            this.onLeftClickListener = leftClickListener;
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLeftClickListener != null){
                        onLeftClickListener.onLeftClick();
                    }else{
                        onBackPressed();
                    }
                }
            });
        }
    }

    /**
     *  设置右侧文字或图标及点击事件
     * @param text  右侧标题内容
     * @param rightClickListener  右侧点击事件
     */
    public void setToolbarRight(String text,OnRightClickListener rightClickListener){
        if (text != null && !"".equals(text)){
            toolbarRight.setCompoundDrawables(null,null,null,null);
            toolbarRight.setText(text);
            this.onRightClickListener = rightClickListener;
            toolbarRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRightClickListener != null){
                        onRightClickListener.onRightClick();
                    }
                }
            });
        }else{
            toolbarRight.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置右侧文字或图标及点击事件
     * @param resId 右侧图标资源id
     * @param rightClickListener 右侧点击事件
     */
    public void setToolbarRight(int resId,OnRightClickListener rightClickListener){
        if (resId != 0){
            toolbarRight.setText("");
            Drawable drawable;
            if (Build.VERSION.SDK_INT >= 21){
                drawable = getResources().getDrawable(resId,null);
            }else{
                drawable = getResources().getDrawable(resId);
            }
//            int width = ScreenUtils.dipToPx(mContext,18);
//            int height = ScreenUtils.dipToPx(mContext,16);
//            drawable.setBounds(0,0,width,height);
            toolbarRight.setCompoundDrawables(drawable,null,null,null);
            this.onRightClickListener = rightClickListener;
            toolbarRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRightClickListener != null){
                        onRightClickListener.onRightClick();
                    }
                }
            });
        }else{
            toolbarRight.setVisibility(View.INVISIBLE);
        }
    }

    // toast提示
    public void showToast(String msg) {
    //ToastUtils.showToast(getApplicationContext(),msg);
    }

    // 跳转至下个页面
    public void turnToActivity(Class className){
        Intent intent = new Intent(this,className);
        startActivity(intent);
    }

    // 跳转至下个页面并传参
    public void turnToActivity(Class className,Bundle bundle){
        Intent intent = new Intent(this,className);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // 跳转至下个页面并销毁
    public void turnToActivityWithFinish(Class className){
        Intent intent = new Intent(this,className);
        startActivity(intent);
        this.finish();
    }

    // 跳转至下个页面并传参，并销毁
    public void turnToActivityWithFinish(Class className,Bundle bundle){
        Intent intent = new Intent(this,className);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    public void setOnLeftClickListener(OnLeftClickListener onLeftClickListener) {
        this.onLeftClickListener = onLeftClickListener;
    }

    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        this.onRightClickListener = onRightClickListener;
    }

    public void jumpToLogin(Context context){

    }

    // 左侧返回按钮点击事件
    public interface OnLeftClickListener{
        public void onLeftClick();
    }

    // 右侧子标题点击事件
    public interface OnRightClickListener{
        public void onRightClick();
    }



}
