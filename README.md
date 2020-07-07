- CREDENTIALS - 
- DETAILS     - org.springframework.security.web.authentication.WebAuthenticationDetails@fffd3270: 
                RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: AFCC40C7ACF874AB17EFC96BC92D62A2

- NAME        - 105941810575572946401
- Principal   - Name: [105941810575572946401],
                Granted Authorities: [[ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, 
                                        SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], 
                User Attributes: [{at_hash=tSqR5It8ZKJimKfv2nc6og, 
                                       sub=105941810575572946401,
                            email_verified=true, 
                                       iss=https://accounts.google.com, 
                                given_name=sunn, 
                                    locale=de, 
                                     nonce=fynp0Qo_DCEhW3NG0Py3-5w5auuY85f8d3bw4ITJ88o,
                                   picture=https://lh3.googleusercontent.com/-flfMtnqHFM8/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucl0Z6o5Nfc4w4pHfC5SWR3Ngqd6SA/s96-c/photo.jpg, 
                                       aud=[489630767816-8iii3jveubnmqmevetail6u72suic0io.apps.googleusercontent.com], 
                                       azp=489630767816-8iii3jveubnmqmevetail6u72suic0io.apps.googleusercontent.com, 
                                      name=sunn kei, 
                                       exp=2020-07-02T00:58:10Z, 
                               family_name=kei, 
                                       iat=2020-07-01T23:58:10Z, 
                                     email=sunnyemail6@gmail.com}]

- Authorities - [ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, 
                      SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]
- CLASS       - class org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken

======================================================================

• To retrive all the Roles and Permissions of a specific user 
• GET:   https://localhost:4711/users/getRolesById/1

  Resonse
  --------

[
    {
        "gts_role_id": 1,
        "gts_role_name": "ROLE_USER",
        "permissions": [
            {
                "gts_permission_id": 1,
                "gts_permission_name": "CAN_CREATE_ACCOUNT",
                "gts_permission_status": false,
                "gts_permission_description": null
            },
            {
                "gts_permission_id": 2,
                "gts_permission_name": "CAN_UPDATE_ACCCOUNT",
                "gts_permission_status": false,
                "gts_permission_description": null
            }
        ]
    }
]






