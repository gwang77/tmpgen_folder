#### TmpGen
这是一个通过模板生成代码的小工具。可根据数据库表，生成Entity，VO，Service，DAO，Controller等一套代码。模板全部采用velocity语法。



#### 快速入门

- git上下载tmpgen项目。
- 查看tmpgen.properties文件，看数据库连接(db.*)是否正确。如果不正确，改成正确的。
- 设置JAVA_HOME（推荐java8）。(如果未设置)
- 打开\config\ParameterConfig.xml，配置一些参数。主要有：
  - project: 项目名。
  - objectname: 生成的对象名称
  - objectnameLow: 对象首字母小写名称
  - tablename: 数据库表名。
- 命令行运行tmpgen.bat。
- 生成的代码就会在out目录中出现.



#### 其他常用配置(tmpgen.properties)

- 修改模板能路径
```properties
  file.template.path=./template
```

- 修改输出路径
```properties
  file.out.path=./out
```

- 修改配置文件路径
```properties
  file.configuration.file=./config/ParameterConfig.xml
```



#### 开发工具集成

- pom引入

```xml
        <dependency>
            <groupId>tmpgen</groupId>
            <artifactId>tmpgen</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

- 引入并修改配置文件
  - 将tmpgen.properties文件放入resource下面。
  - 修改其中的配置，使其指向正确的地方。

```properties
#Configuration File
file.configuration.file=D:/workspace/config/ParameterConfig.xml

#Configuration for Template and out path
file.template.path=D:/workspace/template/template_kcmp_back
file.out.path=./

#DB Connection - MySQL
db.driver=com.mysql.jdbc.Driver
db.connection=jdbc:mysql://localhost:3306/db_test
db.user=root
db.pwd=root
```

- 运行TmpGen
  - 以Application方式运行TmpGen.class
  - 则输出文件会放入file.out.path配置的目录下面。







#### 已有模板

若想生成不同的代码，可替换模板路径（file.template.path），再生成代码

- /template: 基于基础框架生成项目代码的模板。
- /template_framework: 初始后端项目框架模板。此模板集成了大数据中心基类，使用此模板生成的初始化项目，可使用上面模板（/template）生成代码。



#### 高级用法
- 该程序核心采用Velocity内核，配置模板的语言采用Velocity模板语言。具体用法可参见
  http://jakarta.apache.org/velocity/index.html

- 在配置文件ParameterConfig.xml中,有一下几种参数：
  - parameter: 模板文件中可直接使用.
  - fields.field: 通常用于配置数据库的字段名，以list存在于velocity容器中. (不常使用这个参数, 一般使用beanList参数)
  - beanList: 可配置这个自定义class, 自定义class可返回需要的Bean的List,模板中可用这个List. 有以下缺省实现:
    - database.DBFieldReader: 从数据库读取字段, 返回List<DBField>.    数据库相关信息可在tmpgen.properties中配置. 需要对应的jdbc jar文件.
    - database.ExcelDBFieldReader: 从excel读取字段, 返回List<DBField>. Excel文件相关信息可在tmpgen.properties中配置. 需要poi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
    - excel.ExceReader: 从excel读取字段, 返回List<Map>, Map的key为excel的第一行. Excel文件相关信息可在tmpgen.properties中配置. 需要poi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
  - process: 自定义过程,定义后的过程可在模板中使用其方法. 有一下缺省自定义过程:
    - process.TmpGenUtil: 主要包含字符串大小写转换和根据fieldName生成方法名等。



#### 发布信息:

- \config\: 配置文件存放目录.
- \config\ParameterConfig.xml: 参数的定义, 在处理中需要用到的一些参数, 可在这儿定义. (可在tmpgen.properties中配置)
- \template\: 模板存放的目录. (可在tmpgen.properties中配置)
- \out\: 通过模板生成的代码存放的目录. (可在tmpgen.properties中配置)
- \lib\: 程序需要引用的外部包.
- tmpgen.jar: 程序用到的Class.
- tmpgen.bat: 执行文件.
- cpappend.bat: 执行辅助文件.
- readme.md: 发布信息, 使用指南等.