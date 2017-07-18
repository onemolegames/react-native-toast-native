import {NativeModules,ToastAndroid,Platform} from 'react-native';

if(Platform.OS === "android"){
    var ToastNative = {
        show: function (
            message,
            duration,
            position,
            styles
        ) {
            NativeModules.Toast.show(message, duration,position,styles);
        }
    };
}else{
    var ToastNative = {
        // Toast duration constants
        SHORT: RCTToastAndroid.SHORT,
        LONG: RCTToastAndroid.LONG,

        // Toast gravity constants
        TOP: RCTToastAndroid.TOP,
        BOTTOM: RCTToastAndroid.BOTTOM,
        CENTER: RCTToastAndroid.CENTER,

        show: function (
            message,
            duration,
            customStyle
        ) {
            NativeModules.RNToastNative.show(message, duration === undefined ? this.SHORT : duration,customStyle);
        },

        showWithGravity: function (
            message,
            duration,
            gravity,
            customStyle
        ) {
            NativeModules.RNToastNative.showWithGravity(message, duration === undefined ? this.SHORT : duration, customStyle, gravity);
        },
    };
}

export default ToastNative;
