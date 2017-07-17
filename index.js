import {NativeModules,ToastAndroid,Platform} from 'react-native';

var RCTToastAndroid = Platform.OS === 'android' ? ToastAndroid : NativeModules.RNToastNative;

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
        RCTToastAndroid.show(message, duration === undefined ? this.SHORT : duration,customStyle);
    },

    showWithGravity: function (
        message,
        duration,
        gravity,
        customStyle
    ) {
        RCTToastAndroid.showWithGravity(message, duration === undefined ? this.SHORT : duration, customStyle, gravity);
    },
};

export default ToastNative;
