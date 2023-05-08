# Foodics API Test Suite

This is a Java-based API test suite that uses the Rest Assured library to test the endpoints of the Foodics API. The test suite includes test cases for the /cp_internal/login and /cp_internal/whoami endpoints.

Getting Started
To use this test suite, you will need to have the following software installed on your system:

Java Development Kit (JDK) version 8 or higher
Maven build tool
Rest Assured library
Usage
To run the test suite, follow these steps:

Clone this repository to your local machine.
Update the BASE_URL, USERNAME, and PASSWORD constants in the FoodicsAPITest.java file with the appropriate values for your system.
Open a command prompt or terminal window in the root directory of the project.
Run the following command to build the project and run the test suite:
Copy
mvn clean test
The test suite will run, and the results will be displayed in the console window.

Test Cases
The test suite includes the following test cases:

testLogin(): Sends a POST request to the /cp_internal/login endpoint with the username and password, and verifies that the response code is 200 OK and that the response header contains a valid access token.
testWhoAmI(): Sends a GET request to the /cp_internal/whoami endpoint with a valid access token, and verifies that the response code is 200 OK and that the response body contains the expected username.
testInvalidToken(): Sends a GET request to the /cp_internal/whoami endpoint with an invalid access token, and verifies that the response code is 401 Unauthorized.
