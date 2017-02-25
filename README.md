PLEASE READ UNTIL THE END

This project was built with IntelliJ IDEA. You can run the files
from IDEA by adding the jar files in lib to classpath and running main() in
src/main/java/Launcher. I’ve included external libraries via jar files (I did
not use maven).

You can run the project from the command line by navigating to
out/production/MitchellCodingChallenge and running:

java -cp .:../../../lib/mysql-connector-java-5.1.40-bin.jar main/java/Launcher

To store data, I used MYSQL. You can edit the properties in
src/main/resources/mitchellcc.properties to configure the database
used and the username/password associated with it.

JUnit tests are also included in src/test. If you have IntelliJ you can add
the src/test folder to a test run configuration to run tests.

You can run the tests from the command line by navigating to
out/production/MitchellCodingChallenge and running:

java -cp .:../../../lib/junit-4.12.jar:../../../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore test.java.data.dao.VehicleDAOTest

By editing the command above, you can run different test files.
e.g. test.java.data.utils.DataUtilsTest

As for optional requirements:
Validation of model, make, and year fields are supported.
Filtering of GET operation is also supported.

Thank you!

- Timothy Lam