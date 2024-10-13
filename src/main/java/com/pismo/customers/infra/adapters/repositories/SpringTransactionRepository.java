package com.pismo.customers.infra.adapters.repositories;

import com.pismo.customers.infra.adapters.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringTransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
