#install
cd wechat-common
mvn clean install
cd ../wechat-dao
mvn clean install
cd ../wechat-mvc
mvn clean install
cd ../wechat-message
mvn clean package
cd ../wechat-manage
mvn clean package

#tomcat
cd ..
rm -rf $CATALINA_HOME/webapps/*
cp ./wechat-message/target/wechat-message.war $CATALINA_HOME/webapps/
cp ./wechat-manage/target/wechat-manage.war $CATALINA_HOME/webapps/

#svn
rm ../svn/svn-message/ROOT.war
mkdir -p ../svn/svn-message
cp ./wechat-message/target/wechat-message.war ../svn/svn-message/ROOT.war

rm ../svn/svn-manage/ROOT.war
mkdir -p ../svn/svn-manage
cp ./wechat-manage/target/wechat-manage.war ../svn/svn-manage/ROOT.war
