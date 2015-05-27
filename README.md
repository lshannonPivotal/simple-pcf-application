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

# Using the manifest file


