## Spring Boot implementation scenarios
This sample shows different scenarios in implementing SpringBoot based microservice
	- Centralized exception handling using ControllerAdvice
	- Same end-point for different media types
	- Javabean validation using JSR 380
	- Using JSON/XML 

#### 1. Using same end-point for different media types
- URI for JSON: localhost:8090/sample/transaction
- URI for XML: localhost:8090/sample/transaction?mediaType=XML
- Here application/json is the default media type
- Implementation classes: sampleWebConfig
#### 2. Create JSON and XML from Java Model
- URI: localhost:8090/sample/createjsonxml
- Creates JSON String/file and XML String/file from TransactionDetail java model
- ShoppingBill.json and ShoppingBill.xml are created in base folder
- Implementation: @XmlRootElement and @JsonIgnoreProperties in TransactionDetail
#### 3. Create Java Model from JSON string and extract values
- URI for JSON: localhost:8090/sample/postJSON
- For RequestBody use content of ShoppingBill.json created in section 2
- Create Java model from input JSON string using ObjectMapper
- Individual values are extracted from the Java model
#### 3. Create Java Model from XML string and extract values
- URI for JSON: localhost:8090/sample/postXML
- For RequestBody use content of ShoppingBill.xml created in section 2
- Create Java model from input XML string using JAXB unmarshal
- Individual values are extracted from the Java model
#### 4. Java bean validation using JSR 380
- URI for JSON: localhost:8090/sample/postJSON/validate
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
- In JSON String input, customerName, totalBill value, itemName and quantity value for item 01 is null.
- Following violation message will be printed
```
Value cannot be null
Item Name is compulsory
Customer Name is compulsory
Value cannot be null
```
- Implementation: Validation annotations in NumericParameter, TabularRow and TransactionDetail. @Valid used for nested class.
#### 5. Read file resource folder in jar
- URI for JSON: localhost:8090/sample/readresource

