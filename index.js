import {NativeModules,ToastAndroid,Platform} from 'react-native';

var ToastAndroid = Platform.OS === 'android' ? ToastAndroid : NativeModules.RNToastNative;

var ToastNative = {
    // Toast duration constants
    SHORT: ToastAndroid.SHORT,
    LONG: ToastAndroid.LONG,

    // Toast gravity constants
    TOP: ToastAndroid.TOP,
    BOTTOM: ToastAndroid.BOTTOM,
    CENTER: ToastAndroid.CENTER,

    show: function (
        message,
        duration,
        customStyle
    ) {
        ToastAndroid.show(message, duration === undefined ? this.SHORT : duration,customStyle);
    },

    showWithGravity: function (
        message,
        duration,
        gravity,
        customStyle
    ) {
        ToastAndroid.showWithGravity(message, duration === undefined ? this.SHORT : duration, customStyle, gravity);
    },
};

export default ToastNative;
