package ru.mtuci.simpleapiiuk.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mtuci.simpleapiiuk.model.Account;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Account a WHERE a.id=:id") //?
    int delete(@Param("id") Long id);
}
