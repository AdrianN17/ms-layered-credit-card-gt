package com.bank.credit_card.payment.service;

import com.bank.credit_card.payment.dto.PaymentRequestDto;
import com.bank.credit_card.payment.dto.PaymentResponseDto;
import com.bank.credit_card.payment.entity.PaymentEntity;
import com.bank.credit_card.payment.exception.PaymentPersistanceException;
import com.bank.credit_card.payment.mapper.PaymentMapper;
import com.bank.credit_card.payment.repository.PaymentRepository;
import com.bank.credit_card.payment.usecase.PaymentUseCase;
import com.bank.credit_card.payment.usecase.PaymentUseCaseFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.bank.credit_card.payment.exception.PaymentErrorMessage.PAYMENT_CATEGORY_NOT_NULL;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public void save(PaymentRequestDto request, String cardId) {
        PaymentEntity entity = paymentMapper.toEntity(request, cardId);
        paymentRepository.save(entity);
    }

    @Override
    public void validate(BigDecimal available,
                         BigDecimal total,
                         LocalDateTime startDate,
                         LocalDateTime endDate,
                         PaymentRequestDto request) {


        PaymentUseCase useCase = PaymentUseCaseFactory.create(
                request.amount(),
                LocalDate.now(),
                request.category()
        );

        useCase.validateIfPaymentIsPossible(available,
                total,
                startDate.toLocalDate(),
                endDate.toLocalDate());
    }


    @Override
    public List<PaymentResponseDto> findAll(String cardId, LocalDateTime start, LocalDateTime end) {
        return paymentRepository.findByCardIdAndPaymentDateBetween(cardId, start, end)
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentPersistanceException(PAYMENT_CATEGORY_NOT_NULL));

        PaymentUseCase useCase = PaymentUseCaseFactory.create(
                entity.getAmount(),
                entity.getPaymentApprobationDate().toLocalDate(),
                entity.getCategory()
        );

        useCase.close();

        paymentRepository.softDelete(id);
    }

}
