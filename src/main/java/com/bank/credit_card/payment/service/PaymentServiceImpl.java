package com.bank.credit_card.payment.service;

import com.bank.credit_card.payment.dto.PaymentRequestDto;
import com.bank.credit_card.payment.dto.PaymentResponseDto;
import com.bank.credit_card.payment.entity.PaymentEntity;
import com.bank.credit_card.payment.mapper.PaymentMapper;
import com.bank.credit_card.payment.repository.PaymentRepository;
import com.bank.credit_card.payment.usecase.PaymentUseCase;
import com.bank.credit_card.payment.usecase.PaymentUseCaseFactory;
import com.bank.credit_card.generic.exception.UnprocessableEntityException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.bank.credit_card.payment.exception.PaymentErrorMessage.PAYMENT_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public void save(PaymentRequestDto request) {
        var entity = paymentMapper.toEntity(request);
        paymentRepository.save(entity);
    }

    @Override
    public void validate(BigDecimal available,
                         BigDecimal total,
                         LocalDate startDate,
                         LocalDate endDate,
                         PaymentRequestDto request) {


        PaymentUseCase useCase = PaymentUseCaseFactory.create(
                request.amount(),
                LocalDate.now(),
                request.category()
        );

        useCase.validateIfPaymentIsPossible(available,
                total,
                startDate,
                endDate);
    }

    @Override
    public PaymentResponseDto get(UUID id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException(PAYMENT_NOT_FOUND));

        return paymentMapper.toDto(entity);
    }


    @Override
    public List<PaymentResponseDto> findAll(String cardId, LocalDate start, LocalDate end) {
        return paymentRepository.findByCardIdAndPaymentDateBetween(cardId, start, end)
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        PaymentEntity entity = paymentRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException(PAYMENT_NOT_FOUND));

        PaymentUseCase useCase = PaymentUseCaseFactory.create(
                entity.getAmount(),
                entity.getPaymentApprobationDate().toLocalDate(),
                entity.getCategory()
        );

        useCase.close();

        paymentRepository.softDelete(id);
    }

}
