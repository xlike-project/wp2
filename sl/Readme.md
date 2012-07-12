=Slovene language pipeline=
Maintainer: Tadej Štajner, JSI (tadej.stajner@ijs.si)

==Environment assumptions==
.NET 2.0 (Microsoft or Mono)
Java 1.6+ (Oracle or OpenJDK)
Unix utilities (at least bash, xargs, curl)

==Installation==
Run download.sh to downlad all the software and resources from the published locations.
Web services are not done yet.

==Components==

===Lemmatizer and morphosyntactic tagger===
Language: C#
Platform: .NET 2.0 (Microsoft .NET Runtime or equivalend Mono)
Authors: Miha Grcar, Matjaž Jurišic, Jan Rupnik, Simon Krek
Example CLI usage: ./PosTaggerTag.exe -lem:LemmatizerModel.bin -v -o -t InputPlainText.txt TaggerModel.bin OutputTEIXml.xml
Example web service usage: TODO (tadejs)
Reference: http://oznacevalnik.slovenscina.eu/Vsebine/Sl/ProgramskaOprema/Navodila.aspx

===Named entity recognizer===
Language: Java
Platform: Java 1.6*
Authors: Tadej Štajner, Simon Krek, Tomaž Erjavec
Example CLI usage: java -jar slner-all.jar --in InputTEI.xml --in-model model.ser.gz --out OutputTEI.xml
Example web service usage: TODO (tadejs)

==To do==
* Current communication format between components is TEI. Will support CoNLL and export to SLike schema (tadejs)
* Web services besides CLI (tadejs)
* FFZG alternative NER (tadejs, FFZG)