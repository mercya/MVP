package moder.com.mvp.view;

import android.os.Bundle;

/**
 * Created by eCRF on 2017/7/7.
 */

public interface IView {


    public void initView();
    public void showToast(String msg);
    public void turnToActivity(Class className);
    public void turnToActivity(Class className, Bundle bundle);
    public void turnToActivityWithFinish(Class className);
    public void turnToActivityWithFinish(Class className, Bundle bundle);
    // 结束当前页面
    public void finishSelf();


}
