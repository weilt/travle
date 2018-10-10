##截屏客户端
###1.模块
 1) HttpClientUtil HTTP请求上传文件
 2) MACAddressUtil 获取本机MAC地址
 3) ScreenshotUtil 截屏工具类
 4) ScreenshotMain 程序主类（内含定时器和配置文件读取）
 
 ###2.打包方法
 1) 将项目打成jar包
 2) 将打好的jar包和jar包相关的依赖包通过exe4j打成exe可执行文件（注意：移除系统自带的JDK环境，用相对路径添加jre）
 3) 将打包好的exe文件和所依赖的运行jre放在同一个目录下同时创建config/screenshot.config的配置文件
 
 ###3.配置
 配置文件路径：
 
 >screenshot
 >  >lib
 >  >src
 
 >config
 >  >screenshot.config
        
 可配参数： 
  1) host 文件上传服务路径(默认http://127.0.0.1:8080/uploads) 
  2) localPath 本地文件生成路径（目前没有用到）       
  3) delay 程序启动后多久执行定时任务（秒）
  4) period 截屏的间隔时间（默认20秒）      
  5) fileCount 多文件上传的文件个数（默认10条）