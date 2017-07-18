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
        SHORT: NativeModules.RNToastNative.SHORT,
        LONG: NativeModules.RNToastNative.LONG,

        // Toast gravity constants
        TOP: NativeModules.RNToastNative.TOP,
        BOTTOM: NativeModules.RNToastNative.BOTTOM,
        CENTER: NativeModules.RNToastNative.CENTER,

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
