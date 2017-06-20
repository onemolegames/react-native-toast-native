  [![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/onemolegames/onemolegames)

# react-native-toast-native
  React Native Toast component for both Android and iOS. It just let iOS have the same toast performance with Android. Using [scalessec/Toast](https://github.com/scalessec/Toast) for iOS;

## Getting started

`$ npm install react-native-toast-native --save`

### Mostly automatic installation

`$ react-native link react-native-toast-native`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-toast-native` and add `RNToastNative.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNToastNative.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNToastNativePackage;` to the imports at the top of the file
  - Add `new RNToastNativePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-toast-native'
  	project(':react-native-toast-native').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-toast-native/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-toast-native')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNToastNative.sln` in `node_modules/react-native-toast-native/windows/RNToastNative.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Com.Reactlibrary.RNToastNative;` to the usings at the top of the file
  - Add `new RNToastNativePackage()` to the `List<IReactPackage>` returned by the `Packages` method


  ## Usage
  
  It's just the same as [ToastAndroid](http://facebook.github.io/react-native/docs/toastandroid.html)
  
  ```javascript
  import Toast from 'react-native-toast-native';
  
  Toast.show('This is a toast.');
  Toast.show('This is a long toast.',Toast.LONG);
  ```
  ## Options
  
  Toast was been able to make customizable through these properties
  
  ```javascript
  {
              width:300,
              height:50,
              backgroundColor: "#C2F8FF",
              color: "#ffffff",
              borderWidth: 3,
              borderColor: "#C2F8FF",
              borderRadius: 3
  }
  
  ```
  if you want to make a customizable toast,you add an object like above to `show` and `showGravity`
  
  #### Example usage:
  
  ```javascript
  import Toast from 'react-native-toast-native';
  const style={
                          width:300,
                          height:50,
                          backgroundColor: "#C2F8FF",
                          color: "#ffffff",
                          borderWidth: 3,
                          borderColor: "#C2F8FF",
                          borderRadius: 3
              };
  Toast.show('This is a long toast.',Toast.LONG,style);
  
  
  Toast.showWithGravity(message, Toast.SHORT,Toast.TOP,style)
  ```
  These are properties that can make customizable
  `width`,
  `height`,
  `backgroundColor`,
  `color`,
  `borderColor`,
  `borderWidth` 
  You can make customizable all of them or some of them or you can use default toast style.