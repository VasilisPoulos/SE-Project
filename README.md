# Patterns Editor

### Software Engineering Project

## Authors
* Christos Zonios
* Vasilis Poulos
* Sofia Pasoi

## Getting Started

### Project Structure

You can find the code in the /src/ folder.
* The datamodel package is the back end, holding the business logic of the application.
* The gui package holds the Main method and the GUI files.
* the Test package holds the tests for various datamodel classes and methods, as well as integration tests.

In the /out/artifacts/Se_Project_jar you can find an executable .jar file of the application.

### Setting up

You can either:

1. Import the project in Intellij Idea
2. Use the .jar file located in /out/artifacts/SE_Project_jar
3. Compile it yourself:

From the /src/ directory
```sh
javac datamodel/*.java gui/*.java
java gui.Main
```

From the root directory:
```sh
javac src/datamodel/*.java src/gui/*.java
java src/gui.Main
```

### Running Tests

For Intellij, there is an option to run all tests in datamodel.
If you are using the .jar file, compile and run the tests manually.

```sh
shell commands here
ls -l
```