
# react-native-toast-native

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
```javascript
import RNToastNative from 'react-native-toast-native';

// TODO: What to do with the module?
RNToastNative;
```
  