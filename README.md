<a href="http://projectdanube.org/" target="_blank"><img src="http://projectdanube.github.com/xdi2/images/projectdanube_logo.png" align="right"></a>
<img src="http://projectdanube.github.com/xdi2/images/logo64.png"><br>

 +acmenews XDI2 demo [https://acmenews.projectdanube.org/](https://acmenews.projectdanube.org/)

A business requesting authority (RA) requests a link contract for reading an email address from an individual authorizing authority (AA). This happens via a button on a webpage.

### Information

* [Walkthrough](https://github.com/projectdanube/xdi2-connect-acmepizza/wiki/Walkthrough)
* [Screencast](https://github.com/projectdanube/xdi2-connect-acmepizza/wiki/Screencast)

### How to build

First, you need to build the main [XDI2](http://github.com/projectdanube/xdi2) and the 
[xdi2-connect-core](http://github.com/projectdanube/xdi2-connect-core) projects.

After that, just run

    mvn clean install jetty:run

Then the Example Requesting Authority is available at

	http://localhost:9204/

### Community

Google Group: http://groups.google.com/group/xdi2
