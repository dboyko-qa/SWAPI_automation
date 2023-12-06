# API test automation for SWAPI.dev 

## Cases implemented
1.	Find the film with latest release date.
2.	Using previous response (1) find the tallest person among the characters that were part of that film.
3.	Find the tallest person ever played in any Star Wars film.
4.	Create contract (Json schema validation) test for /people API.

## Tools used
The cases in this project are written in `Java`.\
`Gradle` - is used as a build tool.  \
`JUnit5` - testing framework.\
`REST Assured` - for testing of REST services.\
`Allure Report` - for test results visualisation.\

## How to run
To run all tests with default parameters locally

```bash
gradle clean test
```

To build Allure reports
```bash
gradle AllureServe
```