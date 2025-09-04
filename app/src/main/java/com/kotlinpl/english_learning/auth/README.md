# Auth Package (Module)

This module handles the session handling logic for the user.

Some apps normally uses FIDO Passkeys to authenticate users,
to which Android Jetpacks provides an implementation using [CredentialManager][1]
This allows the usage of FingerPrints and Patterns on the user side to authenticate the user.

## Our Implementation

In this project lab we're going to use OAuth like Auth Implementation (Also widely used).
Which uses a pair of `Access Token` and `Refresh Token` which will provide information to the
backend about the actual user(or application) trying to get information from backend

***Figure: Refreshing an Expired Access Token***

```text
  +--------+                                           +---------------+
  |        |--(A)------- Authorization Grant --------->|               |
  |        |                                           |               |
  |        |<-(B)----------- Access Token -------------|               |
  |        |               & Refresh Token             |               |
  |        |                                           |               |
  |        |                            +----------+   |               |
  |        |--(C)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(D)- Protected Resource --| Resource |   | Authorization |
  | Client |                            |  Server  |   |     Server    |
  |        |--(E)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(F)- Invalid Token Error -|          |   |               |
  |        |                            +----------+   |               |
  |        |                                           |               |
  |        |--(G)----------- Refresh Token ----------->|               |
  |        |                                           |               |
  |        |<-(H)----------- Access Token -------------|               |
  +--------+           & Optional Refresh Token        +---------------+
```

This leads us to an implementation using Interceptors to the HTTP Clients
to set the Access Tokens and Refresh Tokens when needed.

You can find this implementation on `com.kotlinpl.english_learning.common` package

[1]: https://developer.android.com/identity/sign-in/credential-manager
