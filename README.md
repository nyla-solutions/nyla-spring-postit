
PostIt is a spring boot web based mail client.

It uses a user database loaded in Apache Geode/GemFire.

# Startup

Start the Geode "gfsh" tool

	gfsh

First a Geode locator and cache server using Gfsh


	gfsh>start locator --name=local --enable-cluster-configuration
	gfsh>start server --name=server1 --use-cluster-configuration

Create the region that will contain save emails address/contact information in gfsh

	gfsh>create region --name=UserProfile --type=PARTITION_PERSISTENT
	
Configuration Geode PDX to be read serialized
	
	configure pdx --read-serialized=true --disk-store=DEFAULT
	
Restart the	cache server

	gfsh>stop server --name=server1
	gfsh>start server --name=server1 --use-cluster-configuration

	
**Start Spring Boot App**

Set environment variable


	mail.from=me@mail.com
	mail.from.password=encryptedPassword (see nyla.solutions.core.util.Cryption)
	mail.host=mailhost
	mail.port=25
	mail.smtp.auth=true
	mail.smtp.ssl.enable=false

Execute UBER Jar

	java -jar nyla.solutions.net.postit-VERSION.jar
	
Access URL http://localhost:8080