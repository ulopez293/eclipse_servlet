SETTING UP THE APPLICATION
--------------------------

1. Requirements

1. Instalar el jars
mvn install:install-file -Dfile=exporter-1.0.jar -DgroupId=com.exporter -DartifactId=exporter -Dversion=1.0 -D packaging=jar -DgeneratePom=true

mvn install:install-file -Dfile=jxl-1.0.jar -DgroupId=com.jxl -DartifactId=jxl -Dversion=1.0 -D packaging=jar -DgeneratePom=true


-El nombre de los metodos en el  serviceImpl deben de empezar de la siguiente forma:
guardar*
actualizar*
eliminar*

-El metodo de guardar debe de regresar el objeto para obtener el id de lo que se guardo
- Todos los dto deben de extender del GenericDto para implementar el mensaje que se guardara en bitacora


SERVIDOR DE DESARROLLO GLASSFISH 3  (/opt/glassfishv3/) 
10.224.223.127 
Puerto 5858
Puerto 9090
Usuario admin 
Password (sin password)
Dominio: smemdomain

asadmin create-domain --adminport 5858 --instanceport 9090 smemdomain 
user: smemdomainuser
password: sm3md0m41nus3r
