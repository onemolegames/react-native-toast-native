using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Com.Reactlibrary.RNToastNative
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNToastNativeModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNToastNativeModule"/>.
        /// </summary>
        internal RNToastNativeModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNToastNative";
            }
        }
    }
}
