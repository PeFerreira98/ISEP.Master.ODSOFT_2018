# FROM tomcat:8-alpine
# FROM tomcat:8-slim
FROM tomcat:8
# use one of 3 above

ADD /build/libs/cmsProject.war /usr/local/tomcat/webapps/cmsproject.war
WORKDIR /usr/local/tomcat/bin
EXPOSE 8080
CMD ["catalina.sh", "run"]