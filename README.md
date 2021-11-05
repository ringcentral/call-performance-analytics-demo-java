# Call Performance Demo Application (Java using SimpleHTTPClient)

This is a straight forward Java SE console application that demonstrates how to use RingCentral's Analytics (Call Performance APIs). The resulting JSON is rendered on the standard console. 

More information about that can be found in the [developer guide.](https://developers.ringcentral.com/guide/analytics)

## PreRequisite:

1. Java IDE and Java SE version 11.x or newer
3. Have a RingCentral Application with 'Analytics Permission' in your Developer Console. If not, you can easily create one by signing up for a [free developer account.] (https://developers.ringcentral.com/login.html#/)
4. Get the RingCentral Application ID & Secret Key from Developer Console. You need the "Production" credentials as that will be used for authentication purpose.

## Steps to run the program

1. Clone/Download this GitHub Repository
2. Open the project in any Java IDE such as IntellJ
3. Create a new file and call it "local.properties". This is where you will store your production credentials locally on our machine. The .gitignore file as part of this project will make sure this file is not checked into the Git repository.

```
cd <repo>
touch local.properties
RINGCENTRAL_CLIENTID = ""
RINGCENTRAL_CLIENTSECRET = ""
RINGCENTRAL_USERNAME = "+"
RINGCENTRAL_PASSWORD = ""
RINGCENTRAL_EXTENSION = ""

```
4. Edit the "CallPerformanceAnalytics.java" file by adding "RC_Token". For more information regarding the same refer to this [guide](https://developers.ringcentral.com/guide/authentication).
5. Compile and Run the program by executing the following commands
6. Open Console to see the JSON Response showing both 'Aggregrate' and 'Timeline' data as returned by the two Call Performance APIs.

