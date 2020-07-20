Pod::Spec.new do |s|
  s.name         = "RNToastNative"
  s.version      = "1.0.1"
  s.summary      = "RNToastNative"
  s.description  = <<-DESC
                  RNToastNative
                   DESC
  s.homepage     = "https://github.com/onemolegames/react-native-toast-native"
  s.license      = "MIT"
  s.author             = { "author" => "remilekun.salami@gmail.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/remiilekun/react-native-toast-native.git", :tag => "master" }
  s.source_files  = "*.{h,m}"
  s.requires_arc = true


  s.dependency "React"

end

  
