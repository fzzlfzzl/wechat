cd ./wechat-message
cp ./target/wechat-message.war $CATALINA_HOME/webapps/
cd ../wechat-manage
cp ./target/wechat-manage.war $CATALINA_HOME/webapps/
cd ..
rm ./svn-message/ROOT.war
cp ./wechat-message/target/wechat-message.war ./svn-message/ROOT.war
rm ./svn-manage/ROOT.war
cp ./wechat-manage/target/wechat-manage.war ./svn-manage/ROOT.war
