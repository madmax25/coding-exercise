# Getting Started

## Decisions / Architecture

* Caching is implemented using Caffeine Cache
* Spring security is implemented using Basic Security
* Validation ensures only alpha-numeric values (and/or dashes) are provided as part of the username input
  * Validation using Spring Validation / @Pattern annotation
* ControllerAdvice in ControllerConfig to provide a consistent output using error message and error code
* Packages are split into various topics such as config, constants, controller, converter, exception, etc.
* Goal of 100% unit test coverage
* Configuration properties extracted to application.properties
* API is versioned following basic semantic versioning i.e. using /v1 explicitly for the first iteration
* Configuration split by topic for code de-coupling
* Utilized interface/implementation approach for services needed, UserService and GitHubService
* 1 HTTP API call and Response object per method in GitHubServiceRestImpl. 
  * This keeps the immediate results simple and decoupled
  * The results are later combined in UserServiceImpl through UserConverter#of
* Converter Pattern for mapping POJOs

## Error Codes
| Error Code | Http Code | Reason                              |
|------------|-----------|-------------------------------------|
| 100        | 500       | Error getting user details          |
| 200        | 500       | Error getting repo details for user |
| 300        | 500       | User not found                      |
| 301        | 400       | Input validation error              |

## Configure Service

It will be important for you to choose an appropriate api.user and api.secret for src/main/resources/application.properties

You will need to get base64 encoded <api.user>:<api.secret> and specify this as a header, 
Authorization: Basic (base64 encoded value).

The secret is not hardcoded in the codebase for security purposes.

## Base64 Encode on Mac Terminal

> echo -n 'example-api-user:example api-password' | openssl base64

## Example API curl

> curl --location 'http://localhost:8080/api/v1/users/octocat' \
--header 'Authorization: Basic (base64 encoded)'

# Run App
* Create an Application Run Configuration
  * Name it like MyApp
  * Ensure Java 21 is used
  * Package is com.branch.poc.CodingExerciseApplication
* Run the app with Run or Debug option

# Troubleshooting
* Contact Max Edmiston (<mailto:max@edmiston.org>)
