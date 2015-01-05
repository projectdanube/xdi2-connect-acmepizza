<a href="http://projectdanube.org/" target="_blank"><img src="http://projectdanube.github.com/xdi2/images/projectdanube_logo.png" align="right"></a>
<img src="http://projectdanube.github.com/xdi2/images/logo64.png"><br>

This is an "Example Requesting Authority" component for the XDI Connect protocol.

### XDI Connect

This is part of a set of projects related to XDI Connect:
* [xdi2-connect-core](http://github.com/projectdanube/xdi2-connect-core)
* [xdi2-connect-service](http://github.com/projectdanube/xdi2-connect-service)
* [xdi2-connect-auth-service](http://github.com/projectdanube/xdi2-connect-auth-service)
* [xdi2-connect-acmenews](http://github.com/projectdanube/xdi2-connect-acmenews)
* [xdi2-connect-acmepizza](http://github.com/projectdanube/xdi2-connect-acmepizza)

### Information

Requesting Authorities:

* [+acmepizza](https://github.com/projectdanube/xdi2-connect-acmepizza/wiki/acmepizza) / **[+]!:uuid:0b0a38c7-1120-4194-ad4c-2f52acc0e1a5**

### How to build

First, you need to build the main [XDI2](http://github.com/projectdanube/xdi2) and the 
[xdi2-connect-core](http://github.com/projectdanube/xdi2-connect-core) projects.

After that, just run

    mvn clean install jetty:run

Then the Example Requesting Authority is available at

	http://localhost:9204/

### Community

Google Group: http://groups.google.com/group/xdi2
