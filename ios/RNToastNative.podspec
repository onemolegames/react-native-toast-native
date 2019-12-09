
Pod::Spec.new do |s|
  s.name         = "RNToastNative"
  s.version      = "1.0.0"
  s.summary      = "RNToastNative"
  s.description  = <<-DESC
                  RNToastNative
                   DESC
  s.homepage     = "https://github.com/onemolegames/react-native-toast-native"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNToastNative.git", :tag => "master" }
  s.source_files  = "*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  
