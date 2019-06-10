## 为什么需要权限管理
- 安全性：误操作、人为破坏、数据泄露等
- 数据隔离：不同的权限能看到及操作不同的数据
- 明确职责：运营、客服等不同角色，leader和dev等不同级别


## 权限管理核心
- 用户-权限：人员少，功能固定，或者特别简单的系统
- RBAC（Role-Based Access Control）：用户-角色-权限，都适用
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610090216179-1810988515.png)


## 理想中的权限管理
- 能实现角色级权限：RBAC
- 能实现功能级、数据级权限
- 简单、易操作，能够应对各种需求



## 相关操作界面
- 权限管理界面、角色管理界面、用户管理界面
- 角色和权限关系维护界面、用户和角色关系维护界面



## Spring Security权限管理框架介绍
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610091232234-524751612.png)


## Spring Security常用权限拦截器
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610091849257-1031469268.png)

![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610092744533-346463947.png)

## Spring Security数据库管理
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610092815757-2028596813.png)

```java
public interface UserDetailsService {
	UserDetails loadUserByUsername(String usegname) throws UsernameNotFoundException;
}
```
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610092956959-645360581.png)

![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610093107810-1782649402.png)

## Spring Security权限缓存
- CachingUserDetailsService


## Spring Security优点
```
提供一套安全框架,而且这个框架是可用的
提供了很多用户认证的功能，实现相关接口即可，节约大量开发工作
基于spring，易于继承到spring项目中，且封装了很多方法
```

## Spring Security缺点
```
配置文件多，角色被“编码”到配置文件和源文件中，RBAC不明显
对于系统中用户、角色、权限之间的关系，没有可操作的界面
大数据量不可用
```

## Apache Shiro
### Shiro介绍
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610100614456-541894022.png)

### Shiro架构图
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610100755422-422685195.png)


## 环境搭建及使用
![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610151630439-2026177505.png)

![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610160357116-1375100253.png)

![](https://img2018.cnblogs.com/blog/1231979/201906/1231979-20190610160910880-309566708.png)




