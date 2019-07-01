# TeamSpeak API
Simple web service to communicate with TeamSpeak server
## Features
* Lazy connection - If the connection has ended with timeout, the connection will be established again exactly when it gets a new command to execute
* Only one instance - All commands are executed on one user to save slots and time (we dont need to login / select virtual server etc. every time when we want execute new command)
* JSON response - TeamSpeak return data in horrible format but if you communicate with this service you have all response in JSON
## Security Hole
This service is dedicated to work in internal network because to keep performance I omitted Auth system so everyone who grained access can give himself admin or sth
## Do Not Use!!!
Some commands are illegal because they cause errors(Generally, these are the ones that change the state of the connection):
* logout
* login
* quit
* servernotifyregister
* servernotifyunregister
* ...
## How To Use
We have 3 endpoints:
* ``/list``
* ``/single``
* ``/empty``

All endpoints are available by GET and POST.
For example:
```
/list?command=clientlist
```
But we need to remember TeamSpeak have specific char encoding for example ``' '`` is ``"\s"``(these are 2 letters i mean '\\' and 's') so if we want do send message to all on server for example ``Hello world`` we ned to use ``sendtextmessage targetmode=3 msg=Hello\sWorld!`` 



If you use bad endpoint for example ``clientlist`` on ``/single`` you execute command but response will bad

## Other
DockerHub https://hub.docker.com/r/taraj2/teamspeak_api

If you want more info about available commands etc. download TeamSpeak server and read included doc
https://www.teamspeak.com/en/downloads/#server