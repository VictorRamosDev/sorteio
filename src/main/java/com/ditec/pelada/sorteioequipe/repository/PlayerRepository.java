package com.ditec.pelada.sorteioequipe.repository;

import com.ditec.pelada.sorteioequipe.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "select p from Player p where 1=1 and sq.num <= ?", nativeQuery = true)
    List<Player> findByQuantity(int quantity);

    @Query("select p from Player p where 1=1 and p.pot.level = :potLevel")
    List<Player> findByPotLevel(@Param("potLevel") int potLevel);

    Player findByName(String nome);

    List<Player> findByGoalkeeperIsFalseAndActiveIsTrue();

    List<Player> findByGoalkeeperIsTrueAndActiveIsTrue();
}
