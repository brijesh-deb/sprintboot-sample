package com.example.sampleJsonXMLConverter.controller;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.validation.ConstraintViolation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sampleJsonXMLConverter.model.NumericParameter;
import com.example.sampleJsonXMLConverter.model.TabularRow;
import com.example.sampleJsonXMLConverter.model.TransactionDetail;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/sample")
public class controller
{
	// 1. Shows how same endpoint can be used for both JSON and XML. JSON is default here
	// 2. URI for JSON: localhost:8080/sample/transaction
	// 3. URI for XML: localhost:8080/sample/transaction?mediaType=XML
	@RequestMapping(value = "/transaction", method = RequestMethod.GET,produces = {"application/json", "application/xml" })
    public ResponseEntity<Object> getProjectByScenarioNo() 
	{
		TransactionDetail transaction = new TransactionDetail();
		transaction = new TransactionDetail();
		transaction.setCustomerName("John");
		transaction.setAddress("dummy street");

		NumericParameter numericParameter = null;

		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(250));
		numericParameter.setPrecision(2);
		numericParameter.setUnit("rs");
		transaction.setTotalBill(numericParameter);
			
		Map<String,TabularRow> items = new HashMap<String,TabularRow>();
		TabularRow item =null;
			
		// Item 1
		item = new TabularRow();
		item.setItemName("Rice");
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(5));
		numericParameter.setPrecision(0);
		numericParameter.setUnit("kg");
		item.setQuantity(numericParameter);
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(40));
		numericParameter.setPrecision(2);
		numericParameter.setUnit("rs");
		item.setPrice(numericParameter);
		items.put("01", item);

		transaction.setItems(items);
		return new ResponseEntity<Object>(transaction, HttpStatus.OK);
    }
	
	// 1. From Java Model create JSON String and JSON file
	// 2. From Java Model create XML String and XML file
	@RequestMapping(value = "/createjsonxml",method = RequestMethod.POST)
    public void createJSONnXML() 
    {
		TransactionDetail transaction = new TransactionDetail();
		transaction.setCustomerName("John");
		transaction.setAddress("dummy street");

		NumericParameter numericParameter = null;

		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(250));
		numericParameter.setPrecision(2);
		numericParameter.setUnit("rs");
		transaction.setTotalBill(numericParameter);
		
		Map<String,TabularRow> items = new HashMap<String,TabularRow>();
		TabularRow item =null;
		
		// Item 1
		item = new TabularRow();
		item.setItemName("Rice");
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(5));
		numericParameter.setPrecision(0);
		numericParameter.setUnit("kg");
		item.setQuantity(numericParameter);
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(40));
		numericParameter.setPrecision(2);
		numericParameter.setUnit("rs");
		item.setPrice(numericParameter);
		items.put("01", item);

		// Item 2
		item = new TabularRow();
		item.setItemName("Milk");
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(2));
		numericParameter.setPrecision(0);
		numericParameter.setUnit("lt");
		item.setQuantity(numericParameter);
		numericParameter = new NumericParameter(); 
		numericParameter.setValue(new Double(20));
		numericParameter.setPrecision(2);
		numericParameter.setUnit("rs");
		item.setPrice(numericParameter);
		items.put("02", item);
		
		transaction.setItems(items);

		ObjectMapper mapper = new ObjectMapper();

		// Write to a file
    	try
    	{
    		mapper.writeValue(new File("./ShoppingBill.json"), transaction);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex);
    	}
    	
    	// Write as JSON string
    	String JSONString;
    	try
    	{
    		JSONString = mapper.writeValueAsString(transaction);
    		System.out.println(JSONString);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.toString());
    	}
    	
    	try
    	{
        	// Write XML to file
    		JAXBContext jaxbContext = JAXBContext.newInstance(TransactionDetail.class);
    		Marshaller marshaller = jaxbContext.createMarshaller();
    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		marshaller.marshal(transaction, new File("./ShoppingBill.xml"));
    		marshaller.marshal(transaction, System.out);
    		
    		// Write XML as String
    		StringWriter sw = new StringWriter();
    		marshaller.marshal(transaction, sw);
    		String xmlString = sw.toString();
    		System.out.println(xmlString);
    	}	
    	catch(Exception ex)
    	{
    		System.out.println(ex.toString());
    	}
	}
	
	// 1. From JSON String create Java Model
	// 2. Extract values from the Java Model
	@RequestMapping(value = "/postJSON",method = RequestMethod.POST)
    public void postJSON(@RequestBody String file) 
    {
		ObjectMapper mapper = new ObjectMapper();
		TransactionDetail transaction = null;
		
		try
    	{
			// Convert JSON String into Java Model
			transaction = mapper.readValue(file, TransactionDetail.class);
			
			// validate java model
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<TransactionDetail>> violations = validator.validate(transaction);
			for (ConstraintViolation<TransactionDetail> violation : violations) {
			    System.out.println(violation.getMessage()); 
			}
			
			//Extract Data 
			String customerName = transaction.getCustomerName();
			String address = transaction.getAddress();
			Double totalBill = transaction.getTotalBill().getValue();
			
			Map<String,TabularRow> items = transaction.getItems();
			TabularRow item = items.get("01");
			String itemName = item.getItemName();
			
			System.out.println("End");
    	}
       	catch(Exception ex)
       	{
    		System.out.println(ex.toString());
       	}
    }
	
	// 1. From JSON String create Java Model
	// 2. Extract values from the Java Model
	// 3. Create JSON String from the Java Model
	@RequestMapping(value = "/postXML",method = RequestMethod.POST)
    public void postXML(@RequestBody String file) 
    {
		TransactionDetail transaction = null;
		
		// Convert XML String into Java Model
		try
		{
			transaction = JAXB.unmarshal(new StringReader(file), TransactionDetail.class);
		}
		catch(Exception ex)
		{
    		System.out.println(ex.toString());
		}

		// validate java model
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<TransactionDetail>> violations = validator.validate(transaction);
		for (ConstraintViolation<TransactionDetail> violation : violations) {
		    System.out.println(violation.getMessage()); 
		}
		
		// Extract Data from incoming XML
		String customerName = transaction.getCustomerName();
		String address = transaction.getAddress();
		Double totalBill = transaction.getTotalBill().getValue();
		
		Map<String,TabularRow> items = transaction.getItems();
		TabularRow item = items.get("01");
		String itemName = item.getItemName();

    	// Write back incoming XML as JSON string
    	String JSONString;
		ObjectMapper mapper = new ObjectMapper();
    	try
    	{
    		JSONString = mapper.writeValueAsString(transaction);
    		System.out.println(JSONString);
    	}
    	catch(Exception ex)
    	{
    		System.out.println(ex.toString());
    	}
    }
	
	// 1. Usage of Java bean validation
	// 2. Put some elements in JSON String as null and check validation
	@RequestMapping(value = "/postJSON/validate",method = RequestMethod.POST)
    public void validation(@RequestBody String file) 
    {
		// Test Input JSON String = {"customerName":null,"address":"dummy street","totalBill":{"value":null,"unit":"rs","precision":2},"items":{"01":{"itemName":null,"quantity":{"value":null,"unit":"kg","precision":0},"price":{"value":40.0,"unit":"rs","precision":2}},"02":{"itemName":"Milk","quantity":{"value":2.0,"unit":"lt","precision":0},"price":{"value":20.0,"unit":"rs","precision":2}}}}
		ObjectMapper mapper = new ObjectMapper();
		TransactionDetail transaction = null;
		
		try
    	{
			// Convert JSON String into Java Model
			transaction = mapper.readValue(file, TransactionDetail.class);
			
			// validate java model
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<TransactionDetail>> violations = validator.validate(transaction);
			for (ConstraintViolation<TransactionDetail> violation : violations) {
			    System.out.println(violation.getMessage()); 
			}
    	}
       	catch(Exception ex)
       	{
    		System.out.println(ex.toString());
       	}
    }
	
}