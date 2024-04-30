package com.rabbitMq.orderService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class OrderedRequest {

	@Id
	@Column
	private String orderId;
	
	@Column
	private String productCode;
	
	@Column
    private int quantity;
		/**
		 * @return the orderId
		 */
		public String getOrderId() {
			return orderId;
		}
		/**
		 * @param orderId the orderId to set
		 */
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		/**
		 * @return the productCode
		 */
		public String getProductCode() {
			return productCode;
		}
		/**
		 * @param productCode the productCode to set
		 */
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		/**
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}
		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

	 
}
