# 🧪 Test Automation Framework Documentation

> **Note**: This document provides comprehensive guidance on the test automation framework, its components, and how to work with it.

## 📋 Table of Contents
1. [Framework Overview](#-framework-overview)
2. [Architecture](#-architecture)
3. [Test Execution Flow](#-test-execution-flow)
4. [Available Test Cases](#-available-test-cases)
5. [Test Management](#-test-management)
6. [Configuration](#-configuration)
7. [Running Tests](#-running-tests)
8. [Test Data Management](#-test-data-management)

## 🏗️ Framework Overview

This is a robust Selenium WebDriver based test automation framework built with modern technologies:

| Component | Technology | Purpose |
|-----------|------------|---------|
| **Core Framework** | Java 11+ | Base programming language |
| **Dependency Injection** | Spring Boot 3.x | Application context and bean management |
| **Testing Framework** | TestNG 7.6+ | Test execution and reporting |
| **Browser Automation** | Selenium WebDriver 4.x | Web UI automation |
| **Page Objects** | Page Factory | Web element management |
| **Driver Management** | WebDriverManager | Automated browser driver setup |
| **Build Tool** | Maven | Dependency and build management |

## 🏛️ Architecture

```mermaid
graph TD
    A[Test Suites] --> B[Test Classes]
    B --> C[Test Methods]
    C --> D[Page Objects]
    D --> E[Web Elements]
    F[Test Data] --> C
    G[Configuration] -->|Provides| C
    G -->|Provides| D
    
    style A fill:#f9f,stroke:#333,stroke-width:2px
    style B fill:#bbf,stroke:#333,stroke-width:2px
    style C fill:#9f9,stroke:#333,stroke-width:2px
    style D fill:#ff9,stroke:#333,stroke-width:2px
    style E fill:#f99,stroke:#333,stroke-width:2px
    style F fill:#9cf,stroke:#333,stroke-width:2px
    style G fill:#c9f,stroke:#333,stroke-width:2px
```

## 🔄 Test Execution Flow

### Test Execution Pipeline

```mermaid
sequenceDiagram
    participant T as TestNG Suite
    participant S as Suites.xml
    participant C as Test Class
    participant M as Test Method
    participant P as Page Objects
    
    T->>S: 1. Load test suite
    S->>C: 2. Initialize test class
    C->>C: 3. @BeforeMethod setup
    loop For each test method
        C->>M: 4. Execute test method
        M->>P: 5. Interact with page objects
        P-->>M: 6. Return results
        M-->>C: 7. Assert results
    end
    C->>C: 8. @AfterMethod teardown
```

### Key Components

1. **Test Suite Initialization**
   - Execution starts from `Suites.xml`
   - Includes other suite files (e.g., `Element-Tests.xml`)
   - Parallel execution with thread count: 5
   - Default test name: "Test"

2. **Test Class Lifecycle**
   - `@BeforeMethod`: Initializes Spring context and WebDriver
   - `@Test`: Individual test methods with assertions
   - `@AfterMethod`: Cleans up WebDriver instances

3. **Page Object Model**
   - Each page has its own class
   - Elements are located using `@FindBy` annotations
   - Page methods encapsulate interactions

## ✅ Available Test Cases

### 🚀 Active Tests

#### 1. `whenPageLoads_thenDisplayCorrectTitle()`
- **Group**: `Sanity Test`
- **Description**: Validates that the Elements page loads correctly with the expected title
- **Maven Command**:
  ```bash
  mvn test -Dtest=ElementTests#whenPageLoads_thenDisplayCorrectTitle
  ```
- **Test Flow**:
  ```mermaid
  graph LR
    A[Open Elements Page] --> B[Get Page Title]
    B --> C[Verify Title = DEMOQA]
  ```
- **Assertions**:
  - Page title should be "DEMOQA"

#### 2. `whenSubmitTextBoxFormWithValidData_thenDisplayCorrectOutput(UserModal userData)`
- **Depends On**: `Sanity Test` group
- **Data Provider**: `User Data`
- **Description**: Verifies form submission with dynamic user data
- **Maven Command**:
  ```bash
  # Run with default data provider
  mvn test -Dtest=ElementTests#whenSubmitTextBoxFormWithValidData_thenDisplayCorrectOutput
  
  # Run with specific test data index (e.g., first data set)
  mvn test -Dtest=ElementTests#whenSubmitTextBoxFormWithValidData_thenDisplayCorrectOutput\[0\]
  ```
- **Test Flow**:
  ```mermaid
  graph TD
    A[Open Text Box Page] --> B[Enter User Data]
    B --> C[Submit Form]
    C --> D[Verify Output]
  ```
- **Test Data**:
  - First Name
  - Email
  - Current Address
  - Permanent Address
- **Assertions**:
  - All submitted data matches output

#### 3. `whenSelectRadioButton_thenDisplaySelectedValue()`
- **Depends On**: `Sanity Test` group
- **Description**: Tests radio button selection and verification
- **Maven Command**:
  ```bash
  mvn test -Dtest=ElementTests#whenSelectRadioButton_thenDisplaySelectedValue
  ```
- **Test Flow**:
  ```mermaid
  graph LR
    A[Open Radio Button Page] --> B[Select 'Impressive']
    B --> C[Verify Selection]
  ```
- **Assertions**:
  - Confirmation message matches selected value

### ⏸️ Disabled Tests

#### 1. `whenSelectCheckBoxOption_thenDisplayConfirmation()`
- **Status**: ❌ Disabled
- **Description**: Validates check box selection in a tree structure
- **To Enable**: Uncomment `@Test` annotation
- **Test Flow**:
  ```mermaid
  graph TD
    A[Open Check Box Page] --> B[Expand Tree]
    B --> C[Select Angular]
    C --> D[Verify Selection]
  ```
- **Assertions**:
  - Confirmation message contains "angular"

## ⚙️ Test Management

### 🔄 Test Lifecycle

```mermaid
stateDiagram-v2
    [*] --> Draft
    Draft --> Active: Enable Test
    Active --> Disabled: Disable Test
    Disabled --> Active: Enable Test
    Active --> [*]: Test Passes
    Active --> [*]: Test Fails
```

### 🔧 Enabling/Disabling Tests

#### Disabling a Test

1. **Using `enabled` flag**:
   ```java
   @Test(enabled = false)
   public void myTest() { ... }
   ```

2. **Commenting annotation**:
   ```java
   // @Test
   public void myTest() { ... }
   ```

3. **Using groups**:
   ```java
   @Test(groups = "wip") // Exclude this group in testng.xml
   public void workInProgressTest() { ... }
   ```

#### Enabling a Test

1. **Basic test**:
   ```java
   @Test
   public void myTest() { ... }
   ```

2. **With dependencies**:
   ```java
   @Test(
       groups = "regression",
       dependsOnGroups = "sanity",
       description = "Verifies form submission with valid data"
   )
   public void whenSubmitForm_thenSuccess() { ... }
   ```

### 🏷️ Test Organization

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Test` | Marks a method as test case | `@Test public void testLogin()` |
| `@BeforeMethod` | Runs before each test | Setup, initialize resources |
| `@AfterMethod` | Runs after each test | Cleanup, take screenshot |
| `@DataProvider` | Supplies test data | User credentials, form data |
| `@Parameters` | Passes parameters from XML | Environment URLs, test data |

## ⚙️ Configuration

### 🖥️ Browser Configuration

```properties
# application.properties
my.properties.browser=chrome    # Options: chrome, firefox
my.properties.grid=false        # Enable Selenium Grid
my.properties.explicitTimeout=60 # Timeout in seconds
my.properties.demo-url=https://demoqa.com/

# WebDriverManager Configuration
wdm.edgeDriverUrl=https://msedgedriver.azureedge.net/
wdm.architecture=X64
wdm.proxy=direct
wdm.proxyBypass=*.local,localhost
```

### 🧩 Test Suites

#### Main Suite (`Suites.xml`)
```xml
<suite name="Main Test Suite" verbose="1">
    <suite-files>
        <suite-file path="Element-Tests.xml" />
        <!-- Additional suite files can be added here -->
    </suite-files>
</suite>
```

#### Element Tests (`Element-Tests.xml`)
```xml
<suite name="Element Tests" parallel="methods" thread-count="5">
    <test name="UI Element Tests">
        <classes>
            <class name="com.auto.framework.ElementTests" />
        </classes>
    </test>
</suite>
```

### 🔄 Parallel Execution

```mermaid
pie
    title Parallel Test Execution
    "Test Methods" : 5
    "Threads" : 5
    "Suites" : 1
    "Classes" : 1
```

## 🚀 Running Tests

### 🖥️ Command Line

```bash
# Run all tests with Maven
mvn clean test

# Run specific test class
mvn test -Dtest=ElementTests

# Run specific test method
mvn test -Dtest=ElementTests#whenPageLoads_thenDisplayCorrectTitle

# Run with different browser
mvn test -Dmy.properties.browser=chrome

# Run with specific test group
mvn test -Dgroups="sanity"

# Run with Maven in parallel
mvn test -DthreadCount=5

# Run with detailed logs
mvn test -Dmaven.surefire.debug -Dmaven.failsafe.debug
```

### 💻 From IDE (IntelliJ/Eclipse)

#### Run Configurations

1. **Run All Tests**
   - Right-click on `src/test` > Run 'All Tests'
   - Or use shortcut: `Ctrl+Shift+F10` (Windows/Linux)

2. **Run Test Class**
   - Open `ElementTests.java`
   - Click green arrow next to class name
   - Or use shortcut: `Ctrl+Shift+F10`

3. **Run Single Test**
   - Click green arrow next to test method
   - Or right-click method > Run

4. **Run with Parameters**
   - Edit Run Configuration
   - Add VM Options: `-Dmy.properties.browser=chrome`
   - Add Environment Variables: `BROWSER=chrome`

#### Debugging Tests

1. Set breakpoints in test methods
2. Right-click > Debug Test
3. Use debug controls to step through code

### 🌐 Selenium Grid

```bash
# Start Selenium Grid Hub
java -jar selenium-server-4.x.x.jar hub

# Register Chrome node
java -Dwebdriver.chrome.driver=chromedriver.exe \
     -jar selenium-server-4.x.x.jar node \
     --hub http://localhost:4444/grid/register \
     --browser "browserName=chrome,platform=WINDOWS"

# Run tests against grid
mvn test -Dmy.properties.grid=true
```

### 📊 Test Reports

After test execution, view reports at:
- `target/surefire-reports/index.html`
- `target/surefire-reports/emailable-report.html`
- `target/surefire-reports/testng-results.xml` (for CI integration)

### 🧪 TestNG Report Example

```mermaid
pie
    title Test Results
    "Passed" : 8
    "Failed" : 1
    "Skipped" : 0
```

## 📊 Test Data Management

### 🧩 Data Providers

#### User Data Provider
```java
@DataProvider(name = "User Data", parallel = true)
public Object[][] userDataProvider() {
    return new Object[][] {
        { generateUserData() },
        { generateUserData() }
    };
}
```

### 📋 Test Data Structure

#### UserModal Class
```mermaid
classDiagram
    class UserModal {
        +String firstName
        +String email
        +String currAddress
        +String permAddress
        +String age
        +String salary
        +String department
        +UserModal builder()
        +UserModal build()
    }
```

### 📝 Adding New Test Data

1. **Extend Existing Provider**
   ```java
   @DataProvider(name = "Admin Users")
   public Object[][] adminUserProvider() {
       UserModal admin = UserModal.builder()
           .firstName("Admin")
           .email("admin@test.com")
           .build();
       return new Object[][]{{ admin }};
   }
   ```

2. **External Test Data**
   - JSON files
   - CSV files
   - Excel spreadsheets
   - Databases

### 🔄 Data Generation with Faker

```java
public UserModal generateUserData() {
    Faker faker = new Faker();
    return UserModal.builder()
        .firstName(faker.name().firstName())
        .email(faker.internet().emailAddress())
        .currAddress(faker.address().fullAddress())
        .permAddress(faker.address().fullAddress())
        .age(faker.number().digit())
        .salary(faker.number().digits(5))
        .department(faker.company().name())
        .build();
}
```

### 🗃️ Test Data Strategies

| Strategy | When to Use | Example |
|----------|-------------|---------|
| **Hardcoded** | Simple, stable data | `User user = new User("test", "pass")` |
| **Generated** | Random test data | Faker library |
| **External Files** | Large datasets | JSON, CSV, Excel |
| **Database** | Complex data relationships | SQL queries |
| **API** | Integration testing | REST clients |

### 🔗 Related Resources

- [TestNG Documentation](https://testng.org/doc/)
- [Selenium WebDriver](https://www.selenium.dev/documentation/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
