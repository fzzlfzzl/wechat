cd wechat-common
mvn clean install
cd ../wechat-dao
mvn clean install
cd ../wechat-mvc
mvn clean install
cd ../wechat-message
mvn clean package
cp ./target/wechat-message.war $CATALINA_HOME/webapps/
cd ../wechat-manage
mvn clean package
cp ./target/wechat-manage.war $CATALINA_HOME/webapps/
cd ..
rm ./svn-message/ROOT.war
cp ./wechat-message/target/wechat-message.war ./svn-message/ROOT.war
rm ./svn-manage/ROOT.war
cp ./wechat-manage/target/wechat-manage.war ./svn-manage/ROOT.war
