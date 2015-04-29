# spring-boot-jpa

这是一个简单的基于 spring boot 的DEMO。

Spring Boot 的出现极大提高了生产效率, 尤其是做一些规模不太大的项目，它，太有用了，不用再为繁杂的配置文件烦恼，你只需在 pom 里增加你想依赖的组件就可以了，剩下的就是定义 Entity，声明 Dao，在 Service 层包装一下业务, controller 里去做 WEB 页面展示的准备工作就好了！用起来一切都那么 so easy！

这个DEMO主要演示在 基于 spring boot 的项目开发，如何使用 jpa 对数据库 CRUD 操作，如何用 freemarker 展示页面。

网上很多spring boot 示例很少说如何将基于 spring boot 开发的项目如何部署到 tomcat，很高兴告诉你，这个 DEMO 是可以直接打成 WAR 包发布到 tomcat 容器的。

---
温馨提示： 装一个lombok可以省去你写 getter\setter的时间
