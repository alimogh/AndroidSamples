# 平时练习的例子，持续更新中，包含但不限于以下内容
# Kotlin版本请移步：https://github.com/sdwfqin/KotlinAndroidSamples
# 开发环境：Android Studio 3.0
# 详细解释请移步到我的博客：http://www.sdwfqin.com
# 本项目当前共有3个Module

1. app：这是sample大合集，内有多个小例子

    ```
    |- View
        |- View的位置参数
        |- MotionEvent与TouchSlop
        |- GestureDetector
        |- Scroller
        |- View触摸事件分发
        |- 按钮放大（属性动画）
        |- 自定义View（优惠券效果）
        |- SurfaceView
        |- 自定义输入密码
        |- 网易云听歌识曲
    |- RecyclerView
        |- 列表基本用法
        |- 使用VLayout实现复杂列表
        |- 双列表联动
    |- Activity跳转动画
        |- View动画
    |- BottomSheet
        |- BottomSheet
        |- BottomSheetDilog
        |- BottomSheetFragment
    |- PopupWindow
        |- PopupWindow的基本用法
        |- 任意位置长按显示弹窗
    |- SQLite&动态表格
        |- SQLite数据库的简单实用
        |- 使用TableLayout实现动态表格
    |- GridView
        |- 之前一个比赛的项目里摘出来的哈哈
    |- Handler
        |- Handler与Timer
    |- Dagger2
        |- 简单用法，没有全局单例
    |- Retrofit
        |- get
        |- post
        |- Retrofit+Rxjava
    |- Broadcast
        |- 普通广播
        |- 有序广播
        |- 本地广播
    |- SpannableString
        |- 富文本字符串
    |- Canvas
        |- 绘图
    |- AsyncTask
        |- 异步任务（模拟下载）
    |- Service
        |- 不可交互服务
        |- 可交互服务
        |- 前台服务
        |- IntentService
    |- Rxjava
        |- 线程调度
        |- map变换
        |- zip合并
        |- Flowable背压
    |- EventBus
        |- 普通事件
        |- 粘性事件
    |- WebView
        |- js与native交互
    |- Picture
        |- Android7.0拍照
        |- Android相册选图
        |- UCrop剪切图片
    |- 运行时权限
        |- EasyPermissions
    ```

2. mvpseed：这是使用Dagger2+Rxjava2+Retrofit2+mvp架构写的一个小例子

    ```
    |- mvp
    |- Dagger2
    |- RxJava2
    |- Retrofit2
    |- okhttp3
    ```

3. greendaomvp：这是一个使用mvp模式重度分离的例子，使用greendao简化数据库操作

    ```
    |- mvp
    |- Greendao
    |- item侧滑
    ```
    
4. 记：
    
    上学时去写的一些Demo了，现在代码看上去好Low，近期打算找时间稍微优化一下，尽可能的根据阿里规约把代码改一下
    现在还存在如下几个问题：
    
        1. 线程池需要改
        2. popupWindow目前还没做Android N的适配修改

## License

```
Copyright 2016, zhangqin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```