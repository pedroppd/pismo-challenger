package com.pismo.customers.infra.adapters.repositories;

import com.pismo.customers.infra.adapters.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringAccountRepository extends JpaRepository<AccountEntity, Long> {

}
