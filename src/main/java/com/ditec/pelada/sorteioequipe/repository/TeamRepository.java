package com.ditec.pelada.sorteioequipe.repository;

import com.ditec.pelada.sorteioequipe.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
