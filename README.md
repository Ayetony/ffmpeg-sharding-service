# 项目介绍
- [项目介绍](#%e9%a1%b9%e7%9b%ae%e4%bb%8b%e7%bb%8d)
  - 请参考 [FMJ (FFMpeg for Java)](#fmj-ffmpeg-for-java)
  - [FFMpeg安装](#ffmpeg%e5%ae%89%e8%a3%85)
    - [Linux](#linux)
    - [Mac](#mac)
    - [Windows](#windows)
  - [具体的ffmpeg命令可以参考下面的文档](#%e5%85%b7%e4%bd%93%e7%9a%84ffmpeg%e5%91%bd%e4%bb%a4%e5%8f%af%e4%bb%a5%e5%8f%82%e8%80%83%e4%b8%8b%e9%9d%a2%e7%9a%84%e6%96%87%e6%a1%a3)

通过Java调用FFMpeg命令的方式来对音视频进行处理（获取信息、截图等等）。


## FFMpeg安装
[FFMpeg官网](http://ffmpeg.org/)

建议使用 **ffmpeg-2.6.1** 版本

### Linux

`yum install ffmpeg`

`apt-get install ffmpeg`

### Mac

`brew install ffmpeg`

### Windows

1. 可以在[这儿](http://ffmpeg.zeranoe.com/builds/)下载编译好的FFmpeg
2. 解压到 **/path/to/ffmpeg** 
3. 添加 **/path/to/ffmpeg/bin** 到你的环境变量 **PATH** 中。
4. 打开命令行，执行 **ffmpeg -version**

## 具体的ffmpeg命令可以参考下面的文档

[FFMpeg命令介绍](https://github.com/tonydeng/fmj/blob/master/ffmpeg.md)

