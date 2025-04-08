@echo off

set APP_NAME=ETU003112
set ASSETS=assets
set SRC_DIR=src\main\java\com\itu
set WEB_DIR=src\main\webapp
set BUILD_DIR=build
set LIB=lib
set TOMCAT_WEBAPPS=D:\ProgramFiles\apache-tomcat-10.1.28\webapps
set SERVLET_API_JAR=%LIB%\servlet-api.jar

:: Nettoyage et creation du repertoire temporaire (build)
rmdir /S /Q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes
mkdir %BUILD_DIR%\WEB-INF\lib
mkdir %BUILD_DIR%\assets\css
mkdir %BUILD_DIR%\assets\fonts
copy "%LIB%\*.jar" "%BUILD_DIR%\WEB-INF\lib%" /y >nul
copy "%ASSETS%\*" "%BUILD_DIR%\assets\%" /y >nul
copy "%ASSETS%\css\*" "%BUILD_DIR%\assets\css" /y >nul
copy "%ASSETS%\fonts\*" "%BUILD_DIR%\assets\fonts" /y >nul

:: Compilation des fichiers java avec le JAR des servlets
:: --Trouver tous les fichiers .java et les écrire dans sources.txt
dir /B /S %SRC_DIR%\*.java > sources.txt

:: Vérification si sources.txt est vide
if not exist sources.txt (
    echo Aucun fichier .java trouvé dans %SRC_DIR%.
    exit /B
)

:: --Compiler les fichiers Java
javac -cp %LIB%\* -d %BUILD_DIR%\WEB-INF\classes @sources.txt
if %ERRORLEVEL% neq 0 (
    echo Erreur lors de la compilation des fichiers Java.
    exit /B
)

:: --Supprimer le fichier sources.txt
del sources.txt

:: Copier les fichiers web (web.xml, JSP, etc)
xcopy %WEB_DIR%\* %BUILD_DIR%\ /E /H /I

:: Changer de répertoire vers BUILD_DIR
cd %BUILD_DIR% || (
    echo Impossible de changer de répertoire vers %BUILD_DIR%.
    exit /B
)

:: Créer le fichier .war
jar -cvf %APP_NAME%.war *
if %ERRORLEVEL% neq 0 (
    echo Erreur lors de la création du fichier .war.
    exit /B
)

:: Revenir au répertoire précédent
cd ..

:: Déploiement vers Tomcat
copy /Y %BUILD_DIR%\%APP_NAME%.war %TOMCAT_WEBAPPS%
if %ERRORLEVEL% neq 0 (
    echo Erreur lors du déploiement vers Tomcat.
    exit /B
)

echo.
echo Deploiement termine. Redemarrez Tomcat si nécessaire.
echo.
