![example workflow](https://github.com/ringcentral/call-performance-analytics-demo-java/actions/workflows/gradle.yml/badge.svg)

# Call Performance Demo Application (Java with Simple HTTP)

This is a Java SE Application that demonstrates how to use RingCentral's Analytics (Call Performance APIs). The resulting JSON is rendered on the standard console. 

This application setup to use RingCentral's Password flow based authentication and it needs to have 'Analytics' permission enabled. Currently private beta API users can request theier application to have this permission by contacting us.

More information about that can be found in the [developer guide.](https://developers.ringcentral.com/guide/analytics)

## PreRequisite:

1. JDK version 11 or newer
2. RingCentral Java SDK (installed via Gradle)
2. Have a RingCentral Application with 'Analytics Permission' in your Developer Console. If not, you can easily create one by signing up for a [free developer account.] (https://developers.ringcentral.com/login.html#/). Make sure the app supports 'password flow' based authentication.
3. Get the RingCentral Application ID & Secret Key from Developer Console. This application uses "Sandbox" credentials but you can also use your "Production" credentials.
4. Update the values for the local.properties file based on the previous step

## Steps to run the Gradle Java App

1. Clone/Download this GitHub Repository
2. Open the project in any IDE such as Visual Studio Code or IDEA
3. Make sure your the local.properties file has all the values for the various fields and also make sure not to expose your Application Credentials publicly. You can even add .env file to your .gitignore file.
4. Run the following commands

```bash
./gradlew run
```
5. Open Console to see the JSON Response shown for both 'Aggregrate' and 'Timeline' data as returned by the two Call Performance APIs.

## Steps to run Tests

The following command will run the JUnit  Tests

```
./gradlew test
```



