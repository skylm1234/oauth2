FROM registry.cn-zhangjiakou.aliyuncs.com/gejian_zhouqiang/java:1.8-full

COPY ./target/java-pixel*.jar /app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

CMD java -jar  ${JAVA_OPTS} /app.jar