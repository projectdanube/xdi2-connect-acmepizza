<a href="http://projectdanube.org/" target="_blank"><img src="http://projectdanube.github.com/xdi2/images/projectdanube_logo.png" align="right"></a>
<img src="http://projectdanube.github.com/xdi2/images/logo64.png"><br>

 +leshop XDI2 demo [https://leshop.xdi2.org/](https://leshop.xdi2.org/)

A business requesting authority (RA) issues a "connection request" to request a link contract for reading an email address from an individual authorizing authority (AA). This happens via an "XDI Connect" button on a webpage.

### Information

* [Walkthrough](https://github.com/projectdanube/xdi2-connect-leshop/wiki/Walkthrough)
* [Screencast](https://github.com/projectdanube/xdi2-connect-leshop/wiki/Screencast)

### How to build

First, you need to build the main [XDI2](http://github.com/projectdanube/xdi2) and the 
[xdi2-connect-core](http://github.com/projectdanube/xdi2-connect-core) projects.

After that, just run

    mvn clean install jetty:run

Then the Example Requesting Authority is available at

	http://localhost:9204/

### Community

Google Group: http://groups.google.com/group/xdi2
