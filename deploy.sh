cd ./wechat-message
cp ./target/wechat-message.war $CATALINA_HOME/webapps/
cd ../wechat-manage
cp ./target/wechat-manage.war $CATALINA_HOME/webapps/
cd ..
rm ../svn/svn-message/ROOT.war
cp ./wechat-message/target/wechat-message.war ../svn/svn-message/ROOT.war
rm ../svn/svn-manage/ROOT.war
cp ./wechat-manage/target/wechat-manage.war ../svn/svn-manage/ROOT.war
