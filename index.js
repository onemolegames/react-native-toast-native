import {NativeModules, ToastAndroid, Platform} from "react-native";

var RCTToastNative = Platform.OS === 'android' ? NativeModules.Toast : NativeModules.RNToastNative;

var ToastNative = {
    //Toast duration constants
    SHORT: RCTToastNative.SHORT,
    LONG: RCTToastNative.LONG,

    // Toast gravity constants
    TOP: RCTToastNative.TOP,
    BOTTOM: RCTToastNative.BOTTOM,
    CENTER: RCTToastNative.CENTER,

    show: function (message,
                    duration,
                    position,
                    styles) {
        RCTToastNative.show(message || "This is a toast message", duration || ToastNative.SHORT, position || ToastNative.TOP, styles || {});
    }
};

export default ToastNative;
