## Spring Boot implementation scenarios
This sample shows different scenarios in implementing SpringBoot based microservice

#### 1. Using same end-point for different MIME types
- URI for JSON: localhost:8080/sample/transaction
- URI for XML: localhost:8080/sample/transaction?mediaType=XML
- Here application/json is the default MIME type
#### 2. Create JSON and XML from Java Model
- URI: localhost:8080/sample/createjsonxml
- Creates JSON String/file and XML String/file from TransactionDetail java model
- ShoppingBill.json and ShoppingBill.xml are created in base folder
#### 3. Create Java Model from JSON string and extract values
- URI for JSON: localhost:8080/sample/postJSON
- For RequestBody use content of ShoppingBill.json created in section 2
- Create Java model from input JSON string using ObjectMapper
- Individual values are extracted from the Java model
#### 3. Create Java Model from XML string and extract values
- URI for JSON: localhost:8080/sample/postXML
- For RequestBody use content of ShoppingBill.xml created in section 2
- Create Java model from input XML string using JAXB unmarshal
- Individual values are extracted from the Java model
#### 4. Java bean validation using JSR 380
- URI for JSON: localhost:8080/sample/postJSON/validate
- RequestBody
```
{
	"customerName": null,
	"address": "dummy street",
	"totalBill": {
		"value": null,
		"unit": "rs",
		"precision": 2
	},
	"items": {
		"01": {
			"itemName": null,
			"quantity": {
				"value": null,
				"unit": "kg",
				"precision": 0
			},
			"price": {
				"value": 40.0,
				"unit": "rs",
				"precision": 2
			}
		},
		"02": {
			"itemName": "Milk",
			"quantity": {
				"value": 2.0,
				"unit": "lt",
				"precision": 0
			},
			"price": {
				"value": 20.0,
				"unit": "rs",
				"precision": 2
			}
		}
	}
}
```


