package moder.com.mvp.present;

/**
 * Created by eCRF on 2017/7/7.
 */

public interface IPresent {
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onCreate();
}
