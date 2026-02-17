package com.bank.credit_card.balances.repository;

import com.bank.credit_card.balances.dto.projection.CardAccountDataProjection;
import com.bank.credit_card.balances.dto.projection.PaymentProjection;
import com.bank.credit_card.balances.dto.projection.PaymentResumeProjection;
import com.bank.credit_card.balances.entity.BalanceEntity;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends GenericRepository<BalanceEntity, Long> {


    @Query(value = "SELECT TOP 1 idBalance FROM balances WHERE cardId = :cardId AND status = 1 ORDER BY startDate DESC", nativeQuery = true)
    Optional<Long> findLastBalanceIdByCardId(@Param("cardId") Long cardId);

    @Query(value = "SELECT TOP 1 " +
            "facturationDate as facturationDate, crediticialTotalAmount as totalAmount, currency as currency  " +
            "FROM cardAccounts WHERE cardId = :cardId AND status = 1 ORDER BY startDate DESC", nativeQuery = true)
    Optional<CardAccountDataProjection> findLastCardAccountDataByCardId(@Param("cardId") Long cardId);


    @Query(value = "SELECT TOP 1 availableAmount FROM balances WHERE balanceId = :balanceId AND status = 1 ORDER BY startDate DESC", nativeQuery = true)
    Optional<BigDecimal> findOldBalanceIdById(@Param("cardId") Long balanceId);

    @Query(value = "SELECT SUM(amount) AS totalAmount, currency AS currency " +
            "FROM payments WHERE cardId = :cardId " +
            "AND paymentApprobationDate >= :dateStart AND paymentApprobationDate <= :dateEnd AND status = 1 " +
            "GROUP BY currency", nativeQuery = true)
    List<PaymentResumeProjection> findTotalPaymentAmountByCardId(@Param("cardId") Long cardId,
                                                                 @Param("dateStart") LocalDateTime dateStart,
                                                                 @Param("dateEnd") LocalDateTime dateEnd);

    @Query(value = "SELECT amount AS amount, currency AS currency " +
            "FROM payments WHERE cardId = :cardId " +
            "AND paymentApprobationDate >= :dateStart AND paymentApprobationDate <= :dateEnd AND status = 1", nativeQuery = true)
    List<PaymentProjection> findPaymentsAmountByCardId(@Param("cardId")
                                                       Long cardId, @Param("dateStart") LocalDateTime dateStart,
                                                       @Param("dateEnd") LocalDateTime dateEnd);


    @Query(value = "SELECT SUM(amount) AS totalAmount, currency AS currency " +
            "FROM consumptions WHERE cardId = :cardId " +
            "AND consumptionApprobationDate >= :dateStart AND consumptionApprobationDate <= :dateEnd AND status = 1 " +
            "GROUP BY currency", nativeQuery = true)
    List<PaymentResumeProjection> findTotalConsumptionAmountByCardId(@Param("cardId") Long cardId,
                                                                 @Param("dateStart") LocalDateTime dateStart,
                                                                 @Param("dateEnd") LocalDateTime dateEnd);

    @Query(value = "SELECT amount AS amount, currency AS currency " +
            "FROM consumptions WHERE cardId = :cardId " +
            "AND consumptionApprobationDate >= :dateStart AND consumptionApprobationDate <= :dateEnd AND status = 1", nativeQuery = true)
    List<PaymentProjection> findConsumptionsAmountByCardId(@Param("cardId")
                                                       Long cardId, @Param("dateStart") LocalDateTime dateStart,
                                                       @Param("dateEnd") LocalDateTime dateEnd);
}
