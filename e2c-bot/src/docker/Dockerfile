FROM quay.io/quarkus/centos-quarkus-maven:19.2.1

COPY function.zip /project

#USER root
RUN cd /project && \
    unzip function.zip
#    chown quarkus /project/lambda && \
#    chmod +x /project/lambda

#USER quarkus
ENV LAMBDA_TASK_ROOT=/project
EXPOSE 8080
CMD /project/bootstrap