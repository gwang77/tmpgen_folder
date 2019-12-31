SET CLASSPATH=tmpgen.jar;
for %%I in (dir ./lib/*.jar) do call cpappend.bat ./lib/%%I

D:\Java\jdk1.8.0_131\bin\java.exe -Dfile.encoding=utf-8 -classpath %CLASSPATH% TmpGen
