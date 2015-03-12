#hibernate
cp ./wechat-web/src/main/resources/hibernate.cfg.xml ./wechat-dao/src/test/resources/

#mvn
mvn clean install
sh deploy.sh
