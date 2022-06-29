@echo off

if "%~1"=="" (
echo "The LOLCODE program source path argument is missing."
goto :eof
)

%JAVA_HOME%\bin\java -jar "%~dp0jlci.jar" %*

