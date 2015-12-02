package io.pivotal.mastercard.payment.history.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA entity for a payment
 * @author Brian Jimerson
 *
 */
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String cardNumber;
	
	@Column(nullable=false)
	private String cvc;
	
	@Column(nullable=false)
	private Integer expirationMonth;
	
	@Column(nullable=false)
	private Integer expirationYear;
	
	@Column(nullable=false)
	private Double amount;
	
	@Column
	private String description;
	
	@Column
	private String status;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cvc
	 */
	public String getCvc() {
		return cvc;
	}

	/**
	 * @param cvc the cvc to set
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	/**
	 * @return the expirationMonth
	 */
	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	/**
	 * @param expirationMonth the expirationMonth to set
	 */
	public void setExpirationMonth(Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	/**
	 * @return the expirationDate
	 */
	public Integer getExpirationYear() {
		return expirationYear;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationYear(Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Overrides the toString method to provide the
	 * state of the object.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Payment = [id: ");
		sb.append(this.getId());
		sb.append(", cardNumber: ");
		sb.append(this.getCardNumber());
		sb.append(", cvc: ");
		sb.append(this.getCvc());
		sb.append(", expirationMonth: ");
		sb.append(this.getExpirationMonth());
		sb.append(", expirationYear: ");
		sb.append(this.getExpirationYear());
		sb.append(", description: ");
		sb.append(this.getDescription());
		sb.append(", status: ");
		sb.append(this.getStatus());
		sb.append("]");
		return sb.toString();
	}
	
}
