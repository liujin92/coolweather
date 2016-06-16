package co.utils;

/**
 * Created by ubuntu on 16-6-16.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
