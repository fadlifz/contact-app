# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username": "fadli",
  "password": "secret",
  "name": "Fadli Faturzaman"
}
```

Response Body(Success) :

```json
{
  "data": "OK"
}
```

Response Body(Failed) :

```json
{
  "errors": "Username cannot be blank"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username": "fadli",
  "password": "secret"
}
```

Response Body(Success) :

```json
{
  "data": {
    "token": "TOKEN",
    "expiredAt": 12121212 //milisecond
  }
}
```

Response Body(Failed, 401) :

```json
{
  "errors": "Username or password wrong"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body(Success) :

```json
{
  "data": {
    "username": "Fadli",
    "name": "Fadli Faturzaman"
  }
}
```

Response Body(Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body(Success) :

```json
{
  "name": "Fadli Faturzaman", //put if only want to update name
  "password": "new password" //put if only want to update password
}
```

Response Body(Success) :

```json
{
  "data": {
    "username": "Fadli",
    "name": "Fadli Faturzaman"
  }
}
```

Response Body(Failed, 401) :

```json
{
  "errors": "Unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body(Success) :

```json
{
  "data": "OK"
}
```
