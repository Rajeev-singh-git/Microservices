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

![1](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/30e8d110-ba31-4a51-9d09-c430d9a087d7)

Groups

- Groups in OAuth are collections of users with similar characteristics or permissions. Some OAuth implementations, especially in enterprise scenarios, allow the assignment of permissions to groups, making it easier to manage access control at scale.

![Screenshot 2024-01-21 150944](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/819af5f3-d6bc-42c4-8c06-9ee11f886cac)

Applications (Client)

- An application, also known as a client in OAuth, is a software entity that requests access to the resources on behalf of the resource owner. This could be a web application, a mobile app, a server-side application, or any other type of software that needs to interact with protected resources.
- Software programs interacting with APIs on behalf of users. Imagine a to-do list app accessing your Google Calendar.

![Screenshot 2024-01-21 151145](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/5bb3df3e-cba9-46ca-b8c5-d2c1eccc043b)


Security API

- API (Application Programming Interface) or Resource Server in OAuth is the server hosting the resources that the client wants to access. The API is responsible for validating and responding to requests for those resources, ensuring that access is granted only to authorized clients.
- Security in OAuth is paramount and is maintained through the use of various security mechanisms, such as token-based authentication and authorization. OAuth relies on secure communication, token validation, and other measures to protect sensitive data and ensure that only authorized entities gain access to resources.

![Screenshot 2024-01-21 151418](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/76ca8836-5457-4531-a8dc-2bc04efdafd0)


Scopes

- Scopes in OAuth define the specific permissions or access rights that a client application is requesting from the resource owner. Scopes help to narrow down the requested access and allow the resource owner to make informed decisions about granting or denying access. Examples of scopes include "read", "write", "profile", etc.


![Screenshot 2024-01-21 151604](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/3f32f891-27e9-4a96-a05b-4bd7379e647e)

Claims

- Claims are essentially pieces of information about a user or an authorization that are securely conveyed within OAuth tokens (typically JSON Web Tokens or JWTs).
- They act as a way to communicate verified details between different parties involved in an OAuth flow.

![Screenshot 2024-01-21 151711](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/042f06e6-61ab-4cce-b583-4d4ea13ae595)


In an OAuth flow:

- The resource owner grants permission to the client by authenticating and authorizing the client's access request.
- The client receives an access token, which is a credential representing the authorization granted.
- The client presents the access token to the resource server (API) when making requests for protected resources.
- The resource server validates the token and grants access if the token is valid and authorized.

## Step 2 : → Create a new App

Create a new app integration in Okta, choosing OIDC (OpenID Connect) and Web Application. Note the Client ID and Client Secret.

Application → Create App Integration → OIDC → Web Application

![Screenshot 2024-01-21 153254](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/f41e1724-ef95-42a6-9bac-356fb5611cfa)

![Screenshot 2024-01-21 185710](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/ce696796-ae4c-42cc-96df-317887fe95f4)

![Screenshot 2024-01-21 153531](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/66e92920-7fc3-41fd-883e-e629d1ee7923)

![Screenshot 2024-01-21 153547](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/ad0a528c-fc11-4749-9399-c6ce1639bea3)

Client ID and Client Secret are important and are unique for all user.

![Screenshot 2024-01-21 153646](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/3ac348d0-e68a-4e70-9421-d521fc6e14e8)


### Step 2.1 → Add People and Groups

Create groups in the directory and assign users to these groups.

To add Groups and People, we need to create Groups and People first.

![Screenshot 2024-01-21 153930](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/c33aaab4-568e-406c-80b9-662efeba05fa)


### Step 2.2 → First Make Groups, In directory


![Screenshot 2024-01-21 154227](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/9bb4d106-f8fe-4949-830c-52320b57316f)

Created 2 groups, one for Normal and one for Admin user.

![Screenshot 2024-01-21 154324](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/58543841-506c-4307-abde-5a78125159c2)


Assign Admin and Normal to the Application

![Screenshot 2024-01-21 154524](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/32ac9bbb-4f43-4b5c-9f0a-3cc9fc2ee04a)


### Step 2.3 : → In directory , create people

password = qwerty321#

![Screenshot 2024-01-21 163840](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/6221f939-d9af-49bd-92d5-9df4cfbbf131)


### Step 2.4:→ Already for this user, mywebapp is assigned


![Screenshot 2024-01-21 164011](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/11bd2f43-2d21-404a-a346-7755a1f7aebe)

In group we can see we have 1 group and 1 user assigned for Admin and Normal

![Screenshot 2024-01-21 164145](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/bfc9cf6f-72bd-4bc6-be6c-04af66bb535f)


### Step 2.5 :→ Create a scope for internal Communication

In Okta, create a scope for internal communication within your microservices.

Security → API → Default → Edit → Add scopes


![Screenshot 2024-01-21 171759](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/8108ee1d-a53a-419a-9e52-5de4ca8f6031)


### Step 2.6 :→ Add Claim

Add claims to convey verified details within OAuth tokens securely.

![Screenshot 2024-01-21 172229](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/85d6c23a-ce57-42b8-8868-c4bf45d1c491)

![Screenshot 2024-01-21 172154](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/9d503705-2c4b-4f80-afda-8be48399a4be)


# Implementing OKTA at API Gateway

Background → Through API Gateway clint sent request


![Screenshot 2024-01-21 171520](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/1b081c5e-1071-413a-9049-5cc3d911aca0)

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

![2](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/96792407-4a11-4f8f-aad5-4a39198449eb)

Client Id  and Client Secret we will get from Applications → MyWebApp (app which we created)

Client id = 0oaeltirtamcF33AH5d7

Client Secret : pTkn1cKltDTfi2GbRpYMOJ68ANr7Y-fr4vEj8cIIcIInoTZM-VL_zvqAMxVZA_bv

![3](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/6db0b548-d941-43fb-984c-b6875446d033)


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


![4](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/aa635d5c-e3a6-43f8-8995-bf011a756120)

After login : →

![5](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/ba55a756-9b58-485c-8a63-55f35177add1)


We can access API Gateway through postman after adding access token in header

Authorization → OAuth2

![6](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/80d5b915-af8c-4796-8ae0-00d20cdcee68)


http://localhost:8084/hotels


![7](https://github.com/Rajeev-singh-git/Microservices/assets/87664048/8251526a-681e-4c34-917e-ef0990428aad)

# Further Reading Recommended : →

OKATA official documentation : https://developer.okta.com/

Spring Security Official Documentation : https://docs.spring.io/spring-security/reference/index.html
