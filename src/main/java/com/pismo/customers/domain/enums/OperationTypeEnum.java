package com.pismo.customers.domain.enums;

public enum OperationTypeEnum {

    PURCHASE(1, "PURCHASE"),
    INSTALLMENT_PURCHASE(2, "INSTALLMENT PURCHASE"),
    WITHDRAWAL(3, "WITHDRAWAL"),
    PAYMENT(4, "PAYMENT");

    private final int key;
    private final String value;

    OperationTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static OperationTypeEnum getByKey(final int key) {
        for (OperationTypeEnum type : OperationTypeEnum.values()) {
            if (type.getKey() == key) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid Operation Type: " + key);
    }
}

