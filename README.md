wp2
===
WP2 multilingual text processing pipeline

==Guidelines==

* Each language should have a folder with a two-letter code, containing a Readme file, detailing your system requirements, dependencies and a script to initialize the environment.
* Create other directories for things that aren't bound to a single language (like /doc) or shared between languages. 
* You can put all the source in here, or have it in a git submodule or an external repository. 
* If you have binaries, commit them here if they're not big. Otherwise, have them fetched via a download script. 
* An initialization script should take care of fetching dependencies like external software, dependencies or langauge resources.
* If you provide only a service aceess, provide the client API and instructions to use.
