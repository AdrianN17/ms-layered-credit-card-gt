package com.bank.credit_card.clients.convert;

import com.bank.credit_card.clients.commons.DocumentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DocumentTypeConverter implements AttributeConverter<DocumentType, Short> {

    @Override
    public Short convertToDatabaseColumn(DocumentType attribute) {
        return attribute == null ? null : (short) attribute.getValue();
    }

    @Override
    public DocumentType convertToEntityAttribute(Short dbData) {
        return dbData == null ? null : DocumentType.ofValue(dbData.intValue()).orElse(null);
    }
}

