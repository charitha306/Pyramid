# Pyramid Word

This is a web application with a REST API, which could be used to provide a string as input and return a response indicating whether the word is a pyramid word or not. 

Pre-requisits
-----------------
 - Java 1.8
 - Maven
 - Tomcat version 6 or above

Configuration and Installation
-----------------
1) Checkout the Pyramid repository
~~~
git clone https://github.com/charitha306/Pyramid.git
~~~
2) Build the module
~~~
cd Pyramid
mvn clean install
~~~
3) Copy the .war file to tomcat/webapps folder
~~~
cp target/pyramid-word.war CATALINA_HOME/webapps/pyramid-word.war
~~~
4) Start tomcat server
~~~
cd CATALINA_HOME/bin
sh catalina.sh start
~~~

Pyramid REST API
-----------------

Request
~~~
GET /pyramid-word/api/ispyramid?word=<word>
~~~

Response

| Status | Response | 
| --- | --- |
| 200 | {"isPyramid": <true/false>} |
| 400 | {"error": "Request must contain a word" / "Invalid word - must contain only lower case letters"} |
| 500 | Internal server error |
