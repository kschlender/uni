@ECHO OFF
D:\!PROGRAMME\Dev-Cpp\MinGW64\bin\gcc -ansi -w -O3 -o %~n1.exe %~n1.c
%~n1
PAUSE