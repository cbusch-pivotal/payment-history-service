package io.pivotal.mastercard.payment.history.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.mastercard.payment.history.entity.Payment;

/**
 * JPA repository for a Payment entity
 * @author Brian Jimerson
 *
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
