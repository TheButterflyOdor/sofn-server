### 2016年09月29日  下午 2:32:33
> 创建一个公共文件夹,所有组员都有读权限, 从 svn checkout 下来后,
使用此文件夹中的 pom.xml替换最外面的主 pom.xml 文件即可

> 此文件夹下的 html 文件使用浏览器打开即可

### 2016年09月30日  上午 11:17:08
> 添加 common-pom.xml 文件 更新后使用此文件替换 sofn-common 模块下的 pom.xml 文件,并改名为 pom.xml

> 修改原来的 pom.xml 文件为 root-pom.xml 文件,使用此文件替换 sofn 模块下的 pom.xml 文件,并改名为 pom.xml

### 2016年10月08日  下午 5:11:20 dong4j
> 添加热部署插件的使用教程

> html文件要用使用浏览器打开

### 2016年10月09日  下午 5:13:51 dong4j
jrebel的安装文件过大,现在从 svn 删除,如果还有需要的,可以去idea 插件官网下载
或者在我这里拷贝

### 2016年10月12日  上午 9:47:20 dong4j
前端框架地址
https://192.168.21.251:443/svn/CodeRepository/GuoJiaZhuiSuPingTai/BusinessSystem/soft-webfont

前端开发说明放在了前端框架里面的 common-files 文件夹中

最新上传 hosts 文件,可以使用 google 了

windows 用户将此文件去掉后缀 txt, 放在 c:\\windows\system32\drive\etc 目录下

### 2016年10月15日  下午 2:35:42 dong4j
1. 添加项目开发问题文档

### 2016年10月17日  下午 2:25:25 dong4j
1. 添加 sys_demo

### 2016年10月18日  下午 1:55:35 dong4j
1. 更新 项目问题 文档
2. 添加分页查询方法的使用
3. 添加多个StringUtils 工具方法,用来生成测试数据
4. 添加2个分页封装对象

### 2016年10月28日  下午 1:21:44 dong4j
1. 添加多个 jar 包
2. 为每个模块添加不同的 dubbo 监听端口
3. 添加操作日志 aop
4. 需要添加日志的 controller 方法使用

`@SystemControllerLog(description = "描述信息"，operationType="操作说明")`

### 2016年11月02日  上午 12:36:59 dong4j
1. 完善从 redis 中获取当前用户
2. 修改配置文件 添加分页插件配置选项
3. 完善单点系统

- 缓存操作 RedisUtil
- 字符串工具包 StringUtils
- 获取当前用户 WebUtil.getCurrentUser(String token)
- 获取当前用户ID WebUtil.getCurrentUserId(String token)

### 2016年11月03日  上午 9:50:12 dong4j
添加 maven 的 settings.xml 配置文件

### 2016年11月06日  下午 9:22:53 dong4j
添加项目搭建文档,增加 postman 使用方法

## 2016年11月28日  下午 9:46:26 dong4j
1. 使用 logback 替换 log4j
> 日志保存路径在配置文件中修改为自己电脑上的可用路径
2. 修改 settings.xml 文件 使用 taobao 源 加快下载速度
3. 修改 druid 监控 允许所有 ip 都能使用用户名和密码访问
4. 修改 hosts 添加能访问 google 的配置