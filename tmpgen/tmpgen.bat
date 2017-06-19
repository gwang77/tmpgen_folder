SET CLASSPATH=tmpgen.jar;
for %%I in (dir ./lib/*.jar) do call cpappend.bat ./lib/%%I

%JAVA_HOME%\bin\java.exe -classpath %CLASSPATH% TmpGen
