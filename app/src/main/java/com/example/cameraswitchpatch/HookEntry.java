package com.example.cameraswitchpatch;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookEntry implements IXposedHookLoadPackage {
    private static final String TAG = "CameraSwitchPatch";
    private static final String TARGET_PACKAGE = "com.android.camera";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals(TARGET_PACKAGE)) return;

        try {
            XposedHelpers.findAndHookMethod(
                "com.android.camera.module.g0",
                lpparam.classLoader,
                "isCameraSwitchingDuringZoomingAllowed",
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        try {
                            param.setResult(true);
                            Log.d(TAG, "Forced camera switching during recording = true (Marble)");
                        } catch (Throwable t) {
                            Log.e(TAG, "Error in isCameraSwitchingDuringZoomingAllowed hook", t);
                        }
                    }
                }
            );

            Log.i(TAG, "Marble Camera switch patch loaded successfully");
            
        } catch (Throwable t) {
            Log.e(TAG, "Failed to load marble camera switch patch", t);
            XposedBridge.log(t);
        }
    }
}
