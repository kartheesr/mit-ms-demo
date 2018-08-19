Tasty-Dash AngularJS application.
Prerequisites
Node.js install from: https://nodejs.org/

Bower npm install bower -g

Git  Install form https://git-scm.com/download/win

Quick start
To download an app using command /terminal inside desired path you need to apply this steps:
$git clone https://prabakrun@bitbucket.org/kamaldlk/krishna-publication.git
After download you will have the latest version of application.
cd krishna-publication/KPWeb
bower install
Once you install all of the dependencies you need to:
Run http server e.g. python http service or Node http server - remember you need to install python libraries from https://www.python.org/downloads/ or npm install http-server -g
python -m http.server 8000 -- where 8000 is port number
          or
http-server -p 8000 -- where 8000 is port number
then you can access it from localhost URL: 127.0.0.1:8000
Otherwise We can run tomcat Server.
  STEP 1: clone code into tomcat weapps location
  STEP 2: Enter into the Web app location (cd krishna-publication/KPWeb); 
  STEP 3: bower install
  Step 4: Check the appliacation in your browser
          ex: http://<ip>:<port>/krishna-publication/KPWeb