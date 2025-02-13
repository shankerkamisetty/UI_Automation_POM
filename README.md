# Automation Framework

## Overview

This is a **Data-Driven Test Automation Framework** built using **Java and Selenium**. This framework follows **Page
Object Model (POM)** design pattern. It is designed to execute automated test scripts efficiently and supports running
tests on both **local and cloud platforms (LambdaTest)**. The framework uses **TestNG** for test execution and
generating reports using **Extent Reports**. Logging is managed with **Log4j2**, and test data can be ingested from
multiple formats, including **JSON, CSV, and Excel**.

## 🚀 About Me

Hi, My Name is Shanker Kamisetty and I have 9 years of experience in Automation Testing using technologies like Selenium
Webdriver, RestAssured, RestSharp.

## Authors

- [@shankerkamisetty](https://github.com/shankerkamisetty)
- Email Address: kamisettyj@gmail.com

## 🔗 Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/shanker-kamisetty/)

## Tech Stack Used in this Project

This project leverages the following technologies:

| Technology                | Purpose                                            |
|---------------------------|----------------------------------------------------|
| **Java**                  | Core programming language for the framework.       |
| **Selenium**              | Used for browser automation.                       |
| **Selenium Grid**         | Used to execute tests in a Selenium Grid.          |
| **TestNG**                | Test execution and reporting.                      |
| **Maven**                 | Build and dependency management.                   |
| **Maven Surefire Plugin** | Enables running tests via CLI.                     |
| **Extent Reports**        | Generates HTML test reports.                       |
| **Log4j2**                | Captures logs during test execution.               |
| **Jackson Library**       | Parses JSON test data.                             |
| **OpenCSV**               | Parses CSV test data.                              |
| **Apache POI**            | Reads and writes Excel test data.                  |
| **Faker**                 | Generates random test data dynamically.            |
| **GitHub Actions (GHA)**  | CI/CD for running tests automatically.             |
| **LambdaTest**            | Cloud-based execution of automated tests.          |
| **Jenkins**               | Execute tests in Jenkins within a Docker Container |

## Features

- Supports **Data-Driven Testing** using JSON, CSV, and Excel.
- Can run tests **locally and on LambdaTest**.
- Supports **multiple browsers**: Chrome, Firefox, Edge.
- Configurable to run tests in **Headless Mode**.
- Generates **detailed logs** using Log4j2.
- **Maven CLI execution** for flexibility.
- **CI/CD integration** with GitHub Actions and Jenkins
- **Comprehensive test reporting** using Extent Reports.

## Folder Structure

```
.
├── .github                                     # GitHub Actions Configuration
├── config                                      # Configurations
│   ├── DEV.properties
│   ├── QA.properties
│   ├── UAT.properties
│   └── config.json
├── docker
│   └── docker-compose-jenkins-seleniumGrid.yml # Docker-Compose YAML File
├── logs                                        # Execution logs
│   └── test-execution.log
├── src
│   ├── main
│   │   ├── java
│   │   └── resources
│   └── test
│       ├── java
│       │   └── com
│       │       ├── constants                   # Enums
│       │       │   ├── Browser.java
│       │       │   └── Env.java
│       │       ├── ui
│       │       │   ├── dataproviders           # DataProvider classes
│       │       │   ├── listeners               # TestNG listeners
│       │       │   ├── pages                   # Page classes
│       │       │   ├── pojo                    # POJO classes
│       │       │   └── tests                   # Test scripts 
│       │       │   └── utility                 # Utility classes 
│       └── resources
│           ├── test-data                       # Data sources (JSON, CSV, Excel)
│           │   ├── LoginData.csv
│           │   ├── LoginData.xlsx
│           │   └── loginData.json
│           └── log4j2.xml                      # Logging Resource
├── README.md                                   # Project documentation
├── pom.xml                                     # Maven dependencies & configurations
├── test-results-report.html                    # HTML Test Report
└── testng.xml                                  # TestNG file
```

## Prerequisites

Ensure you have the following installed before running the tests:

- **Java JDK 17 or higher**
- **Maven 3.9.8 or later**
- **Chrome/Firefox/Edge browser (if running locally)**
- **Git** (for cloning the repository)
- **Docker Desktop**

#### Prerequisite steps for tests to run in lambdaTest:

- Create an account in Lambda Test
- Navigate to Web Automation
- Build a demo application to get the user and access key

## Setup Instructions:

### Clone the Repository:

```sh
  git clone https://github.com/shankerkamisetty/UI_Automation_POM.git

  cd UI_Automation_POM
```

### Demo Site Used for Testing: http://www.automationpractice.pl/index.php

## Running the Tests

You can execute the tests using Maven from the command line.

### Running Locally

```sh
mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DrunInDockerContainer=false -DisHeadless=false
```

- `-Dbrowser` : Specifies the browser (`chrome`, `firefox`, `edge`).
- `-DisLambdaTest` : Set `true` to run on LambdaTest, `false` to run locally.
- `-runInDockerContainer` : Set `true` to run on docker container with Jenkins and SeleniumGrid containers, `false` to
  either run locally or in LambdaTest.
- `-DisHeadless` : Set `true` for headless execution, `false` for UI mode.

### Running on LambdaTest Platform

```sh
mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DrunInDockerContainer=false -DisHeadless=true
```

### Running Tests in a Docker Container: Jenkins and Selenium Grid

#### Setup Docker Containers

- Make sure that the Docker is running.
- Open terminal and navigate to the project directory and change directory to docker as below

```
cd docker
```

- Run the docker-compose command to run the containers in docker that were configured
  in `docker-compose-jenkins-seleniumGrid.yml` file as below

```
docker-compose -f docker-compose-jenkins-seleniumGrid.yml up -d
```

#### Setup Jenkins

- Navigate to `http://localhost:8080/`
- Add the below list of plugins to install in Jenkins:
    - Docker
    - Docker Pipeline
    - Html Publisher
    - Maven Integration
    - GitHub Integration
    - Test Result Analyser
    - TestNG results
- Add Maven Installation in Jenkins Tools.
- Create a new Maven Project and Configure the maven project with:
    - Git: Set the repository URL with HTTPS
        - Add a new Private Access Token in git repo and use the token as password for the git credentials.
    - Build: set `Root POM` to `pom.xml` and set the below goals and Save the Project.

```
clean test -Dbrowser=chrome -DisLambdaTest=false -DrunInDockerContainer=true -DisHeadless=true -X
```

#### Running Tests

- While running the tests locally, the HUB_URL in SeleniumGridUtility should point to `http://localhost:4444/wd/hub` and
  then run the below command

```sh
mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DrunInDockerContainer=false -DisHeadless=true
```

- While running tests remotely, inside the docker container, the HUB_URL in SeleniumGridUtility should point to `http://selenium-hub:4444/wd/hub`
    - Since Jenkins and SeleniumGrid are in the same docker network, Jenkins will be able to access the above remote
      HUB_URL.
- Build the project in Jenkins
- When build/test run is complete, make sure to run the docker-compose `down` command as below

```
docker-compose -f docker-compose-jenkins-seleniumGrid.yml down --remove-orphans
```

## Test Reports

After execution, test reports will be available:

#### Locally at:

```
./test-results-report.html
```

#### GitHub Pages at:

```
https://shankerkamisetty.github.io/UI_Automation_POM/test-results-report.html
```

Open the generated HTML file in a browser to view the detailed report. The report contains information on test cases
executed, passed, failed and skipped along with the screenshots for failed tests.

## Logs

Execution logs are captured locally the below directory, Check this directory for debugging information.:

```
/logs/
```

## Integrated with GitHub Actions

- This framework is integrated with GitHub actions. The workflow configurations are set in `gha-maven.yml` file.
- Upon every pull request to merge to master or push to master branch the GHA workflow will trigger and the tests will
  be run in headless mode.
- Upon each workflow run, there is a deploy action that uses `uses: JamesIves/github-pages-deploy-action@v4.7.2`
    - This will publish the report for a user to access the test reports directly in GitHub Pages.
    - The token used at this `Deploy` is another Private Access Token(PAT) created in the git repo that has access to
      repo.



