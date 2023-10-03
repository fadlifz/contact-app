# Address API Spec

## Create Address

Endpoint : POST /api/contacts/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "street": "Jalan Raya",
  "city": "Atlantis",
  "province": "Acropolis",
  "country": "Fictional",
  "postalCode": "49872"
}
```

Response Body(Success) :

```json
{
  "data": {
    "street": "Jalan Raya",
    "city": "Atlantis",
    "province": "Acropolis",
    "country": "Fictional",
    "postalCode": "49872"
  }
}
```

Response Body(Failed) :

```json
{
  "errors": "Contact is not found"
}
```

## Update Address

Endpoint : PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "id": "randomstring",
  "street": "Jalan Raya",
  "city": "Atlantis",
  "province": "Acropolis",
  "country": "Fictional",
  "postalCode": "49872"
}
```

Response Body(Success) :

```json
{
  "data": {
    "id": "randomstring",
    "street": "Jalan Raya",
    "city": "Atlantis",
    "province": "Acropolis",
    "country": "Fictional",
    "postalCode": "49872"
  }
}
```

Response Body(Failed) :

```json
{
  "errors": "Address is not found"
}
```

## Get Address

Endpoint : GET /api/contacts/{idContact}/address/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body(Success) :

```json
{
  "data": {
    "id": "randomstring",
    "street": "Jalan Raya",
    "city": "Atlantis",
    "province": "Acropolis",
    "country": "Fictional",
    "postalCode": "49872"
  }
}
```

Response Body(Failed) :

```json
{
  "errors": "Address is not found"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body(Success) :

```json
{
  "data": "OK"
}
```

Response Body(Failed) :

```json
{
  "errors": "Address is not found"
}
```

## List Address

Endpoint : GET /api/contacts/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body(Success) :

```json
{
  "data": [
    {
      "id": "randomstring",
      "street": "Jalan Raya",
      "city": "Atlantis",
      "province": "Acropolis",
      "country": "Fictional",
      "postalCode": "49872"
    }
  ]
}
```

Response Body(Failed) :

```json
{
  "errors": "Contact  is not found"
}
```
