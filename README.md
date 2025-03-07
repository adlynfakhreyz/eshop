# Adv-Prog

### Name: Andi Muhammad Adlyn Fakhreyza Khairi Putra
### NPM: 2306241713

---

## Deployment

[![Deploy](https://img.shields.io/badge/Live-Demo-blue)](https://decent-wrennie-adlynfakhreyz-1d68e148.koyeb.app/)

---

## Sections:

- [Module 1](#module-1)
- [Module 2](#module-2)
- [Module 3](#module-3)
- [Module 4](#module-4)

---

## Module 1

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

## Module 3

### Reflection:

---

#### 1. Principles Applied
In this module, i have applied the SOLID principles to enhance the maintainability and scalability of the codebase. The following principles are implemented:

##### **1. Single Responsibility Principle (SRP)**
- Each controller is responsible only for handling HTTP requests and delegating the business logic to the service layer.
- The `ItemController<T>` abstract class centralizes shared logic for managing different item types (Product, Car) while keeping each concrete controller focused on its specific functionality.

##### **2. Open/Closed Principle (OCP)**
- The `ItemController<T>` base class allows extension through subclassing (e.g., `ProductController` and `CarController`) without modifying the existing logic.
- The controllers define abstract methods like `getListView()` and `getCreateView()`, allowing subclasses to define their behavior without altering the base class.

##### **3. Liskov Substitution Principle (LSP)**
- `ProductController` and `CarController` inherit from `ItemController<T>` and can be used interchangeably where `ItemController<T>` is expected, ensuring consistent behavior.
- The `service.create(product)` and `service.create(car)` calls respect polymorphism, as both rely on the same interface.

##### **4. Interface Segregation Principle (ISP)**
- The `ItemService<T>` service class ensures that controllers interact with specific services tailored to their domain, avoiding a monolithic interface.
- The separation of `CarService` and `ProductService` prevents unnecessary dependencies and keeps service interfaces lightweight.

##### **5. Dependency Inversion Principle (DIP)**
- The controllers depend on abstract services (`ItemService<T>`) rather than concrete implementations, promoting flexibility.
- The `@Autowired` dependency injection ensures that the controllers receive their required services without tightly coupling them to specific implementations.

---
#### 2. Advantages of Applying SOLID Principles

##### **1. Improved Maintainability**
The separation of concerns makes it easier to modify or extend the project.

**Example:** Instead of modifying each controller separately, shared logic is managed in `ItemController<T>`:
```java
public abstract class ItemController<T extends AbstractItem> {
    protected final ItemService<T> service;

    protected ItemController(ItemService<T> service) {
        this.service = service;
    }
    
    protected abstract String getListView();
    protected abstract String getCreateView();
    protected abstract String getEditView();
    protected abstract String getRedirectToList();
    protected abstract String getItemAttributeName();
    protected abstract String getItemsAttributeName();
}
```

##### **2. Enhanced Scalability**
By adhering to OCP and DIP, adding new item types (e.g., Electronics) only requires creating a new controller and service without modifying existing ones.

**Example:** If we introduce `ElectronicsController`, we only need to extend `ItemController<Electronics>` and implement required methods.
```java
@Controller
@RequestMapping("/electronics")
public class ElectronicsController extends ItemController<Electronics> {
    @Autowired
    public ElectronicsController(ElectronicsService service) {
        super(service);
    }

    @Override
    protected String getListView() { return "electronicsList"; }
    @Override
    protected String getCreateView() { return "createElectronics"; }
    @Override
    protected String getEditView() { return "editElectronics"; }
    @Override
    protected String getRedirectToList() { return "redirect:/electronics/list"; }
    @Override
    protected String getItemAttributeName() { return "electronics"; }
    @Override
    protected String getItemsAttributeName() { return "electronicsList"; }
}
```

##### **3. Code Reusability**
Common operations (CRUD) are shared across controllers via `ItemController<T>`, reducing duplication.

---
#### 3. Disadvantages of Not Applying SOLID Principles

##### **1. Code Duplication**
Without an abstract base controller, each entity controller would need to redefine the same CRUD logic repeatedly.

**Example (Without SRP & OCP):**
```java
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "createProduct";
    }
    
    @PostMapping("/create")
    public String createProduct(@ModelAttribute @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "createProduct";
        }
        service.create(product);
        return "redirect:/product/list";
    }
}
```
🔴 **Issue:** If another entity like `Car` is introduced, we must write nearly identical code, leading to redundancy.

##### **2. Difficult to Extend and Modify**
If we don’t use `ItemController<T>`, adding a new entity requires modifying multiple controllers instead of just creating a new subclass.

##### **3. Tight Coupling Between Components**
If controllers depend directly on concrete services (`ProductService` or `CarService`) instead of abstractions, any change in service implementation will force changes in controllers.

---

## Module 4

### Reflection: Test-Driven Development & Refactoring

---

#### 1. Reflecting on TDD Flow
After implementing the Order and Payment features following the Test-Driven Development workflow, I found this methodology extremely useful for creating maintainable and well-designed code. Reflecting on Percival's self-reflective questions:

##### Correctness
- The TDD approach provided immediate feedback on whether our features were working as intended. When implementing the Payment feature with various payment methods (especially the voucher code validation), having tests in place before implementation made it easier to verify the business logic was correctly implemented.
- With TDD, edge cases were identified early. For example, handling invalid statuses in our Order model or validating voucher for payment using voucher methods.
- The tests made it clear what the expected behavior was, reducing ambiguity in the implementation.

##### Maintainability
- The RED-GREEN-REFACTOR cycle encouraged clean code from the beginning. During the refactoring phase, extracting the OrderStatus enum improved maintainability without breaking existing functionality.
- Tests served as documentation that clearly explained the purpose of each class and method, making the codebase more accessible for future developers.
- The separation of concerns between the model, repository, and service layers was reinforced by the test structure, resulting in more modular code.

##### Productive Workflow
- Initially, writing tests first seemed to slow down development, but it quickly became apparent that it prevented rework by catching design issues early.
- Having a test suite made it possible to refactor confidently, knowing that any breaking changes would be immediately flagged.
- The incremental approach of adding one test at a time kept the development process focused and manageable.

#### 2. Evaluating Tests Against F.I.R.S.T. Principles
My unit tests generally adhered to the F.I.R.S.T. principles, though there are still areas for improvement:

##### Fast
- Tests ran quickly as they didn't involve external dependencies like databases.
- The use of Mockito for service layer tests ensured we weren't slowed down by repository implementations.

##### Independent
- Each test method focused on testing a single aspect or behavior.
- Setup code was properly isolated in the @BeforeEach method.
- However, some tests might have been overly coupled to the implementation details, especially in the repository tests.

##### Repeatable
- Tests were designed to run in any environment without external dependencies.
- The use of mocks for dependencies ensured consistent results regardless of the execution environment.

##### Self-Validating
- All tests had clear assertions that either passed or failed without manual interpretation.
- Error messages were descriptive, making it easy to understand what went wrong when tests failed.

##### Timely
- Tests were written before the implementation, following the TDD approach.
- This ensured that code was designed to be testable from the beginning.

For future test development, I should focus on:
1. Ensuring better isolation between tests by avoiding shared state
2. Creating more thorough tests for edge cases, especially for payment validation
3. Structuring tests to be less implementation-dependent to support refactoring
4. Improving test names conciseness and consistency to better describe what functionality is being tested