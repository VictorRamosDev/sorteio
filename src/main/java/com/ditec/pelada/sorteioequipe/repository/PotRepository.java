package com.ditec.pelada.sorteioequipe.repository;

import com.ditec.pelada.sorteioequipe.model.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotRepository extends JpaRepository<Pot, Integer> {


    Pot findByLevel(Integer level);
}
