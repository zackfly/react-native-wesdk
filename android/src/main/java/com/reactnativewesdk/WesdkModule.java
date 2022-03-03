package com.reactnativewesdk;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

@ReactModule(name = WesdkModule.NAME)
public class WesdkModule extends ReactContextBaseJavaModule {
    public static final String NAME = "Wesdk";
    private ReactApplicationContext _reactContext ;
    public WesdkModule(ReactApplicationContext reactContext) {
        super(reactContext);
      _reactContext = reactContext;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void multiply(String appId, String userName, Promise promise) {
      IWXAPI api = WXAPIFactory.createWXAPI(this._reactContext, appId);

      WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
      req.userName = userName; // 填小程序原始id
//      req.path = path;                  ////拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
      req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_TEST;// 可选打开 开发版，体验版和正式版
      api.sendReq(req);
      promise.resolve(a * b);
    }

    public static native int nativeMultiply(int a, int b);
}
