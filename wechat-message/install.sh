mvn clean package
rm -rf $CATALINA_HOME/webapps/*
cp ./target/wechat-message.war $CATALINA_HOME/webapps/
