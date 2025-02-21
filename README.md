# Adv-Prog

---

### Name: Andi Muhammad Adlyn Fakhreyza Khairi Putra
### NPM: 2306241713

---

## Deployment

[![Deploy](https://img.shields.io/badge/Live-Demo-blue)](https://decent-wrennie-adlynfakhreyz-1d68e148.koyeb.app/)

---

## Sections:

---

- [Module 1](#module-1)
- [Module 2](#module-2)

---

## Module 1

---

### Reflection 1
After implementing the edit and delete features in this Spring Boot application, I have applied several clean code principles and best practices in software development:

#### Clear and Descriptive Naming
- I ensured that variable and function names clearly reflect their purpose and usage.
- I maintained consistent naming throughout the code.
- I made sure that the names chosen were easy for other developers to understand.

#### Single Responsibility
- I made sure that each function has a single, specific responsibility.
- Business logic is separated into the appropriate layers (Controller, Service, Repository), which enhances both maintainability and readability.

#### Consistency in Implementation
- I followed the same pattern in implementing the edit and delete features as I did for the create feature.
- I kept the code structure consistent, which aids in understanding and maintaining the code.
- I adhered to consistent coding standards across the project.

#### DRY (Don't Repeat Yourself) Principle
- I avoided code duplication by creating reusable functions.
- I used abstraction for commonly used logic and made effective use of interfaces and inheritance.

#### Feature Branch Workflow
- I used separate branches for developing new features, avoiding conflicts by isolating changes.
- This approach also facilitated easier review and testing before merging into the main branch.

However, I realize that there are areas that need improvement, especially in terms of security. Currently, the application is vulnerable to unauthorized access, where any user can delete any product through direct URL access or brute-force attacks. To address this, an authentication and authorization system should be implemented, which will:
- Validate the identity of users
- Restrict access based on product ownership
- Prevent unauthorized deletions through session management

---

### Reflection 2
After implementing unit tests in this project, I gained several important insights:

#### Benefits of Unit Testing:
- Unit testing increases confidence in the quality and correctness of the code.
- It is essential in large-scale software development (enterprise/startup environments).
- It helps catch bugs and errors early in the process.
- It saves development time in the long run.

#### Number of Unit Tests Needed:
- There is no fixed rule for the ideal number of unit tests.
- The need for unit tests depends on the complexity of the program.
- The most important thing is ensuring that tests cover:
  - The main functions of the program
  - Possible edge cases
  - Various usage scenarios

#### Understanding Code Coverage:
- 100% code coverage does not guarantee that the program is free from bugs.
- Code coverage measures how much code is executed during testing, not the correctness of the logic or the quality of the tests.
- It should be combined with other quality assurance methods for a comprehensive evaluation.

#### Functional Test Suite for Product List:
When tasked with creating another functional test suite to verify the number of items in the product list, similar to the previous functional test suite for product creation, I considered several clean code factors:

##### Identified Issues:
- Duplication of setup code and instance variables across test classes.
- Violates the DRY (Don't Repeat Yourself) principle.
- Reduces the maintainability of the code.

##### Proposed Solution:
- Create a base test class that contains:
  - Common setup procedures
  - Frequently used instance variables
  - Reusable utility methods
- Have specific test classes inherit from the base class, focusing only on the unique test cases for that feature.

##### Benefits of This Approach:
- Reduces code duplication.
- Improves maintainability.
- Simplifies the addition of new test cases.
- Ensures consistency across test suites.
- Eases the process of updating setup procedures.

---

## Module 2

---

### Reflection: CI/CD Implementation and Code Quality Fixes

#### Code Quality Issues Fixed and Strategy
During the exercise, I addressed several code quality issues identified by tools like SonarCloud. Below are the issues and the strategies I used to fix them:

1. **Grouping Dependencies by Their Destination**
  - **Issue**: Dependencies in `build.gradle.kts` were not grouped logically, making it harder to maintain and understand.
  - **Fix**: I grouped the dependencies together for better readability and maintainability.
  - **Strategy**: Logical grouping of dependencies improves code organization and makes it easier to manage dependencies in the future.

2. **Using Constructor Injection Instead of Field Injection**
  - **Issue**: Field injection with `@Autowired` was used in `ProductServiceImpl.java` and `ProductController.java`, which is less testable and violates best practices.
  - **Fix**: I replaced field injection with constructor injection.
  - **Strategy**: Constructor injection improves testability, ensures immutability, and aligns with Spring's best practices.

3. **Adding Comments to Empty Methods**
  - **Issue**: Empty methods like `contextLoads()` and `setUp()` lacked explanations, which could confuse future developers.
  - **Fix**: I added nested comments to explain why these methods were empty.
  - **Strategy**: Adding comments ensures clarity and maintains documentation standards.

4. **Removing Unnecessary Exception Declarations**
  - **Issue**: Some Test methods in unit and functional tests unnecessarily declared `throws Exception`.
  - **Fix**: I removed the `throws Exception` declaration since it was not needed.
  - **Strategy**: Removing unnecessary code reduces clutter and improves readability.

---

#### CI/CD Workflow Reflection
In my opinion the current implementation meets the definition of **Continuous Integration (CI)** and **Continuous Deployment (CD)** at least for these reasons:

1. **Continuous Integration (CI)**
  - The CI pipeline (e.g., `ci.yml`) runs unit tests automatically on every push or pull request, ensuring that new changes do not break existing functionality.
  - Static code analysis tools like SonarCloud are integrated into the workflow to detect bugs, vulnerabilities, and maintainability issues early in the development process.
  - The pipeline ensures that the app is tested in a standardized environment (e.g., Java 21 on Ubuntu), reducing inconsistencies and environment-specific issues.

2. **Continuous Deployment (CD)**
  - The deployment process is automated, with changes to the `main` branch triggering a deployment to the PaaS (e.g., Koyeb).
  - The pipeline ensures that only code that passes all tests and quality checks is deployed, preventing broken code from reaching production.
  - Every commit follows the same automated steps, eliminating human errors and ensuring consistency in the deployment process.

3. **Stability and Reliability**
  - If any test fails or if the code quality does not meet the defined standards, the deployment is halted, ensuring that only stable and reliable code is deployed.
  - Tools like Scorecard and SonarCloud ensure that security and code quality are maintained throughout the development lifecycle.

---