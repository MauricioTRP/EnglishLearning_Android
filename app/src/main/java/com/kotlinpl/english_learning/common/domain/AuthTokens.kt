package com.kotlinpl.english_learning.common.domain

/**
 * AuthTokens stores a pair of access token, and refresh token as part of the
 * OAuth2 authorization mechanism defined in [RFC6749](https://www.rfc-editor.org/rfc/inline-errata/rfc6749.html)
 *
 * @param accessToken - as the Access Token returned by Auth Server
 * @param refreshToken - as the Refresh Token returned by Auth Server
 *
 * ```text
 *   +--------+                                           +---------------+
 *   |        |--(A)------- Authorization Grant --------->|               |
 *   |        |                                           |               |
 *   |        |<-(B)----------- Access Token -------------|               |
 *   |        |               & Refresh Token             |               |
 *   |        |                                           |               |
 *   |        |                            +----------+   |               |
 *   |        |--(C)---- Access Token ---->|          |   |               |
 *   |        |                            |          |   |               |
 *   |        |<-(D)- Protected Resource --| Resource |   | Authorization |
 *   | Client |                            |  Server  |   |     Server    |
 *   |        |--(E)---- Access Token ---->|          |   |               |
 *   |        |                            |          |   |               |
 *   |        |<-(F)- Invalid Token Error -|          |   |               |
 *   |        |                            +----------+   |               |
 *   |        |                                           |               |
 *   |        |--(G)----------- Refresh Token ----------->|               |
 *   |        |                                           |               |
 *   |        |<-(H)----------- Access Token -------------|               |
 *   +--------+           & Optional Refresh Token        +---------------+
 * ```
 *                Figure 2: Refreshing an Expired Access Token
 */

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)
