  [![StackShare](https://img.shields.io/badge/tech-stack-0690fa.svg?style=flat)](https://stackshare.io/onemolegames/onemolegames)

# react-native-toast-native
  React Native Toast is a toast component for both Android and iOS. it uses [scalessec/Toast](https://github.com/scalessec/Toast) for iOS;

     
## Demo:
  ![](test.gif)

## Getting started

`$ npm install react-native-toast-native --save`

### Mostly automatic installation

`$ react-native link react-native-toast-native`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-toast-native` and add `RNToastNative.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNToastNative.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.toast.RCTToastPackage;` to the imports at the top of the file
  - Add `new RCTToastPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-toast-native'
    project(':react-native-toast-native').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-toast-native/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
        compile project(':react-native-toast-native')
  	```

  ## Usage
  
  It's just the same as [ToastAndroid](http://facebook.github.io/react-native/docs/toastandroid.html)
  
  ```javascript
  import Toast from 'react-native-toast-native';
  
  Toast.show(); // Default toast message is shown.
  Toast.show('This is a toast.'); // Specific message is shown with default duration("SHORT") and poistion("TOP") and styles.
  Toast.show('This is a long toast.',Toast.LONG); //Specific message and duration are shown with default position and styles.
  ```
  It is higly customasible. Just provide a style object as a parameter.
  example:
   ```javascript
  import Toast from 'react-native-toast-native';
    const styles={
                    width,
                    height,
                    backgroundColor,
                    color,
                    borderWidth,
                    borderColor,
                    borderRadius
    }
    
    Toast.show('This is a long toast.',Toast.SHORT,Toast.TOP,styles); 
    // Customizable toast message is shown with specific message,duration and position.
  ```
   
    
  ## Options
 You can style the toast with below options;
  Android:
  ```javascript
  {
                width,
                height,
                backgroundColor,
                color,
                borderWidth,
                paddingLeft,
                paddingRight,
                paddingBottom,
                paddingTop,
                fontSize,
                lineHeight,
                xOffset,
                yOffset,
                letterSpacing,
                fontWeight
  }
  ```
  Ios:
  ```javascript
    {
                    width,
                    height,
                    backgroundColor,
                    color,
                    borderWidth,
                    borderColor,
                    borderRadius
    }
    
```
    
  
  if you want to make a customizable toast,you add an object like above to `show` and `showGravity`
  
  #### Example usage:
  
  ```javascript
  import Toast from 'react-native-toast-native';
  import {Platform} from 'react-native';
  const style={
                               backgroundColor: "#4ADDFB",
                               width: 300,
                               height: Platform.OS === ("ios") ? 50 : 100,
                               color: "#ffffff",
                               fontSize: 15,
                               lineHeight: 2,
                               lines: 4,
                               borderRadius: 15,
                               fontWeight: "bold",
                               yOffset: 40
                           };
    Toast.show(message, Toast.SHORT, Toast.TOP,style);
  
 
  ```

