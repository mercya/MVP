package moder.com.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import moder.com.mvp.present.BasePresenter;
import moder.com.mvp.view.IView;

/**
 * Created by eCRF on 2017/7/7.
 */

public abstract class BaseActivity<T extends BasePresenter> extends  EasyActivity implements IView {

    public T mPresenter;
    /*
    * 需要子类来实现。获取子类的IPresent，一个Activity有可能有多个IPresent
    * */

    protected BasePresenter getPresent(){
        return mPresenter;
    }

    protected abstract  void initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void finishSelf() {
        finish();
    }
}
