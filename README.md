# Introduction
This is a simple Spring Boot Rest Application for sample deployment to PCF

# Prerequisites
A PCF Account is required. This can be obtained free of charge by signing up at:
run.pivotal.io

# Creating the binary
To create a jar file to deploy to PCF run install or package with the skip tests flag (for brevity sake so are no tests in this sample).

````shell
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
````

# Configuring the client to deploy
The client can be obtained from run.pivotal.io after logging in to the run.pivotal.io console.
Next the following steps can be executed to set the end point.
````shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf api api.run.pivotal.io
Setting api endpoint to api.run.pivotal.io...
OK

                   
API endpoint:   https://api.run.pivotal.io (API version: 2.27.0)   
User:           lshannon@pivotal.io   
Org:            lshannon-org1   
Space:          development   
````
Next we will set up our credentials to log in.
````shell
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
````
Notice how the Org within the space was selected.

We can now test the CLI to see the apps.
````shell
Luke-Shannons-Macbook-Pro:git lshannon$ cf apps
Getting apps in org toronto-pivotal-meetup / space development as lshannon@pivotal.io...
OK

No apps found
````
We can also see the build packs.
````shell
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
# Pushing the application
For more information on the push command:
http://docs.pivotal.io/pivotalcf/devguide/installcf/whats-new-v6.html#push

# Using the manifest file


