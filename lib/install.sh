#!/bin/bash

# It is assumed that you have mvn in your path already...

mvn install:install-file -Dfile=master/pom.xml \
                         -DpomFile=master/pom.xml \
                         -DgroupId=com.intere.pom.master \
                         -DartifactId=ter-pom\
                         -Dversion=0.0.1-SNAPSHOT \
                         -Dpackaging=pom \
                         -DcreateChecksum=true


mvn install:install-file -Dfile=process/process-0.0.1-SNAPSHOT.jar \
                         -DpomFile=process/process-0.0.1-SNAPSHOT.pom \
                         -Dsources=process/process-0.0.1-SNAPSHOT-sources.jar \
                         -DgroupId=com.intere.spring \
                         -DartifactId=process \
                         -Dversion=0.0.1-SNAPSHOT \
                         -Dpackaging=jar \
                         -DcreateChecksum=true
