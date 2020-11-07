package ru.mtuci.simpleapiiuk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mtuci.simpleapiiuk.model.Deposit;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Deposit d WHERE d.id=:id")
    int delete(@Param("id") Long id);
}
