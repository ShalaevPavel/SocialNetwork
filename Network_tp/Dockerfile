FROM tomcat:9.0.7

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/Network_tp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
