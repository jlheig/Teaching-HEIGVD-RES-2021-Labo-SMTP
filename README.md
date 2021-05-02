# Teaching-HEIGVD-RES-2021-Labo-SMTP

>Author : Blanc Jean-Luc
>Date : 02.05.2021

## Project's description
This repository allows you to use a java client. This client will use the socket API in order to communicate with an SMTP server to send prank mails to a list of victims.
The application will use some files to setup the prank and the victims. You have access to configuration files to change the prank message, the list of victims aswell as the address and the port used by the client to connect to the SMTP server.
If you wish to use this programm, you need to have Docker and Maven installed.

## Mock Server
### Description
A Mock Server is an SMTP server used to catch the mails sent on a web app without allowing them to reach the mail box.
### Installation
This project contain a Docker directory, this directory contains the necessary files to execute MockMock inside of a Docker container.
To use it, simply execute the 2 scripts : 
```
bash build_image.sh
bash run_image.sh
```
Then you simply need to connect to the web app, to do this simply use a web browser and connect to http://localhost:8282/
Keep in mind that the port to receive the mails is 2525, while the port used to access the mail box of the web app is 8282.
## MailRobot
In this repository you will find a "settings" directory, it contains all the configuration file to make the application work.

### Config.properties file
In this file we can find : 
* The address of the SMTP server
* The port where the mails must be sent to
* The number of groups our prank application have to create, we need at least 3 addresses to create a group
* The addresses to who we send a copy of the mail, if we have multiple addresses seperate them with a ", "

```
smtpServerAddress=localhost
smtpServerPort=2525
numberOfGroups=1
witnessesToCC=jean-luc.blanc2@heig-vd.ch
```

### victims.utf8 file
This file contains the list of victims to who we will pull the prank on. If the file doesn't contain at least 3 addresses per group we want to create or we won't send anything.
We have to write one email address per line. The first person of each group will be considered as the sender of the prank.
### messages.utf8 file
This file contains the messages we will send to the victims.
* The first line must be "Subject: *title*"
* In between, we will have the body of our mail
* The last line must be "=="

## Execution of the app
To execute the app, you simply need to start the jar file, here is the command you need to type : 
```
java -jar target/MailRobot-1.0-SNAPSHOT-launcher.jar
```
If you want to regenerate the jar file in the case you modified the code, simply type this : 
```
mvn package
```

## Class diagram

![](/images/UML_Diagram.JPG)

* MailRobot is the main class of the application, it initializes the ConfigurationManager, the SmtpClient.
* First we use the ConfigurationManager to get the informations from the config files.
* The email addresses we get are all matched to a Person, then form groups using those Person.
* Then we generate a Prank for each group, afterwards we "translate" those Pranks as Messages.
* PrankGenerator then sends the Messages to the SmtpClient who will then send them to the server.



## Discussion between the client and the SMTP server



```
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: Sending message via SMTP
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 220 3618881e028f ESMTP MockMock SMTP Server version 1.4
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250-3618881e028f
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250-8BITMIME
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 354 End data with <CR><LF>.<CR><LF>
mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: Subject:  Vous êtes l'heureux gagnant!

mai 02, 2021 9:16:28 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: Subject: Vous êtes l'heureux gagnant!

Bonsoir,

Vous êtes l'heureux gagnant du concours "Je suis naïf". Vous recevez donc un total de 200'000'000 de dollards.

John
mai 02, 2021 9:16:29 PM ch.heigvd.res.smtp.SmtpClient sendMessage
INFO: 250 Ok
```

Mail received on the MockMock web interface : 

![](/images/MailReceived.JPG)

We can read the mail on the web interface : 

![](/images/Mail1.JPG)

![](/images/Mail2.JPG)

We can also see on the MockMock terminal that the mail was well received : 

![](/images/dockerTerminal.jpg)