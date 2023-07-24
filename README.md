# GuitarHub
A website designed to sell electric guitars.


IMPORTANTE:
Per far funzionare l'applicazione direttamente su Tomcat si deve aggiungere auna variabile d'ambiente (di sistema) a Windows nel seguente modo:
- Nome = JDK_JAVA_OPTIONS
- Valore= --add-opens java.base/java.time=ALL-UNNAMED
Questo perchè Gson (di Google) ha alcune problematiche con LocalDate di Java.
