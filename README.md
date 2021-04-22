### Streams

- Once a stream is terminated it cannot be used again

### Junit

- Note in the pom.xml, that there is only one dependency for junit5
- To run junit 5, you need to dependencies
    - junit-jupiter-api defines the API for writing tests and extensions.
    - junit-jupiter-engine is the test engine implementation that runs the unit tests.
    - junit-jupiter-params provides support for parameterized tests.
- JUnit 5 packages its components in the org.junit.jupiter group and we need to add the `junit-jupiter` artifact
- Note here that `test` scope is used because that dependency is not required for normal use of the application

#### Mockito

- Some examples of mockito verifications

```
verify(mock).someMethod();
verify(mock, times(10)).someMethod();
verify(mock, atLeastOnce()).someMethod();
```

- Example of error when you do something like `verify(mock).someMethod()`, but that method isn't actually called

```
Wanted but not invoked:
myNaiveSingleton.doSomething();
-> at testing.MyClassUsingSingletonTest.t1(MyClassUsingSingletonTest.java:47)
Actually, there were zero interactions with this mock
```

**Spy vs Mock**

- Both can be used to mock methods or fields
- Mock is an object made by whatever library you're using (aka `Mockito.mock(MyClass.class)`)
    - Default behavior of a method when not stubed is to `do nothing`
- When you use spies, you are creating a real object (aka `new MyClass()`) but then `stub` (aka change behavior) of certain methods
    - If an unstubbed method is called, the real method will be invoked

<br>

**when vs doReturn for a spy**

- `when(...)thenReturn(...)` makes a real method call just before the specified value will be returned. So if the called method throws an Exception you have to deal with it / mock it etc. Of course you still get your result (what you define in thenReturn(...))
    - The same is true for `given`
- `doReturn(...)when(...)` does not call the method at all.
- This is only true in spies because for a `mock` - real methods are never called

### Classes and Interfaces

- Section notes coe from `Effective Java 3rd Edition`

<br>

- If a package-private top-level class or interface is used by one class, consider making the top level-class a private static nested class in the one class that uses it
- Static nested classes
    - Static classes belong to their enclosing class and not to an instance of the class
    - Can have all types of access modifiers in their declaration
    - They only have access to static members in the enclosing class
    - They can define both static and non-static members
- Non-Static nested classes
    - Also called `inner classes`
    - Can have all types of access modifiers in their declaration
    - Inner classes are associated with an instance of the enclosing class
    - Have access to all members of the enclosing class (static and nonstatic)
    - Can only define non-static members
- Local Classes
    - Special type of inner class where `the class is defined inside a method` or scope block
    - Cannot have access modifiers in their declaration
    - They have access to both static and non-static members in the enclosing context
    - They can only define instance members
- Anonymous Classes
    - Can be used to define an implementation of an interface or an abstract class w/o having to create a reusable implementation
    - Cannot have access modifiers in their declaration
    - Have access to both static and non-static members in the enclosing context
    - Can only define instance members
    - They're the only type of nested class that cannot define constructors or extend/implement other classes or interfaces
- Shadowing
    - Declaration of members ofan inner class shadow those of the enclosing class if they have the same name

### Generics

- Type parameters can be bounded (restricted)
    - We can restrict types that can be accepted by a method
    - We can specify a method that accepts a type and all its subclasses (upper bound) or a type and all its superclasses (lower bound)
- To declare an `upper bounded type` we use the keyword `extends` after the type followed by the upper bound that we want to use

```
public <T extends Number> List<T> fromArrayToList(T[] a) {

}
```

- `Extends` is used here to mean that the Type `T`:
    - Extends the upper bound in case of a class
    - Implements an upper bound in case of an interface

<br>

**Type Parameter Naming Conventions**

-  By convention, type parameter names are single, uppercase letters. This stands in sharp contrast to the variable naming conventions that you already know about, and with good reason: Without this convention, it would be difficult to tell the difference between a type variable and an ordinary class or interface name.
- The most commonly used type parameter names are:
    - E - Element (used extensively by the Java Collections Framework)
    - K - Key
    - N - Number
    - T - Type
    - V - Value
    - S,U,V etc. - 2nd, 3rd, 4th types
- Type parameter vs type argument
    - `T` in `Foo<T>` is a `type parameter`
    - `String` in `Foo<String>` is a `type argument`


## Certificates

- http://tutorials.jenkov.com/java-cryptography/keystore.html
- https://www.baeldung.com/java-keystore-truststore-difference

<br>

### KeyStore

- The JDK that you using stores a group of security certificates in `/lib/security/cacerts` (note that this is relative to your JDK's folder)
- The `KeyStore` is a database that can contain keys
    - We access this with the jdk `KeyStore` class
- The keystore can be password protected and each key entry can be password protected as
- It can hold the following types of keys
    - Private keys
    - Public keys + certificates
    - Secret keys

<br>

- Private and public keys are used in asymmetric encryption
- A certificate is a document that verifies the identity of the person, organization or claiming to own the public key
    - A certificate is typically digitally signed by the verifying party as proof
- Secret keys are used in symmetric encryption 
    - In most cases symmetric keys are negotiated when a secure connection is set up, so usually you will be storing public and private keys in a KeyStore than secret keys
- Usually we'll use a keystore when we are a server and want to use HTTPS
    - During the SSL handshake, the server looks up the private key from the keystore and presents its corresponding public key and certificate to the client
    - If the client also needs to authenticate itself - mtls - then the client also has a keystore and also presents its public key and certificate
- Since Java 9, the default keystore format is PKCS12 which is a standardized format that is language neutral (aka not Java specific)
    - JKS (standard before Java 9) is java specific 

### TrustStore

- A truststore holds onto certificates that identify others
- In Java we use it to trust the 3rd party we're about to communicate with
- If a server presents a public key and certificate to a client, the client look up the associated certificate in the `truststore`
- If the certificate or Certificate Authorities presented by the external server is not in our truststore, we'll get an *SSLHandShakeException* and the connection won't be established
- Java has a bundled truststore called *cacerts* and it resides in *$JAVA_HOME/jre/lib/security*
- You can check the default trusted Certificate Authorites using `keytool -list -keystore cacerts`
    - This will list the different certificate authorities that the JVM will automatically trust


### What is the SSL certificate chain

- https://support.dnsimple.com/articles/what-is-ssl-certificate-chain/

<br>

- Two types of certificate authorities
    - root CAs
    - intermediate CAs
- **For an SSL certificate to be trusted, that certificate must be issued by a CA that's included in the trusted store of the device that's connecting**
    - If the certificate wasn't issued by a trusted CA, the connecting device (like a web browser) checks t see if the certificate of the issuing CA was issued by a trusted CA
    - It continues until either a trusted CA is found (at which point a trusted, secure connectoin will be established) or no trusted CA can be found (at which point the device will usually display an error)
- This list of SSL certificates, from the root certificate to the end-user certificate, represents the SSL certificate chain

#### Example of an SSL certificate chain

- Suppose you purchase a certificate from the *Awesome Authority* for the domain `example.awesome`
- `Awesome Authority` isn't a root certificate authority
    - It's certificate isn't directly embedded in your web browser, so it can't be explicitly trusted
- The following is an example of the certificate chain that your browser follows until it finds a root CA (or fails)
    - *Awesome Authority* utilizes a certificate issued by *Intermediate Awesome CA Alpha*
    - *Intermediate Awesome CA Alpha* utilizes a certificate issued by *Intermediate Awesome CA Beta*
    - *Intermediate Awesome CA Beta* utilizes a certificated issued by *Intermediate Awesome CA Gamma*
    - *Intermediate Awesome CA Gamma* utilizes a certificate issued by *The King of Awesomeness*
    - *The King of Awesomeness* is a Root CA. Its certificate is directly embedded in your web browser, therefore it can be explicitly trusted
- In this example, the SSL certificate chain is represented by 6 certificates
    1. End-user Certificate - Issued to: example.com; Issued By: Awesome Authority
    1. Intermediate Certificate 1 - Issued to: Awesome Authority; Issued By: Intermediate Awesome CA Alpha
    1. Intermediate Certificate 2 - Issued to: Intermediate Awesome CA Alpha; Issued By: Intermediate Awesome CA Beta
    1. Intermediate Certificate 3 - Issued to: Intermediate Awesome CA Beta; Issued By: Intermediate Awesome CA Gamma
    1. Intermediate Certificate 4 - Issued to: Intermediate Awesome CA Gamma; Issued By: The King of Awesomeness
    1. Root certificate - Issued by and to: The King of Awesomeness

<br>

- Certificate 1, the one you purchased from *Awesome Authority* is your `end-user certificate`
- Certificates 2-5 are `intermediate certificates`
- Certificate 6, the one at the top of the chain (or at the end, depending on how you read it) is the `root certificate`
- When you install your end-user certificate for `example.awesome`, you **must** bundle all the intermediate certificates and install them along with your end-user certificate
- If the SSL certificate chain is invalid or broken, your certificate won't be trusted by some devices