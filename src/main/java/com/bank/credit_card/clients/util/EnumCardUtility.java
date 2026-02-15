package com.bank.credit_card.clients.util;

import com.bank.credit_card.clients.commons.CategoryCard;
import com.bank.credit_card.clients.commons.DocumentType;
import com.bank.credit_card.clients.commons.TypeCard;
import org.mapstruct.Named;

public class EnumCardUtility {

    @Named("mapToTypeCard")
    public TypeCard mapToTypeCard(String code) {
        return TypeCard.fromCode(code);
    }

    @Named("typeCardToString")
    public String typeCardToString(TypeCard typeCard) {
        return typeCard.getCode();
    }

    @Named("mapToCategoryCard")
    public CategoryCard mapToCategoryCard(String code) {
        return CategoryCard.fromCode(code);
    }

    @Named("categoryCardToString")
    public String categoryCardToString(CategoryCard categoryCard) {
        return categoryCard.getCode();
    }

    @Named("mapToDocumentType")
    public DocumentType mapToDocumentType(String code) {
        return DocumentType.fromCode(code);
    }

    @Named("documentTypeToString")
    public String documentTypeToString(DocumentType documentType) {
        return documentType.getCode();
    }
}
