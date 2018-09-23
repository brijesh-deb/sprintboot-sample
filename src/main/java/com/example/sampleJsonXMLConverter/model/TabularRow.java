package com.example.sampleJsonXMLConverter.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TabularRow implements Serializable
{
	private static final long serialVersionUID = -232691436798773261L;
	
	@NotBlank(message = "Item Name is compulsory")
	private String itemName;
	@Valid	
	private NumericParameter quantity;
	@Valid	
	private NumericParameter price;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public NumericParameter getQuantity() {
		return quantity;
	}
	public void setQuantity(NumericParameter quantity) {
		this.quantity = quantity;
	}
	public NumericParameter getPrice() {
		return price;
	}
	public void setPrice(NumericParameter price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}