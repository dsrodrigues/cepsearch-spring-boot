# cepsearch-spring-boot

A sample project using spring boot.

## Libraries

- `Maven`
- `Spring boot`
- `Spring Data Jpa`
- `HSQLDB`
- `Spring MVC`

## Running 

```shell
mvn spring-boot:run
```
OR
```shell
mvn package
java -jar target/cepsearch.jar
```

Access the project via: http://localhost:8080/

## Using API

### ZipCode

**Valid Examples**
- **Zip Codes:** 04675010, 79002240, 22333900, 12345678, 22345678, 32345678, 42345678

**Request**
- **GET:** /address/?zipcode={zipcode}
- **Accept:** application/json
- **Content-Type:** application/json
- **Description:** search for adress info by a zipcode

**Response**
- **200** OK
```html
{
	"id": 1,
	"zipCode": "04675010",
	"street": "Av. Dr. Silva Melo",
	"neighborhood": "Jd. Taquaral",
	"city": "Sao Paulo",
	"state": "Sao Paulo"
}
```

OR

- **400** BadRequest
```html
{
	"statusCode": 400,
	"errors":[
		{
			"message": "Invalid Zip Code"
		}
	]
}
```
```html
{
	"statusCode": 400,
	"errors":[
		{
			"message": "Not Found Zip Code"
		}
	]
}
```