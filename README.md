##网络论坛

##资料
[Spring 文档](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[elastic中文社区](https://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Bootstrap全局样式，有栅格系统](https://v3.bootcss.com/css/#grid)
[Github OAuth](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/creating-an-oauth-app/)
[菜鸟教程](https://www.runoob.com/mysql)
[H2数据库](https://www.h2database.com/html/main.html)
[mybatis官网,化S支持制定QL](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
[spring官方文档](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
[thymeleaf，能够处理HTML,XML,JavaScript,CSS和纯文本](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)
##工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com)
[Visual Paradigm](https://www.visual-paradigm.com)
[Okhttp](@GetMapping("/callback"))
[Maven仓库](https://mvnrepository.com/)
[flyway数据库版本管理工具](https://flywaydb.org/)
[flyway配置](https://flywaydb.org/documentation/configuration/configfile.html)
[lombok，@Data](https://projectlombok.org/)
[jquery官网，js](https://jquery.com/)
[postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)
[json](https://jsoneditoronline.org/#left=local.bixije)
##脚本
```sql
create table USER1
(
    ID           INTEGER auto_increment,
    ACCOUNT_ID   CHARACTER VARYING(100),
    NAME         CHARACTER VARYING(50),
    TOKEN        CHARACTER(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER1_PK
        primary key (ID)
);
```
```bash
mvn flyway:migrate
mvn '-Dmybatis.generator.overwrite=true' mybatis-generator:generate

```
