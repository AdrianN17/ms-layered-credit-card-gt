package com.bank.credit_card.clients.repository;

import com.bank.credit_card.clients.entity.vo.ClientEntityVO;
import com.bank.credit_card.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryVO extends GenericRepository<ClientEntityVO, Long> {
}
