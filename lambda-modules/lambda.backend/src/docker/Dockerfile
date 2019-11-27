FROM quay.io/quarkus/centos-quarkus-maven:19.2.1

COPY lambda.backend-runner /project

USER root
RUN chown quarkus /project/lambda.backend-runner && \
    chmod +x /project/lambda.backend-runner

USER quarkus
EXPOSE 8080
CMD /project/lambda.backend-runner -Djava.library.path=${JAVA_HOME}/jre/lib/amd64/libsunec.so -Djavax.net.ssl.trustStore=${JAVA_HOME}/jre/lib/security/cacerts
