package com.bank.credit_card.generic.repository;

import com.bank.credit_card.generic.commons.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    List<T> findByStatus(Status status);

    default List<T> findAllActive() {
        return findByStatus(Status.ACTIVE);
    }

    Optional<T> findByIdAndStatus(ID id, Status status);

    boolean existsByIdAndStatus(ID id, Status status);

    @Override
    default Optional<T> findById(ID id) {
        return findByIdAndStatus(id, Status.ACTIVE);
    }

    @Override
    default boolean existsById(ID id) {
        return existsByIdAndStatus(id, Status.ACTIVE);
    }
}
