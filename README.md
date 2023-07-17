1. I secured my endpoints with JWT. To gain access to the endpoints, make a POST
call to localhost:8083/authenticate. I had created a dummy user for test purposes
so your post call shd have a JSON body as shown below:
 {
    "username": "admin",
    "password": "password"
                                         
 } 

2. The above call shd generate a token which you can use in subsequent API calls
by setting the Authorization header to: Bearer "token"

Note: i)the token is signed with HS512 algorithm. You can confirm by visiting: https://jwt.io/
and pasting the generated token therein. You will also see the Validity period of the token
ii) By default Spring security uses sessions, however, I had overridden this DEFAULT configuration
 and made it to use a STATELESS session creation policy bcos our system will be token based. 

3. Upon generating the token, make a call to: localhost:8083/airtime/fulfil
passing the token as an Authorization header as explained in point 2 above

4. I used springboot to build the project for fast bootstrapping and to take advantage of 
AutoConfigurations. For example, my project gets auto-deployed on Tomcat once I run
it. This is because merely adding springboot starter web to my pom file pulls in
transitive dependencies like apache tomcat server, jackson databind(for serialization
and deserialization) etc. Spring also provides D.I and component scanning etc.

5. For the unit test, I leveraged JUnit5 and Mockito for mocking Objects. I would
advice that MockWebServer be used(I didnt use it as it is, strictly speaking, not
a unit test) since I used WebClient to make API calls to your airtime VTU API


  