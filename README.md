# This project is composed of 2 samples
- simple-pcf-application: This is a simple Spring Boot Web application that can be used to explore working with PCF and integrating it with a tool like Jenkins
- simple-data-pcf-application: This a data driven Spring Boot Application that can be used to explore binding a service


# simple-pcf-application
## Introduction
This is a simple Spring Boot Rest Application for sample deployment to PCF. Some basic commands with PCF's Command Line Interface (CLI) are also explored.

## Prerequisites
A PCF Account is required. This can be obtained free of charge by signing up at:
run.pivotal.io

## Creating the binary
To create a jar file to deploy to PCF run install or package with the skip tests flag (for brevity sake so are no tests in this sample).

```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ mvn install -DskipTests
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building simple-pcf-application 0.0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ simple-pcf-application ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ simple-pcf-application ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ simple-pcf-application ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ simple-pcf-application ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.17:test (default-test) @ simple-pcf-application ---
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:2.5:jar (default-jar) @ simple-pcf-application ---
[INFO] Building jar: /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/target/simple-pcf-application-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.2.3.RELEASE:repackage (default) @ simple-pcf-application ---
[INFO] 
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ simple-pcf-application ---
[INFO] Installing /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/target/simple-pcf-application-0.0.1-SNAPSHOT.jar to /Users/lshannon/.m2/repository/io/pivotal/fe/pcf/sample/simple-pcf-application/0.0.1-SNAPSHOT/simple-pcf-application-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/pom.xml to /Users/lshannon/.m2/repository/io/pivotal/fe/pcf/sample/simple-pcf-application/0.0.1-SNAPSHOT/simple-pcf-application-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.644 s
[INFO] Finished at: 2015-05-26T23:25:59-04:00
[INFO] Final Memory: 16M/310M
[INFO] ------------------------------------------------------------------------
```

## Configuring the client to deploy
The client can be obtained from run.pivotal.io after logging in to the run.pivotal.io console.
Next the following steps can be executed to set the end point.
```shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf api api.run.pivotal.io
Setting api endpoint to api.run.pivotal.io...
OK

                   
API endpoint:   https://api.run.pivotal.io (API version: 2.27.0)   
User:           lshannon@pivotal.io   
Org:            lshannon-org1   
Space:          development   
```
Next we will set up our credentials to log in.
```shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf login -a https://api.run.pivotal.io
API endpoint: https://api.run.pivotal.io

Email> lshannon@pivotal.io

Password> 
Authenticating...
OK

Select an org (or press enter to skip):
1. Northeast / Canada
2. lshannon-org1
3. toronto-pivotal-meetup

Org> 3
Targeted org toronto-pivotal-meetup

Targeted space development


                   
API endpoint:   https://api.run.pivotal.io (API version: 2.27.0)   
User:           lshannon@pivotal.io   
Org:            toronto-pivotal-meetup   
Space:          development   
```
Notice how the Org within the space was selected.

We can now test the CLI to see the apps.
```shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf apps
Getting apps in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

No apps found
```
We can also see the build packs.
```shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf buildpacks
Getting buildpacks...

buildpack              position   enabled   locked   filename   
ruby_buildpack         1          true      false    ruby-including-unsupported_buildpack-cached-v1.4.1.zip   
nodejs_buildpack       2          true      false    nodejs-including-unsupported_buildpack-cached-v1.3.1.zip   
java_buildpack         3          true      false    java-buildpack-v3.0.zip   
go_buildpack           4          true      false    go-including-unsupported_buildpack-cached-v1.3.1.zip   
liberty_buildpack      5          true      false    liberty_buildpack.zip   
python_buildpack       6          true      false    python-including-unsupported_buildpack-cached-v1.3.1.zip   
php_buildpack          7          true      false    php-including-unsupported_buildpack-cached-v3.2.1.zip   
staticfile_buildpack   8          true      false    staticfile_buildpack-cached-v1.0.0.zip   
binary_buildpack       9          true      false    binary_buildpack-cached-v1.0.0.zip   
Luke-Shannons-Macbook-Pro:git lshannon$ 

````
## Pushing the application
To get the application on to PCF.
````shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf push toronto-meetup-simple-app  -p target/simple-pcf-application-0.0.1-SNAPSHOT.jar 
Creating app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Creating route toronto-meetup-simple-app.cfapps.io...
OK

Binding toronto-meetup-simple-app.cfapps.io to toronto-meetup-simple-app...
OK

Uploading toronto-meetup-simple-app...
Uploading app files from: target/simple-pcf-application-0.0.1-SNAPSHOT.jar
Uploading 527.8K, 93 files
Done uploading               
OK

Starting app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
-----> Downloaded app package (11M)
-----> Java Buildpack Version: v3.0 | https://github.com/cloudfoundry/java-buildpack.git#3bd15e1
-----> Downloading Open Jdk JRE 1.8.0_45 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_45.tar.gz (1.0s)
       Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.3s)
-----> Downloading Spring Auto Reconfiguration 1.7.0_RELEASE from https://download.run.pivotal.io/auto-reconfiguration/auto-reconfiguration-1.7.0_RELEASE.jar (0.0s)

-----> Uploading droplet (55M)

0 of 1 instances running, 1 starting
1 of 1 instances running

App started


OK

App toronto-meetup-simple-app was started using this command `SERVER_PORT=$PORT $PWD/.java-buildpack/open_jdk_jre/bin/java -cp $PWD/.:$PWD/.java-buildpack/spring_auto_reconfiguration/spring_auto_reconfiguration-1.7.0_RELEASE.jar -Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh -Xmx768M -Xms768M -XX:MaxMetaspaceSize=104857K -XX:MetaspaceSize=104857K -Xss1M org.springframework.boot.loader.JarLauncher`

Showing health and status for app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

requested state: started
instances: 1/1
usage: 1G x 1 instances
urls: toronto-meetup-simple-app.cfapps.io
last uploaded: Wed May 27 03:41:28 UTC 2015
stack: cflinuxfs2

     state     since                    cpu    memory         disk           details   
#0   running   2015-05-26 11:42:09 PM   0.0%   467.2M of 1G   131.1M of 1G   
```
## Working with the CLI on the Running Application
To verify it is running the apps command can be used
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf apps
Getting apps in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

name                        requested state   instances   memory   disk   urls   
toronto-meetup-simple-app   started           1/1         1G       1G     toronto-meetup-simple-app.cfapps.io  
```
To start and stop the application
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf stop toronto-meetup-simple-app
Stopping app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf start toronto-meetup-simple-app
Starting app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...

0 of 1 instances running, 1 starting
0 of 1 instances running, 1 starting
1 of 1 instances running

App started


OK

App toronto-meetup-simple-app was started using this command `SERVER_PORT=$PORT $PWD/.java-buildpack/open_jdk_jre/bin/java -cp $PWD/.:$PWD/.java-buildpack/spring_auto_reconfiguration/spring_auto_reconfiguration-1.7.0_RELEASE.jar -Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh -Xmx768M -Xms768M -XX:MaxMetaspaceSize=104857K -XX:MetaspaceSize=104857K -Xss1M org.springframework.boot.loader.JarLauncher`

Showing health and status for app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

requested state: started
instances: 1/1
usage: 1G x 1 instances
urls: toronto-meetup-simple-app.cfapps.io
last uploaded: Wed May 27 03:41:28 UTC 2015
stack: cflinuxfs2

     state     since                    cpu    memory         disk           details   
#0   running   2015-05-26 11:51:47 PM   0.0%   434.3M of 1G   131.1M of 1G      
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ 
```
To scale the application
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf scale toronto-meetup-simple-app -i 2
Scaling app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf scale toronto-meetup-simple-app
Showing current scale of app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

memory: 1G
disk: 1G
instances: 2
```
And finally how to delete the application.
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf delete toronto-meetup-simple-app

Really delete the app toronto-meetup-simple-app?> yes
Deleting app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK
```

## Basic Trouble Shooting

To tail the log for this application.
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf logs toronto-meetup-simple-app
Connected, tailing logs for app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...

2015-05-26T23:46:35.73-0400 [RTR/1]      OUT toronto-meetup-simple-app.cfapps.io - [27/05/2015:03:46:35 +0000] "GET / HTTP/1.1" 200 0 32 "-" "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36" 10.10.2.122:6906 x_forwarded_for:"135.23.188.178" vcap_request_id:7a1e358a-4726-4264-7097-28ca711dcb9a response_time:0.010188244 app_id:18ee4e21-014c-4f6c-8774-01286ce378b4
```
To view the recent log entries (the entire log not shown)
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf logs toronto-meetup-simple-app --recent
Connected, dumping recent logs for app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...

2015-05-26T23:41:17.95-0400 [API/4]      OUT Created app with guid 18ee4e21-014c-4f6c-8774-01286ce378b4
2015-05-26T23:41:19.74-0400 [API/4]      OUT Updated app with guid 18ee4e21-014c-4f6c-8774-01286ce378b4 ({"route"=>"401cb77e-40f0-4e38-a1a6-450ed2ab1272"})
2015-05-26T23:41:32.74-0400 [DEA/38]     OUT Got staging request for app with id 18ee4e21-014c-4f6c-8774-01286ce378b4
2015-05-26T23:41:34.42-0400 [API/6]      OUT Updated app with guid 18ee4e21-014c-4f6c-8774-01286ce378b4 ({"state"=>"STARTED"})
2015-05-26T23:41:34.69-0400 [STG/38]     OUT -----> Downloaded app package (11M)
2015-05-26T23:41:36.10-0400 [STG/0]      OUT -----> Java Buildpack Version: v3.0 | https://github.com/cloudfoundry/java-buildpack.git#3bd15e1
2015-05-26T23:41:37.42-0400 [STG/0]      OUT -----> Downloading Open Jdk JRE 1.8.0_45 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_45.tar.gz (1.0s)
2015-05-26T23:41:38.72-0400 [STG/0]      OUT        Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.3s)
2015-05-26T23:41:38.82-0400 [STG/0]      OUT -----> Downloading Spring Auto Reconfiguration 1.7.0_RELEASE from https://download.run.pivotal.io/auto-reconfiguration/auto-reconfiguration-1.7.0_RELEASE.jar (0.0s)
2015-05-26T23:41:40.34-0400 [STG/0]      ERR 
2015-05-26T23:41:48.30-0400 [STG/38]     OUT -----> Uploading droplet (55M)
2015-05-26T23:41:59.56-0400 [DEA/38]     OUT Starting app instance (index 0) with guid 18ee4e21-014c-4f6c-8774-01286ce378b4
2015-05-26T23:42:04.29-0400 [App/0]      OUT 
2015-05-26T23:42:04.29-0400 [App/0]      OUT   .   ____          _            __ _ _
2015-05-26T23:42:04.29-0400 [App/0]      OUT  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
2015-05-26T23:42:04.29-0400 [App/0]      OUT ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
2015-05-26T23:42:04.29-0400 [App/0]      OUT  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
2015-05-26T23:42:04.29-0400 [App/0]      OUT   '  |____| .__|_| |_|_| |_\__, | / / / /
2015-05-26T23:42:04.29-0400 [App/0]      OUT  =========|_|==============|___/=/_/_/_/
2015-05-26T23:42:04.29-0400 [App/0]      OUT  :: Spring Boot ::        (v1.2.3.RELEASE)
2015-05-26T23:42:04.41-0400 [App/0]      OUT 2015-05-27 03:42:04.407  INFO 29 --- [           main] pertySourceApplicationContextInitializer : Adding 'cloud' PropertySource to ApplicationContext
2015-05-26T23:42:04.49-0400 [App/0]      OUT 2015-05-27 03:42:04.496  INFO 29 --- [           main] nfigurationApplicationContextInitializer : Adding cloud service auto-reconfiguration to ApplicationContext
2015-05-26T23:42:04.51-0400 [App/0]      OUT 2015-05-27 03:42:04.519  INFO 29 --- [           main] i.p.fe.pcf.sample.SimplePcfApplication   : Starting SimplePcfApplication on 18mosst7b8t with PID 29 (/home/vcap/app started by vcap in /home/vcap/app)
2015-05-26T23:42:04.59-0400 [App/0]      OUT 2015-05-27 03:42:04.595  INFO 29 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@23774975: startup date [Wed May 27 03:42:04 UTC 2015]; root of context hierarchy
2015-05-26T23:42:04.95-0400 [App/0]      OUT 2015-05-27 03:42:04.955  WARN 29 --- [           main] .i.s.PathMatchingResourcePatternResolver : Skipping [/home/vcap/app/.java-buildpack/spring_auto_reconfiguration/spring_auto_reconfiguration-1.7.0_RELEASE.jar] because it does not denote a directory

````
For more information on the the CLI commands:
http://docs.pivotal.io/pivotalcf/devguide/installcf/whats-new-v6.html

## Using the manifest file
A manifest file can describe everything PCF needs to know about your application in one location.
```shell
---
applications:
- name: toronto-meetup-simple-app
  memory: 256M
  instances: 2
  host: toronto-meetup-simple-app
  path: target/simple-pcf-application-0.0.1-SNAPSHOT.jar
```
With this file in the root of the project a simple 'cf push' will tell the CLI to use the manifest file.
```shell
Luke-Shannons-Macbook-Pro:simple-pcf-application lshannon$ cf push
Using manifest file /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/manifest.yml

Updating app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Using route toronto-meetup-simple-app.cfapps.io
Uploading toronto-meetup-simple-app...
Uploading app files from: /Users/lshannon/Documents/git/simple-pcf-application/simple-pcf-application/target/simple-pcf-application-0.0.1-SNAPSHOT.jar
Uploading 527.8K, 93 files
Done uploading               
OK

Stopping app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Starting app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
-----> Downloaded app package (11M)
-----> Downloaded app buildpack cache (44M)
-----> Java Buildpack Version: v3.0 | https://github.com/cloudfoundry/java-buildpack.git#3bd15e1
-----> Downloading Open Jdk JRE 1.8.0_45 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_45.tar.gz (found in cache)
       Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.2s)
-----> Downloading Spring Auto Reconfiguration 1.7.0_RELEASE from https://download.run.pivotal.io/auto-reconfiguration/auto-reconfiguration-1.7.0_RELEASE.jar (found in cache)

-----> Uploading droplet (55M)

0 of 2 instances running, 2 starting
2 of 2 instances running

App started


OK

App toronto-meetup-simple-app was started using this command `SERVER_PORT=$PORT $PWD/.java-buildpack/open_jdk_jre/bin/java -cp $PWD/.:$PWD/.java-buildpack/spring_auto_reconfiguration/spring_auto_reconfiguration-1.7.0_RELEASE.jar -Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh -Xmx160M -Xms160M -XX:MaxMetaspaceSize=64M -XX:MetaspaceSize=64M -Xss853K org.springframework.boot.loader.JarLauncher`

Showing health and status for app toronto-meetup-simple-app in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

requested state: started
instances: 2/2
usage: 256M x 2 instances
urls: toronto-meetup-simple-app.cfapps.io
last uploaded: Wed May 27 04:01:07 UTC 2015
stack: cflinuxfs2

     state     since                    cpu    memory           disk           details   
#0   running   2015-05-27 12:01:52 AM   0.0%   248.3M of 256M   131.1M of 1G      
#1   running   2015-05-27 12:01:54 AM   0.0%   224.6M of 256M   131.1M of 1G 
```
## Integrating with Jenkins
Although there is a plugin for CF available for Jenkins, it did not work out of the box using the manifest file. More exploration needs to be done on this plugin. In the meantime if the CF CLI is installed on the server running Jenkins, a Jenkins job can easily add a step to deploy the application for testing.

In Post Build Steps, a shell script like the following can be executed:
```shell
export CF_HOME=`pwd`
cf login -a api.run.pivotal.io -u lshannon@pivotal.io -p $CF_PASSWORD -o toronto-pivotal-meetup -s development
cf push $BUILD_NUMBER-test -p simple-pcf-application/target/simple-pcf-application-0.0.1-SNAPSHOT.jar
```
$CF_PASSWORD is a Build Parameter created for this job. $BUILD_NUMBER is a built in Jenkins variable that is incremented with each build.

The result of this is, if the code is successfully built with Maven, the artifact is pushed to PCF using the manifest file resulting in a fully configured and running application.

# simple-data-pcf-application
Similar to above, this is a Spring Boot Web application. However this application takes a String value through a GET request and stores it in a MySQL DB. It also provides an end point to get all the messages in the DB.

Thanks to Spring Boot's JDBC starter, the connection details are specified in the application.properties file, along with the SQL.

```javascript
spring.datasource.url=jdbc:mysql://localhost:3306/messages
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
insert.message=INSERT INTO message (message) VALUES (?)
select.message=SELECT message FROM message
schema.message=CREATE TABLE IF NOT exists message (id int(11) NOT NULL AUTO_INCREMENT, message varchar(45) DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
```

For local testing a MySQL Database is required with a DB named messages created.

Upon starting the application locally, a value can be added like this:
http://127.0.0.1:8080/set?message=ice cube

Values can be looked up like this:
http://127.0.0.1:8080/get

The manifest file contains the following configuration:
```javascript
applications:
- name: simple-data-pcf-application
  memory: 512M
  path: target/simple-data-pcf-application-0.0.1-SNAPSHOT.jar
  host: simple-data-pcf-application
services:
- mysql-datasource
```
A MySQL Service called 'mysql-datasource' needs to be created in the organization and space that the CLI is pointing too.

With this manifest a simple 'cf push' will result in the following output:
```shell
piv-wifi-19-185:simple-data-pcf-application lshannon$ cf push
Using manifest file /Users/lshannon/Documents/git/simple-pcf-application/simple-data-pcf-application/manifest.yml

Updating app simple-data-pcf-application in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Using route simple-data-pcf-application.cfapps.io
Uploading simple-data-pcf-application...
Uploading app files from: /Users/lshannon/Documents/git/simple-pcf-application/simple-data-pcf-application/target/simple-data-pcf-application-0.0.1-SNAPSHOT.jar
Uploading 574.6K, 96 files
Done uploading               
OK
Binding service mysql-datasource to app simple-data-pcf-application in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Stopping app simple-data-pcf-application in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

Starting app simple-data-pcf-application in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
-----> Downloaded app package (13M)
-----> Downloaded app buildpack cache (44M)
-----> Java Buildpack Version: v3.0 | https://github.com/cloudfoundry/java-buildpack.git#3bd15e1
-----> Downloading Open Jdk JRE 1.8.0_45 from https://download.run.pivotal.io/openjdk/trusty/x86_64/openjdk-1.8.0_45.tar.gz (found in cache)
       Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.3s)
-----> Downloading Spring Auto Reconfiguration 1.7.0_RELEASE from https://download.run.pivotal.io/auto-reconfiguration/auto-reconfiguration-1.7.0_RELEASE.jar (found in cache)

-----> Uploading droplet (57M)

0 of 1 instances running, 1 starting
1 of 1 instances running

App started


OK

App simple-data-pcf-application was started using this command `SERVER_PORT=$PORT $PWD/.java-buildpack/open_jdk_jre/bin/java -cp $PWD/.:$PWD/.java-buildpack/spring_auto_reconfiguration/spring_auto_reconfiguration-1.7.0_RELEASE.jar -Djava.io.tmpdir=$TMPDIR -XX:OnOutOfMemoryError=$PWD/.java-buildpack/open_jdk_jre/bin/killjava.sh -Xmx382293K -Xms382293K -XX:MaxMetaspaceSize=64M -XX:MetaspaceSize=64M -Xss995K org.springframework.boot.loader.JarLauncher`

Showing health and status for app simple-data-pcf-application in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

requested state: started
instances: 1/1
usage: 512M x 1 instances
urls: simple-data-pcf-application.cfapps.io
last uploaded: Wed May 27 21:20:38 UTC 2015
stack: cflinuxfs2
```
PCF binds this application to the DB, binding in all relevant parameters at run time. The application runs in PCF, just as it did locally, with a simple push command.
