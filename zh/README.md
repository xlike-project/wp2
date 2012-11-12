=Chinese language pipeline=
Maintainer: Alex Zhang, THU (zpjumper@gmail.com)

==Environment assumptions==
Java 1.6+ (Oracle or OpenJDK)
Unix utilities (at least bash, xargs, curl)
ANT 1.7+

==Installation==
1. Run download.sh to downlad all the libraries and resources from the 
   published locations, and extract it into a directory.
2. Set TOMCAT_HOME environment variable.
3. Modify the configuration files: src/ICTCLAS.properties, 
   src/parser.properties according to your system.
4. Use ANT tool and build.xml to build project.
5. Copy the dist/*.war file to tomcat webapps directory, access the service
   in browser.

==Components==


==To do==

