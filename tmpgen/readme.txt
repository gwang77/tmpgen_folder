*****************************************
˵��:
*****************************************
����һ��ͨ��ģ�����ɴ����С����.
����, ���üܹ��б��, ��ʹ��Struts, EJB, Mybatis �ȵ�, ���е�ģ�鶼���뽨����ͬ���͵������ļ��ʹ����ļ�, ����ֶ�����, ���ǿ���һ���ļ�, �ĸ��ļ����Ͳ���, �������ó���Ա�е�����, �ҷ�ʱ��.
�����������, ����Ҫ�������ļ�����һ��ģ��, ��ģ���ṩһЩ��������ʹ������. �����������, ���еĴ���ͻ����. ����Լʱ��, Ҳ���ٳ������.
������Ҫ���ɴ������Ƶ��ļ��ĵط�, ������ʹ�����С����.

*****************************************
�÷�:
*****************************************
��ѹtmpgen.zip������Ŀ¼.
����template�ļ�, �ŵ�templateĿ¼����.
������Ĳ������õ�\config\ParameterConfig.xml. ��ô��ģ���оͿ�������Щ����.
����б�Ҫ, �޸�tmpgen.properties������. ���ļ������������ļ�Ŀ¼, ����ļ�Ŀ¼, ���ݿ�������Ϣ�ȵ�.
����tmpgen.bat, ���ɵĴ���ͻ���outĿ¼�г���.

*****************************************
���л���:
*****************************************
j2sdk1.5.0�����ϰ汾

*****************************************
����:
*****************************************
\config\: �����ļ����Ŀ¼.
\config\ParameterConfig.xml: �����Ķ���, �ڴ�������Ҫ�õ���һЩ����, �����������. (����tmpgen.properties������)
\template\: ģ���ŵ�Ŀ¼. (����tmpgen.properties������)
\out\: ͨ��ģ�����ɵĴ����ŵ�Ŀ¼. (����tmpgen.properties������)
\lib\: ������Ҫ���õ��ⲿ��.
tmpgen.jar: �����õ���Class.
tmpgen.bat: ִ���ļ�.
cpappend.bat: ִ�и����ļ�.
readme.txt: ������Ϣ, ʹ��ָ��, Licence˵��.

*****************************************
�߼��÷�--���ο���:
*****************************************
�ó�����Ĳ���Velocity�ںˣ�����ģ������Բ���Velocityģ�����ԡ������÷��ɲμ�
http://jakarta.apache.org/velocity/index.html
�������ļ�ParameterConfig.xml��,��һ�¼��ֲ�����
parameter: ģ���ļ��п�ֱ��ʹ��.
fields.field: ͨ�������������ݿ���ֶ�������list������velocity������. (����ʹ���������, һ��ʹ��beanList����)
beanList: ����������Զ���class, �Զ���class�ɷ�����Ҫ��Bean��List,ģ���п������List. ��һ��ȱʡʵ��:
    database.DBFieldReader: �����ݿ��ȡ�ֶ�, ����List<DBField>. ���ݿ������Ϣ����tmpgen.properties������. ��Ҫ��Ӧ��jdbc jar�ļ�.
    database.ExcelDBFieldReader: ��excel��ȡ�ֶ�, ����List<DBField>. Excel�ļ������Ϣ����tmpgen.properties������. ��Ҫpoi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
    excel.ExceReader: ��excel��ȡ�ֶ�, ����List<Map>, Map��keyΪexcel�ĵ�һ��. Excel�ļ������Ϣ����tmpgen.properties������. ��Ҫpoi-3.12.jar,poi-ooxml-3.12.jar,poi-ooxml-schemas-3.12.jar,xmlbeans-2.6.0.jar
process: �Զ������,�����Ĺ��̿���ģ����ʹ���䷽��. ��һ��ȱʡ�Զ������:
    process.TmpGenUtil: ��Ҫ�����ַ�����Сдת���͸���fieldName���ɷ������ȡ�


*****************************************
Licence: 
*****************************************
This program is Developed by wanggang. It is free for research or study. For business use, must be approved by the author.
Anyone who want get the source code, can contact with the author.
wanggang (gwang_77@163.com)
2005/11/10
