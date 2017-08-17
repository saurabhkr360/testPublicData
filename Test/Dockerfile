#This is an Sample Image
FROM ubuntu

run apt-get update
CMD ["echo", New Image Created"]
ENV JAVA_VER 8
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
run apt-get install -y openjdk-8-jre
RUN update-alternatives --config java
RUN update-alternatives --config javac

RUN echo "export JAVA_HOME=/usr/lib/jvm/java-8-oracle" >> ~/.bashrc
#ENV JDK_HOME /usr/lib/jvm/jdk1.8.0_101
#ENV JAVA_HOME /usr/lib/jvm/jdk1.8.0_101
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

CMD ["/sbin/my_init"]

run apt-get install testng
#ENV TESTNG_HOME /usr/local/TESTNG


#ENV PATH=$PATH:$JAVA_HOME/bin/


#run apt-get install -y default-jre

#entrypoint java-version
#CMD ["java", "-version"]
#CMD ["echo", "apache server"]
#java -version






