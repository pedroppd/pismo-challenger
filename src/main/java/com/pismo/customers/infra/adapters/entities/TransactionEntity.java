package com.pismo.customers.infra.adapters.entities;

import com.pismo.customers.application.controllers.dto.response.TransactionResponseDTO;
import com.pismo.customers.domain.Transaction;
import com.pismo.customers.domain.enums.OperationTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "operation_type_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationTypeEnum operationType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "event_date", nullable = false)
    private String eventDate;

    public TransactionEntity(final Transaction transaction) {
        this.id = transaction.getId();
        this.account = new AccountEntity(transaction.getAccount());
        this.amount = transaction.getAmount();
        this.eventDate = transaction.getEventDate();
        this.operationType = transaction.getOperationType();
    }

    public TransactionResponseDTO toTransactionResponseDTO() {
        return TransactionResponseDTO.builder()
                .id(this.getId())
                .accountId(this.getAccount().getId())
                .operationTypeId(this.getOperationType().getKey())
                .amount(this.getAmount())
                .build();

    }

}
