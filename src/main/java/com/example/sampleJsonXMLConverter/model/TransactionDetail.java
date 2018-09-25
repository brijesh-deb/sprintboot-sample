package com.example.sampleJsonXMLConverter.model;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetail implements Serializable
{
	private static final long serialVersionUID = -232691436798773261L;

	@NotEmpty(message = "Customer Name is compulsory")
	private String customerName;
	@NotBlank(message = "Address is compulsory")
	private String address;
	@Valid	
	@NotNull(message = "TotalBill is compulsory")
	private NumericParameter totalBill;
	@Valid
	@NotNull(message = "Items is compulsory")
	private Map<String,TabularRow> items;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public NumericParameter getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(NumericParameter totalBill) {
		this.totalBill = totalBill;
	}
	public Map<String, TabularRow> getItems() {
		return items;
	}
	public void setItems(Map<String, TabularRow> items) {
		this.items = items;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}