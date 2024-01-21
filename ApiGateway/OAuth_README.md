# **Okta and Microservices Security**

Okta can play a vital role in securing your microservices architecture by providing centralized identity and access management (IAM) capabilities.

## ****Key Benefits of Using Okta for Microservices Security****

- **`Centralized Authentication:`** Okta can be used as a central authentication provider for all of your microservices. This means that users only need to log in once to access all of your applications. This can improve security by reducing the number of login prompts that users see and making it more difficult for attackers to steal credentials.
- **`Authorization:**` Okta can also be used to enforce authorization policies for your microservices. This means that you can control which users have access to each microservice and what actions they are allowed to perform. This can help to prevent unauthorized access to your data and applications.
- **`API Security:`** Okta can be used to secure your APIs by providing API access management (APIAM) capabilities. This means that you can control who has access to your APIs and what data they can access. This can help to prevent unauthorized access to your data and applications.
- **`Single Sign-On (SSO):**` Okta can also be used to provide SSO for your microservices. This means that users can log in once to access all of your applications without having to enter their credentials multiple times. This can improve the user experience and make it more difficult for attackers to steal credentials.

## A**dditional benefits of using Okta**

- **Improved scalability: Okta can be easily scaled to support a large number of users and applications. This is important for microservices architectures, which can often be very dynamic and grow rapidly.**
- **Reduced complexity: Okta can help to reduce the complexity of managing security in a microservices architecture. This is because Okta provides a centralized platform for managing authentication, authorization, and API security.**
- **Improved compliance: Okta can help you comply with security regulations such as PCI DSS and HIPAA.**

## ****Considerations for Using Okta****

- **Careful Planning:** Plan your Okta deployment by identifying which microservices need protection and how Okta integrates with your existing infrastructure.
- **Developer Training:** Train developers on secure usage of Okta, covering user authentication, authorization for microservices, and protection of API endpoints.

# Configuring OKTA

## Step 1 : Create account on Okta Developer

Visit [Okta Developer](https://developer.okta.com/) to create an account. In the directory, manage People (resource owners) and Groups.

https://developer.okta.com/

In directory → People (Resource owner) , we can add user

- The "people" in OAuth refers to the end-users or resource owners who have control over the resources they own or have access to. These resources can be anything from user data to services.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/344ab3be-5e69-421c-89a1-3b86de989e4f/Untitled.png)

Groups

- Groups in OAuth are collections of users with similar characteristics or permissions. Some OAuth implementations, especially in enterprise scenarios, allow the assignment of permissions to groups, making it easier to manage access control at scale.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/3d955c80-5122-4311-8535-4310b38edd15/Untitled.png)

Applications (Client)

- An application, also known as a client in OAuth, is a software entity that requests access to the resources on behalf of the resource owner. This could be a web application, a mobile app, a server-side application, or any other type of software that needs to interact with protected resources.
- Software programs interacting with APIs on behalf of users. Imagine a to-do list app accessing your Google Calendar.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/64d7c038-a9ed-4910-ba37-efa673e30ff5/Untitled.png)

Security API

- API (Application Programming Interface) or Resource Server in OAuth is the server hosting the resources that the client wants to access. The API is responsible for validating and responding to requests for those resources, ensuring that access is granted only to authorized clients.
- Security in OAuth is paramount and is maintained through the use of various security mechanisms, such as token-based authentication and authorization. OAuth relies on secure communication, token validation, and other measures to protect sensitive data and ensure that only authorized entities gain access to resources.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/ec208268-0a19-4f89-8c25-9d7009ce6537/Untitled.png)

Scopes

- Scopes in OAuth define the specific permissions or access rights that a client application is requesting from the resource owner. Scopes help to narrow down the requested access and allow the resource owner to make informed decisions about granting or denying access. Examples of scopes include "read", "write", "profile", etc.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/77183afc-311a-4b05-8ffb-37f16f4a55e0/Untitled.png)

Claims

- Claims are essentially pieces of information about a user or an authorization that are securely conveyed within OAuth tokens (typically JSON Web Tokens or JWTs).
- They act as a way to communicate verified details between different parties involved in an OAuth flow.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/9372f16e-2b69-4c0e-8b06-6f30192e3cf9/Untitled.png)

In an OAuth flow:

- The resource owner grants permission to the client by authenticating and authorizing the client's access request.
- The client receives an access token, which is a credential representing the authorization granted.
- The client presents the access token to the resource server (API) when making requests for protected resources.
- The resource server validates the token and grants access if the token is valid and authorized.

## Step 2 : → Create a new App

Create a new app integration in Okta, choosing OIDC (OpenID Connect) and Web Application. Note the Client ID and Client Secret.

Application → Create App Integration → OIDC → Web Application

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/80e1f0de-13a4-4ed8-a250-39311111fc35/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/c896490f-ae34-4714-9621-e5d325610be7/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/a6f9da65-ae51-4cfe-b70e-0b665b1b78a4/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/6a63f02f-bab4-4fb2-8856-569153ddc8a0/Untitled.png)

Client ID and Client Secret are important and are unique for all user.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/eb4858d7-8686-42fb-9fba-642c6e871468/Untitled.png)

### Step 2.1 → Add People and Groups

Create groups in the directory and assign users to these groups.

To add Groups and People, we need to create Groups and People first.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/ee0ba275-561b-4405-a223-745f23339117/Untitled.png)

### Step 2.2 → First Make Groups, In directory

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/2b103a72-28f1-42dc-a7dc-ac9bf1609f9c/Untitled.png)

Created 2 groups, one for Normal and one for Admin user.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/02a8bd0c-09ce-4d7b-b039-2a22a08d6bf1/Untitled.png)

Assign Admin and Normal to the Application

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/6a1b20a5-a730-4711-8cba-d4214764ca51/Untitled.png)

### Step 2.3 : → In directory , create people

password = qwerty321#

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/0405cbc8-5b7f-431b-85c5-b3b2f53cea0c/Untitled.png)

### Step 2.4:→ Already for this user, mywebapp is assigned

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/b99dfb9c-18c5-4d1c-acdb-e2061f0de97b/Untitled.png)

In group we can see we have 1 group and 1 user assigned for Admin and Normal

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/62894e5f-6b4b-4b8f-9a3e-66cf7b6b066b/Untitled.png)

### Step 2.5 :→ Create a scope for internal Communication

In Okta, create a scope for internal communication within your microservices.

Security → API → Default → Edit → Add scopes

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/d357cbd7-6eab-4e74-8347-55b96f8fdbe8/Untitled.png)

### Step 2.6 :→ Add Claim

Add claims to convey verified details within OAuth tokens securely.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/e316ef12-e959-45d2-aa19-ee0a8b86d527/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/7f975ab5-be57-4a6f-99df-260e74a2365a/Untitled.png)

# Implementing OKTA at API Gateway

Background → Through API Gateway clint sent request

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/306c3816-60d3-4c95-bb6b-31d9f1f0149d/Untitled.png)

## Step 1 :→ ****Add Dependencies in API Gateway POM.XML****

In API Gateway, POM.XML

add `spring-boot-starter-security` and `okta-spring-boot-starter`

```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
</dependency>
 <dependency>
      <groupId>com.okta.spring</groupId>
      <artifactId>okta-spring-boot-starter</artifactId>
      <version>3.0.6</version>
</dependency>
```

## Step 2 : ****Configure API Gateway****

Issuer URL we will get from OKTA → Security → Default → Edit

issuer : https://dev-60370986.okta.com/oauth2/default

Audience we will also get from the same place

Audience  api://default

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/2eb11935-181b-4371-ba7e-8f6e79f74626/Untitled.png)

Client Id  and Client Secret we will get from Applications → MyWebApp (app which we created)

Client id = 0oaeltirtamcF33AH5d7

Client Secret : pTkn1cKltDTfi2GbRpYMOJ68ANr7Y-fr4vEj8cIIcIInoTZM-VL_zvqAMxVZA_bv

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/47cb6af7-ec7e-4f1b-8d01-454847b24ae8/Untitled.png)

```yaml
#okta configuration for aoi gateway

okta:
  oauth2:
    issuer: https://dev-60370986.okta.com/oauth2/default
    audience: api://default
    client-id : 0oaeltirtamcF33AH5d7
    client-secret : pTkn1cKltDTfi2GbRpYMOJ68ANr7Y-fr4vEj8cIIcIInoTZM-VL_zvqAMxVZA_bv
    scopes: openid, profile, email, offline_access
```

## Step 3 : → ****Enable Spring Security in API Gateway****

### Step 3.1 :→ Create `SecurityConfig` class in `config` module

```java
package com.lcwd.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange(exchanges ->
                        exchanges
                                .anyExchange()
                                .authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt -> { /* Configure JWT settings here */
                                //    jwt.jwkSetUri("https://your-authorization-server/.well-known/jwks.json");
                                }) // Move JWT configuration inside the lambda
                )
                .build();
    }

}
```

### Step 3.2 → Create AuthController Class

```java
package com.lcwd.gateway.controllers;

import com.lcwd.gateway.models.AuthResponse;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
           @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
           @AuthenticationPrincipal OidcUser user,
           Model model
    ){
         logger.info("user email id : {} ", user.getEmail());

         //creating auth response object
         AuthResponse authResponse = new AuthResponse();

         //setting email to authresponse
         authResponse.setUserId(user.getEmail());

         //setting token to auth response
         authResponse.setAccessToken(client.getAccessToken().getTokenValue());

         authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());

         authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());

         List<String> authorities =  user.getAuthorities().stream().map(grantedAuthority -> {
             return grantedAuthority.getAuthority();
         }).collect(Collectors.toList());

         authResponse.setAuthories(authorities);

         return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
```

### Step 3.2 → Create AuthResponse

```java
package com.lcwd.gateway.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;

    private long expireAt;

    private Collection<String> authories;
}
```

## Result : → Hit API Gateway URL

Our URL : http://localhost:8084/

Output : →

Username : learncodewithrajeev@gmail.com

password : qwerty321#

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/241486de-792f-48c1-85f1-f5f3978862a0/Untitled.png)

After login : →

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/cec00be8-096e-4c64-9b5d-bcc7780efc89/Untitled.png)

We can access API Gateway through postman after adding access token in header

Authorization → OAuth2

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/dea61609-ab73-4b24-8872-2add85127492/Untitled.png)

http://localhost:8084/hotels

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/01bbf536-a533-419d-b567-d81390e807ad/e6a08198-ca0e-4c2a-93d4-dbb0d0f5ce36/Untitled.png)

# Further Reading Recommended : →

OKATA official documentation : https://developer.okta.com/

Spring Security Official Documentation : https://docs.spring.io/spring-security/reference/index.html