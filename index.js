import {NativeModules,ToastAndroid,Platform} from 'react-native';

var RCTToastAndroid = Platform.OS === 'android' ? NativeModules.Toast : NativeModules.RNToastNative;

var ToastNative = {
    // Toast duration constants
    // SHORT: RCTToastAndroid.SHORT,
    // LONG: RCTToastAndroid.LONG,
    //
    // // Toast gravity constants
    // TOP: RCTToastAndroid.TOP,
    // BOTTOM: RCTToastAndroid.BOTTOM,
    // CENTER: RCTToastAndroid.CENTER,

    show: function (
        message,
        duration,
        position,
        styles
    ) {
        RCTToastAndroid.show(message, duration,position,styles);
    }
};

export default ToastNative;
