//
//  RNToastNative
//
#import <UIKit/UIKit.h>
#import "RCTBridgeModule.h"
#import "UIView+Toast.h"

NSInteger const RNToastNativeBottomOffset = 40;
double const RNToastNativeShortDuration = 3.0;
double const RNToastNativeLongDuration = 5.0;
NSInteger const RNToastNativeGravityBottom = 1;
NSInteger const RNToastNativeGravityCenter = 2;
NSInteger const RNToastNativeGravityTop = 3;

@interface RNToastNative : NSObject <RCTBridgeModule>
@end

@implementation RNToastNative {
    CGFloat _keyOffset;
}

- (instancetype)init {
    if (self = [super init]) {
        _keyOffset = 0;
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(keyboardWasShown:)
                                                     name:UIKeyboardDidShowNotification
                                                   object:nil];
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(keyboardWillHiden:)
                                                     name:UIKeyboardWillHideNotification
                                                   object:nil];
    }
    return self;
}

- (void)keyboardWasShown:(NSNotification *)notification {
    
    CGSize keyboardSize = [[[notification userInfo] objectForKey:UIKeyboardFrameBeginUserInfoKey] CGRectValue].size;
    
    int height = MIN(keyboardSize.height,keyboardSize.width);
    int width = MAX(keyboardSize.height,keyboardSize.width);
    
    _keyOffset = height;
}

- (void)keyboardWillHiden:(NSNotification *)notification {
    _keyOffset = 0;
}


RCT_EXPORT_MODULE()

- (NSDictionary *)constantsToExport {
    return @{
             @"SHORT": [NSNumber numberWithDouble:RNToastNativeShortDuration],
             @"LONG": [NSNumber numberWithDouble:RNToastNativeLongDuration],
             @"BOTTOM": [NSNumber numberWithInteger:RNToastNativeGravityBottom],
             @"CENTER": [NSNumber numberWithInteger:RNToastNativeGravityCenter],
             @"TOP": [NSNumber numberWithInteger:RNToastNativeGravityTop]
             };
}

RCT_EXPORT_METHOD(show:(NSString *)msg duration:(double)duration  gravity:(nonnull NSNumber *)gravity customStyle:(NSDictionary *)customStyle {
    [self _show:msg duration:duration gravity:gravity.intValue customStyle:customStyle];
});

RCT_EXPORT_METHOD(showWithGravity:(NSString *)msg duration:(double)duration gravity:(nonnull NSNumber *)gravity customStyle:(NSDictionary *)customStyle {
    [self _show:msg duration:duration gravity:gravity.intValue customStyle:customStyle];
});

- (UIViewController *)visibleViewController:(UIViewController *)rootViewController
{
    if (rootViewController.presentedViewController == nil)
    {
        return rootViewController;
    }
    if ([rootViewController.presentedViewController isKindOfClass:[UINavigationController class]])
    {
        UINavigationController *navigationController = (UINavigationController *)rootViewController.presentedViewController;
        UIViewController *lastViewController = [[navigationController viewControllers] lastObject];
        
        return [self visibleViewController:lastViewController];
    }
    if ([rootViewController.presentedViewController isKindOfClass:[UITabBarController class]])
    {
        UITabBarController *tabBarController = (UITabBarController *)rootViewController.presentedViewController;
        UIViewController *selectedViewController = tabBarController.selectedViewController;
        
        return [self visibleViewController:selectedViewController];
    }
    
    UIViewController *presentedViewController = (UIViewController *)rootViewController.presentedViewController;
    
    return [self visibleViewController:presentedViewController];
}

- (void)_show:(NSString *)msg duration:(NSTimeInterval)duration gravity:(NSInteger)gravity customStyle:(NSDictionary *)customStyle {
    dispatch_async(dispatch_get_main_queue(), ^{
        //UIView *root = [[[[[UIApplication sharedApplication] delegate] window] rootViewController] view];
        UIViewController *ctrl = [self visibleViewController:[UIApplication sharedApplication].keyWindow.rootViewController];
        UIView *root = [ctrl view];
        CGRect bound = root.bounds;
        bound.size.height -= _keyOffset;
        if (bound.size.height > RNToastNativeBottomOffset*2) {
            bound.origin.y += RNToastNativeBottomOffset;
            bound.size.height -= RNToastNativeBottomOffset*2;
        }
        UIView *view = [[UIView alloc] initWithFrame:bound];
        view.userInteractionEnabled = NO;
        [root addSubview:view];
        UIView __weak *blockView = view;
        id position;
        if (gravity == RNToastNativeGravityTop) {
            position = CSToastPositionTop;
        } else if (gravity == RNToastNativeGravityCenter) {
            position = CSToastPositionCenter;
        } else {
            position = CSToastPositionBottom;
        }
        [view makeToast:msg
               duration:duration
               position:position
            customStyle:customStyle
                  title:nil
                  image:nil
                  style:nil
             completion:^(BOOL didTap) {
                 [blockView removeFromSuperview];
             }];
    });
}

@end

