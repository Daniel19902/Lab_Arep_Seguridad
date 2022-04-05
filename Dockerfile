FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000


COPY  /keystrokes     /usrapp/bin/keystores
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arem.controller.LoginService"]