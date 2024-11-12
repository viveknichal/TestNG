WEB AUTOMATION

Steps to run the code
1. Through testng.xml file
2. Through maven commands ( mvn test)
3. Test can be executed in parallel using testng.xml fil

Classes:
1. TestBase class:
	Which has the capability setup method where it requires browser name as a arguments.
2. DriverFactory class:
	Which has the threadlocal driver instances for the parallel running capability

Used different type of locators like xpath, id css and tagName
Added Gitpod file for this Repo 