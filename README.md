# spring-boot-jpa

这是一个简单的基于 spring boot 的DEMO。

Spring Boot 的出现极大提高了生产效率, 尤其是做一些规模不太大的项目，它太好用了，不用再为繁杂的配置文件烦恼，你只需在 pom 里增加你想依赖的组件就可以了，剩下的就是定义 Entity，声明 Dao，在 Service 层包装一下业务, controller 里去做 WEB 页面展示的准备工作就好了！用起来一切都那么 so easy！

这个DEMO主要演示在 基于 spring boot 的项目开发，如何使用 jpa 对数据库 CRUD 操作，如何用 freemarker 展示页面。

网上很多spring boot 示例很少说如何将基于 spring boot 开发的项目如何部署到 tomcat，很高兴告诉你，这个 DEMO 是可以直接打成 WAR 包发布到 tomcat 容器的。

---

温馨提示： 
1、装一个 lombok 插件可以省去你写 getter\setter 的时间，在 IDE 里看 Entity class 的时候至少是正常的
2、Spring Jpa 虽然熟悉了会极大提高生产效率，但有时候用起来不是那么容易，最好是通读一下手册。我对比了其他几个 JPA 的实现方案，比如 Ebean、ActiveJpa，有兴趣的可以试试，不过我更倾向于用户基数大的开源项目，文档多，遇到问题比较容易解决，所以最终还是选择了 Spring Jpa。
