# Small cinema

Small cinema project represents a backend Spring Boot application for fetching movie show times, their details, allowing ratings etc.

# How to run this application

##### Requirements

  - Java 14
  - Maven 3

##### Steps

- Clone or download this repository
- To run unit tests, run `$ mvn clean test` in the main catalog. The results will be displayed on the console as well as saved in `/target/surefire-reports`
- To run unit and integration tests, run `$ mvn clean verify` in the main catalog. The results of the unit tests will be displayed on the console as well as saved in `/target/surefire-reports` and the results of the integration tests will be displayed on the console as well as saved in `/target/failsafe-reports`
- To run the application, run ` $ mvn spring-boot:run` in the main catalog. When running for the first time, it will take longer, because all dependencies must be downloaded by Maven to your local repository. The application will use port 8989 by default, but this can be configured in `application.yml` file
- Application will be still running even after closing the command console. In order to shutdown the application, please use `/shutdown` endpoint (available on Swagger ui)

# How to use this application

The application exposes 4 REST endpoints, all of them are easily available through swagger ui, which is available under http://localhost:8989/cinema/swagger-ui.html

The application uses [omdb API](https://breakdance.github.io/breakdance/) for fetching movie details and to make it working, you have to have **api key**, which can be obtained [here](http://www.omdbapi.com/apikey.aspx). Once you receive your api key, it has to be set as `imdb_api_key` system environment variable (after setting, command console needs to be reopened once again)

##### Security

Most of the REST operations are available for anyone (not logged user). However, some critical actions (like updating show time prices/times or shutdowning the application) requires admin login. Feel free to provide your credentials in the `application.yml` file (`spring.security.user.name` and `spring.security.user.password` properties) - this user will have admin privileges.

# Reasons behind choices

- Spring Boot for simplifying exposing REST endpoints, security and running entire application
- Maven for simplifying builds
- Splitting classes per package - 1 package for 1 functionality (wich almost all classes on the package-level access) simplifies possible future changes
- Extracting interfaces helps creating extensible code
- Creating abstractions level for the generic dao operations - reduces repeating the same code
- Proposed dao which operates on HashMap, for ability to create module tests without Spring context
- Versioning for using Optimistic Locking (in database), or using read-write locks (in HashMap)
- H2 as the internal DB for operating with real database, without configuring anything outside the application

# What next?

##### There are a lot of possible improvements:

- Separate database sequence for every entity id generation
- Creating DB schema through our scripts, using for example Flyway
- After such switching to Flyway, data generators will be moved to the populating scripts
- DB changed to some external DB, not internal
- Tests - Spock in Groovy instead of JUnit
- Tests - can be parameterized, more test cases, including edge cases
- Concurrency - tests for concurrency cases
- Logging - using for exampe SLF4J, logging in services and in controller advice, on appropriate levels
- Connecting with omdb movies api - setting appropriate timeouts
- Connecting with omdb movies api - handle case when api key is invalid
- Lombok can be added - I had some issues with my local Java 14 so I refused to use it (I wanted to use it especially for pure structure classes like movie details view)
- Models - I created Movie like an entity, so, unfortunately, it had to have default constructor. in the future, we can separate pure JPA entity and business entity (so without JPA annotations, but for example with logic like in rate() method)
- Refaktoring - magic number can be extracted as a named variables
- Introducing audit, that is, adding new common base class with fields like "createdBy", "cratedOn", "updatedBy" and "updatedOn".
- Optimistic locking for ratings - ok, we have optimistic locking, but I think when I, as an user, want to rate the movie, I don't care if in the meantime someone another rates too. Assuming the cinema will grow and grow, multiple people will fight to have current version and have possibility to rate a movie. Therefore, ratings should try again and try again until current version is fetched.
- Functionalities that would be also useful for the user: 
-- Possibility to reserve a ticket (and pay for it)
-- Get all movies endpoint
-- Filter movies by genre
-- Ability to register to have an account for example to have favourites movies, remember address or being notified once new movie will premiere.
- Tests - assuming that there will be a client for consuming our REST API, there should be also integration tests for controllers to test if the contract is met.
- Tests - more unit tests with new Movie HashMap dao (there is no such dao yet) - just as it is in UpdateShowTimesServiceImplUnitTest
- Security - currently basic authentication, but eventually can be switched to OAuth2 for example 

