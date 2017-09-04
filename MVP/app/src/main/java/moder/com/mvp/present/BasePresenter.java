package moder.com.mvp.present;

import android.app.Activity;
import android.content.Context;

import moder.com.mvp.view.IView;

/**
 * Created by eCRF on 2017/7/7.
 */

public class BasePresenter<V extends IView> implements IPresent {
    public V mView;
    public Context mContext;
    public Activity mActivity;
    //初始化方法
    public void init(V view){
        this.mView=view;
        this.mView.initView();
    }
    public void init(V view,Context context){
        this.mView=view;
        this.mContext=context;
        this.mView.initView();
    }
    public void init(V view,Context context,Activity activity){
        this.mView=view;
        this.mContext=context;
        this.mActivity=activity;
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        clearMemory();
    }

    @Override
    public void onCreate() {

    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
    /*
    * 为防止内存泄漏，需在页面销毁的时候清楚对context实例的引用
    * */
    public void clearMemory(){
        this.mContext=null;
        this.mActivity=null;
    }

}
