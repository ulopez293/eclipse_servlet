SETTING UP THE APPLICATION
--------------------------

-El nombre de los metodos en el  serviceImpl deben de empezar de la siguiente forma para que se guarden los movimientos en la bitacora:
guardar*
actualizar*
eliminar*

-El metodo de guardar debe de regresar el objeto para obtener el id de lo que se guardo
- Todos los dto deben de extender del GenericDto para implementar el mensaje que se guardara en bitacora

PASOS PARA GENERAR EL WAR
1. Descargar código del git
2. Desde linea de comandos, nos ubicamos en la carpeta smem-web 
3. Escribir lo siguiente: mvn eclipse:eclipse
4. Desde linea de comandos, nos ubicamos en la carpeta smem-core
5. Escribir lo siguiente: mvn eclipse:eclipse
6. Importar los dos modulos a eclipse, desde la opción importar proyecto existente
7. Para generar el war es importante, revisar las rutas del archivo local.properties y log4j.properties para el log del sistema
8. Generar el war desde linea de comandos de la siguiente forma, ubicados en la carpeta smem-web:
mvn -P local clean install  [ambiente local]
mvn -P dev clean install  [ambiente desarrollo]
mvn -P qa clean install  [ambiente qa]
mvn -P prod clean install  [ambiente producción]
