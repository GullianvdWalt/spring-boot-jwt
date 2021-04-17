# spring-boot-jwt
Demo Spring boot + Spring Boot Security + MySQL project login with jwt

# What I learned

JSON WEB TOKEN
JWT consists out of three parts: Header, Payload & Signature.
The Payload is uesed to describe the type & algorithm.
The Header is used for the storing the claims which is the identify of the client.
The Signature defines that the message wasn't changed.

Using Spring Security

JWTUtil service class:
Gets a username and creates a jwt token with a expiration date.
Validate a user token.

A Filter component class
Used for the authentication of the username and token.
Get the Bearer jwt response which includes the token.
Get the token from the response and then validate the user.

In the Security configaration class:

Override WebSecurityConfigurerAdapter and register the filter
