# spring-boot-jwt
Demo Spring boot + Spring Boot Security + MySQL project login with jwt

# What I learned

<b>JSON WEB TOKEN</b>

<ul>
  <li>JWT consists out of three parts: Header, Payload & Signature.</li>
  <li>The Payload is uesed to describe the type & algorithm.</li>
  <li>The Header is used for the storing the claims which is the identify of the client.</li>
  <li>The Signature defines that the message wasn't changed.</li>
</ul>

<b>Using Spring Security</b>

JWTUtil service class:
<ul>
  <li>Gets a username and creates a jwt token with a expiration date.</li>
  <li>Validate a user token.</li>
</ul>

<b>A Filter component class</b>
<ul>
  <li>Used for the authentication of the username and token.</li>
  <li>Get the Bearer jwt response which includes the token.</li>
  <li>Get the token from the response and then validate the user.</li>
</ul>

<b>In the Security configaration class:</b>
<ul>
  <li>Override WebSecurityConfigurerAdapter and register the filter</li>
</ul>

