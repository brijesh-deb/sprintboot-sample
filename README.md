## Spring Boot implementation scenarios
This sample shows different scenarios in implementing SpringBoot based microservice

### Using same end-point for different MIME types
- URI for JSON: localhost:8080/sample/transaction
- URI for XML: localhost:8080/sample/transaction?mediaType=XML
- Here application/json is the default MIME type
