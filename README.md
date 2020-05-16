GET Requests
Get Tender By Id
Obtains a specific tenders by its unique identifier.

Sample Request
GET /tender/1 HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 47

{"id":1,"workDescription":"Work description 1"}
CURL sample
$ curl 'http://localhost:8080/tender/1' -i -X GET
Path Parameters
Table 1. /tender/{id}
Parameter	Description
id

Tender Unique Identifier

Response Fields
{"id":1,"workDescription":"Work description 1"}
Get Tenders For Issuer
Obtains a tenders for issuer by its unique identifier.

Sample Request
GET /tender/issuer/1 HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Length: 97
Content-Type: application/json;charset=UTF-8

[{"id":1,"workDescription":"Work description 1"},{"id":2,"workDescription":"Work description 2"}]
CURL sample
$ curl 'http://localhost:8080/tender/issuer/1' -i -X GET
Path Parameters
Table 2. /tender/issuer/{id}
Parameter	Description
id

Issuer Unique Identifier

Response Fields
[{"id":1,"workDescription":"Work description 1"},{"id":2,"workDescription":"Work description 2"}]
Get Offer By Id
Obtains a specific offers by its unique identifier.

Sample Request
GET /tender/offer/1 HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 47

{"id":1,"status":"NON_SUBMITTED","price":20000}
CURL sample
$ curl 'http://localhost:8080/tender/offer/1' -i -X GET
Path Parameters
Table 3. /tender/offer/{id}
Parameter	Description
id

Offer Unique Identifier

Response Fields
{"id":1,"status":"NON_SUBMITTED","price":20000}
Get Tender Offers By Id
Obtains a specific tender offers by its unique identifier.

Sample Request
GET /tender/1/offers HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 145

[{"id":1,"status":"NON_SUBMITTED","price":20000},{"id":2,"status":"NON_SUBMITTED","price":30000},{"id":3,"status":"NON_SUBMITTED","price":20000}]
CURL sample
$ curl 'http://localhost:8080/tender/1/offers' -i -X GET
Path Parameters
Table 4. /tender/{id}/offers
Parameter	Description
id

Offer Unique Identifier

Response Fields
[{"id":1,"status":"NON_SUBMITTED","price":20000},{"id":2,"status":"NON_SUBMITTED","price":30000},{"id":3,"status":"NON_SUBMITTED","price":20000}]
Get Tender Offers Submitted By Bidder
Obtains a tender offers submitted by bidder.

Sample Request
GET /tender/bidder/1/offers HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 89

[{"id":1,"status":"SUBMITTED","price":20000},{"id":5,"status":"SUBMITTED","price":60000}]
CURL sample
$ curl 'http://localhost:8080/tender/bidder/1/offers' -i -X GET
Path Parameters
Table 5. /tender/bidder/{id}/offers
Parameter	Description
id

Bidder Unique Identifier

Response Fields
[{"id":1,"status":"SUBMITTED","price":20000},{"id":5,"status":"SUBMITTED","price":60000}]
Get Specific Tender Offers Submitted By Bidder
Obtains a specific tender offers submitted by bidder.

Sample Request
GET /tender/bidder/1/offers?tenderId=2 HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
Content-Length: 45

[{"id":5,"status":"SUBMITTED","price":60000}]
CURL sample
$ curl 'http://localhost:8080/tender/bidder/1/offers?tenderId=2' -i -X GET
Path Parameters
Table 6. /tender/bidder/{id}/offers
Parameter	Description
id

Bidder Unique Identifier

Request Parameters
Parameter	Description
tenderId

Tender Unique Identifier

Response Fields
[{"id":5,"status":"SUBMITTED","price":60000}]
POST requests
Create Tender
Create tender.

Sample Request
POST /tender HTTP/1.1
Content-Length: 49
Content-Type: application/json;charset=UTF-8
Host: localhost:8080

{"issuerId":1,"workDescription":"Work desc test"}
Sample Response
HTTP/1.1 201 Created
CURL sample
$ curl 'http://localhost:8080/tender' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{"issuerId":1,"workDescription":"Work desc test"}'
Request Fields
Path	Type	Description
workDescription

String

Work Description of Tender

issuerId

Number

Issuer Unique Identifier

Request Body
{"issuerId":1,"workDescription":"Work desc test"}
Response Body
Create Offer
Create offer.

Sample Request
POST /tender/offer HTTP/1.1
Content-Length: 41
Content-Type: application/json;charset=UTF-8
Host: localhost:8080

{"price":50000,"bidderId":1,"tenderId":1}
Sample Response
HTTP/1.1 201 Created
CURL sample
$ curl 'http://localhost:8080/tender/offer' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{"price":50000,"bidderId":1,"tenderId":1}'
Request Fields
Path	Type	Description
bidderId

Number

Bidder Unique Identifier

tenderId

Number

Tender Unique Identifier

price

Number

Offer Price

Request Body
{"price":50000,"bidderId":1,"tenderId":1}
Response Fields
Create Issuer
Create issuer.

Sample Request
POST /issuer HTTP/1.1
Content-Length: 33
Content-Type: application/json;charset=UTF-8
Host: localhost:8080

{"issuerName":"Issuer Name Test"}
Sample Response
HTTP/1.1 201 Created
CURL sample
$ curl 'http://localhost:8080/issuer' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{"issuerName":"Issuer Name Test"}'
Request Fields
Path	Type	Description
issuerName

String

Issuer Name

Request Body
{"issuerName":"Issuer Name Test"}
Response Body
Create Bidder
Create bidder.

Sample Request
POST /bidder HTTP/1.1
Content-Length: 33
Content-Type: application/json;charset=UTF-8
Host: localhost:8080

{"bidderName":"Bidder Name Test"}
Sample Response
HTTP/1.1 201 Created
CURL sample
$ curl 'http://localhost:8080/bidder' -i -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{"bidderName":"Bidder Name Test"}'
Request Fields
Path	Type	Description
bidderName

String

Bidder Name

Request Body
{"bidderName":"Bidder Name Test"}
Response Body
PUT requests
Submit Offer
Submit offer.

Sample Request
PUT /tender/1/offer/1 HTTP/1.1
Host: localhost:8080
Sample Response
HTTP/1.1 200 OK
CURL sample
$ curl 'http://localhost:8080/tender/1/offer/1' -i -X PUT
Path Parameters
Table 7. /tender/{id}/offer/{offerId}
Parameter	Description
id

Tender Unique Identifier

offerId

Offer Unique Identifier

Request Body
Response Body
Last updated 2020-05-16 18:31:24 +0200
