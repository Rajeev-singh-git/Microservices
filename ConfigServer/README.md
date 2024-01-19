# What is config server?

**In microservices architecture, a config server is a centralized application that provides externalized configuration to various microservices in the system. It plays a crucial role in managing configuration effectively and efficiently.**

**Here's why a config server is needed:**

- **Centralized Configuration Management:**
    - Stores all configuration properties in a single, centralized location (typically a Git repository or file system).
    - Eliminates the need to hardcode configuration values within individual microservices.
    - Simplifies maintenance and updates, as changes can be made in one place and propagated to all microservices seamlessly.
- **Environment-Specific Configurations:**
    - Manages configurations for different environments (dev, test, production) effectively.
    - Ensures microservices receive appropriate settings for their respective environments.
- **Dynamic Configuration Updates:**
    - Allows changes to configuration properties without requiring microservice restarts (in most cases).
    - Enables faster rollout of configuration changes and quicker response to issues.
- **Feature Flag Management:**
    - Can be used to manage feature flags, enabling or disabling features dynamically.
    - Facilitates experimentation and gradual rollout of new features.
- **Auditing and Versioning:**
    - Provides tracking of configuration changes over time, aiding in auditing and troubleshooting.
    - Offers a clear history of configuration modifications and the ability to revert to previous versions.
- **Security:**
    - Supports encryption of sensitive configuration values for enhanced security.
    - Protects confidential information from unauthorized access.

**Key benefits of using a config server:**

- **Improved Manageability:** Centralized management simplifies configuration maintenance and updates.
- **Consistency:** Ensures consistency of configuration across microservices.
- **Reduced Deployment Time:** Eliminates the need to redeploy microservices for configuration changes.
- **Enhanced Security:** Secure storage and management of sensitive information.
- **Auditability:** Tracking of configuration changes for auditing and compliance.

In a microservices architecture, a config server is essential for maintaining consistent, manageable, and secure configurations across a distributed system.

# Steps to Implement Config server

## Step 1 : Create Spring Boot Application

Add dependency

1. `Config Server`  = Central Management for configuration.
- **Purpose:** Provides the core functionality for building a centralized configuration server.
- **Key Features:**
    - Fetches configuration properties from a remote source (like a Git repository).
    - Serves configuration data to client microservices via a REST API.
    - Supports multiple environments and profiles for managing different configuration sets.
    - Enables dynamic configuration updates in most cases.

1. `Eureka Discovery Client` = Because Config Application is also microservice, which will be registered with Service resgistry.
- **Purpose:** Enables the config server to register itself with a service registry (like Eureka) and be discoverable by other microservices.
- **Key Benefits:**
    - Client microservices can easily locate the config server using the service registry, even if its physical location or IP address changes.
    - Promotes flexibility and scalability in the microservices architecture.
    - Facilitates load balancing and failover strategies for the config server.

## Step 2 :→ Create github repo for config file.

Create `application.yml` file in that.



![Screenshot 2024-01-16 012358](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/87e967a2-a27a-47e6-a457-a0887ca8947c)


## Step 3 → Add annotation `@EnableConfigServer`  in main class of Spring boots

```sql
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
```

**Purpose:**

- **Activates Config Server Functionality:** This annotation acts as a "switch" that turns a Spring Boot application into a fully functional Spring Cloud Config Server.
- **Enables Configuration Serving:** It enables the application to fetch configuration files from a remote source (like a Git repository) and serve them to client applications via a REST API.

**Key Features:**

- **Configuration Fetching:** Automatically fetches configuration files from the configured remote repository.
- **REST Endpoints:** Exposes REST endpoints for client applications to retrieve configuration data.
- **Environment-Specific Configurations:** Supports managing configuration files for different environments (e.g., "dev," "test," "prod").
- **Profile-Based Configurations:** Manages configurations for different application profiles.
- **Refreshable Configurations:** Allows dynamic updates to configuration properties without requiring client application restarts (in most cases).
- **Security:** Integrates with Spring Security for authentication and authorization of configuration access.

**Usage:**

1. **Add Annotation:** Apply the `@EnableConfigServer` annotation to the main class or a configuration class of your Spring Boot application.
2. **Provide Configuration:** Set up additional properties in `application.properties` or `application.yml` to specify:
    - The location of the remote repository (e.g., Git repository URL).
    - Other Config Server-specific settings.


## Step 4 :→ Add github repo url in application.yml

```sql
cloud:
   config:
    server:
     git:
       uri : https://github.com/Rajeev-singh-git/Microservice-config
       clone-on-start: true
```

```sql
server:
  port: 8085
spring:
  application:
    name: CONFIG-SERVER

  cloud:
   config:
    server:
     git:
       uri : https://github.com/Rajeev-singh-git/Microservice-config
       clone-on-start: true
```

**Purpose:**

- This configuration snippet establishes the connection between a Spring Cloud Config Server and a Git repository, enabling the server to fetch configuration files from the specified repository.

**Key Properties:**

- **`cloud.config.server.git.uri`:**
    - Specifies the URL of the Git repository containing configuration files.
    - In this case, it's set to `https://github.com/Rajeev-singh-git/Microservice-config`, indicating that the Config Server will fetch configurations from this repository.
- **`cloud.config.server.git.clone-on-start`:**
    - Instructs the Config Server to clone the Git repository locally when the application starts.
    - Setting this to `true` ensures faster access to configuration files, as they'll be available locally instead of being fetched remotely each time.

**Additional Considerations:**

- **Authentication:** If the Git repository is private, you'll need to provide necessary credentials (username and password or SSH key) using additional properties.
- **Branch:** To fetch configurations from a specific branch, use the `cloud.config.server.git.default-label` property.
- **Path:** To specify a subdirectory within the repository for configuration files, use the `cloud.config.server.git.search-paths` property.
- **Security:** Consider security measures to protect sensitive configuration properties, such as encryption or access control.

**Remember:** This configuration works in conjunction with the `@EnableConfigServer` annotation to fully activate the Config Server functionality.

## Step 5 :→ Add repeating configuration in github application.yml

Eureka Config is repeating in multiple application.xml file so adding there.


![Screenshot 2024-01-16 015523](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/c0e758eb-4e1d-46f2-bd91-eb5030750c15)


Before adding eurka = config url showing default profile

[rajeev:8085/actuator/default](http://rajeev:8085/actuator/default)

![Screenshot 2024-01-16 015354](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/a1f8d1e7-3561-43b9-b552-9dc119ac9836)


Post adding eurka = config url showing default profile


![Screenshot 2024-01-16 020325](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/9d5681db-d239-44e5-a2cb-59531770595f)

Just for understanding, adding same configuration in dev profile of git

[rajeev:8085/actuator/de](http://rajeev:8085/actuator/default)v

Now, checking dev url of config

![Screenshot 2024-01-16 020407](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/544333a0-c66e-4d08-b35b-39b57d35b92f)


# Reading Config from GitHub

## How can we  utilize configurations stored on GitHub for a given service?

### Step 1 : → Add  “**Config Client” dependency to the service**

- **Config Client  =** Client that connects to a Spring Cloud Config Server to fetch the application's configuration.

In UserService POM.XML

```xml
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

### Step 2 : →In `application.yml`, of UserService add property

`spring.config.import`

Add configuration server URL

http://localhost:8085

```xml
spring:
   config:
      import: configserver:http://localhost:8085
```

*** This property is essential when using Spring Cloud Config to fetch configuration from a remote server. It tells the application where to find the configuration server.

### Step 3: → In application.yml of UserService, comment Discovery config. we will load it from github.

```xml
#eureka:
#  instance:
#    prefer-ip-address: true
#  client:
#    fetch-registry: true
#    register-with-eureka: true
#    service-url:
#      defaultZone: http://localhost:8761/eureka
```

There are multiple config file on github. To load a specific configuration from GitHub, you can specify the desired config file. The following code ensures the loading of the production configuration.

```xml
profiles:
    active: prod
```

If no profile name is specified, the system will default to loading the configuration from 'application.yml.

![Screenshot 2024-01-19 062517](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/b614e8cc-3edb-4c3f-a4e1-eae2474fd9a2)


Given that the Eureka configuration is consistent across all service classes, replicate Steps 1, 2, and 3. This approach eliminates the need for redundant configuration entries, allowing direct retrieval from GitHub.

[Official Documentation](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)
