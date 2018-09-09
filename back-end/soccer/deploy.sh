# /bin/sh

docker rmi registry.cn-hangzhou.aliyuncs.com/duqian/soccer-back;

mvn clean package -Dmaven.test.skip=true -P prod dockerfile:build \
 && docker push registry.cn-hangzhou.aliyuncs.com/duqian/soccer-back \
 && ssh aliyun "/root/soccer/update-backend.sh"