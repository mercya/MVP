package moder.com.mvp.control;

import android.support.v7.app.AppCompatActivity;

import moder.com.mvp.activity.BaseActivity;

import moder.com.mvp.R;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Override
    public void initView() {

    }

    // init presenter
    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter();
        mPresenter.init(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;

    }
}
