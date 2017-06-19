*****************************************
说明:
*****************************************
这是一个通过模板生成代码的小程序.
现在, 运用架构中编程, 如使用Struts, EJB, Mybatis 等等, 所有的模块都必须建立相同类型的配置文件和代码文件, 如果手动建立, 则是拷贝一份文件, 改改文件名和参数, 这样做让程序员感到枯燥, 且费时间.
运用这个程序, 将需要建立的文件做成一个模板, 在模板提供一些处理参数和处理过程. 运行这个程序, 所有的代码就会输出. 大大节约时间, 也减少出错概率.
所有需要生成大量类似的文件的地方, 都可以使用这个小工具.

*****************************************
用法:
*****************************************
解压tmpgen.zip到本地目录.
建立template文件, 放到template目录下面.
把所需的参数配置到\config\ParameterConfig.xml. 那么在模板中就可以用这些参数.
如果有必要, 修改tmpgen.properties中配置. 此文件可配置配置文件目录, 输出文件目录, 数据库连接信息等等.
运行tmpgen.bat, 生成的代码就会在out目录中出现.

*****************************************
运行环境:
*****************************************
j2sdk1.5.0或以上版本

*****************************************
发布:
*****************************************
\config\: 配置文件存放目录.
\config\ParameterConfig.xml: 参数的定义, 在处理中需要用到的一些参数, 可在这儿定义. (可在tmpgen.properties中配置)
\template\: 模板存放的目录. (可在tmpgen.properties中配置)
\out\: 通过模板生成的代码存放的目录. (可在tmpgen.properties中配置)
\lib\: 程序需要引用的外部包.
tmpgen.jar: 程序用到的Class.
tmpgen.bat: 执行文件.
cpappend.bat: 执行辅助文件.
readme.txt: 发布信息, 使用指南, Licence说明.

*****************************************
高级用法--二次开发:
*****************************************
该程序核心采用Velocity内核，配置模板的语言采用Velocity模板语言。具体用法可参见
http://jakarta.apache.org/velocity/index.html
在配置文件ParameterConfig.xml中,有一下几种参数：
parameter: 模板文件中可直接使用.
fields.field: 通常用于配置数据库的字段名，以list存在于velocity容器中. (不常使用这个参数, 一般使用beanList参数)
beanList: 可配置这个自定义class, 自定义class可返回需要的Bean的List,模板中可用这个List. 有一下缺省实现:
    database.DBFieldReader: 从数据库读取字段, 返回List<DBField>. 数据库相关信息可在tmpgen.properties中配置. 需要对应的jdbc jar文件.
    database.ExcelDBFieldReader: 从excel读取字段, 返回List<DBField>. Excel文件相关信息可在tmpgen.properties中配置. 需要poi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
    excel.ExceReader: 从excel读取字段, 返回List<Map>, Map的key为excel的第一行. Excel文件相关信息可在tmpgen.properties中配置. 需要poi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
process: 自定义过程,定义后的过程可在模板中使用其方法. 有一下缺省自定义过程:
    process.TmpGenUtil: 主要包含字符串大小写转换和根据fieldName生成方法名等。


*****************************************
Licence: 
*****************************************
This program is Developed by wanggang. It is free for research or study. For business use, must be approved by the author.
Anyone who want get the source code, can contact with the author.
wanggang (gwang_77@163.com)
2005/11/10
