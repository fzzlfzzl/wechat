#hibernate
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-dao/src/test/resources/
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-mvc/src/test/resources/
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-message/src/test/resources/
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-manage/src/test/resources/
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-util/src/main/resources/

#mvn
mvn clean install
sh deploy.sh
