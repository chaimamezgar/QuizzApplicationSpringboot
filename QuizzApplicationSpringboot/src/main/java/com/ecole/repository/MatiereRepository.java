package com.ecole.repository;

import com.ecole.domain.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository  extends JpaRepository<Matiere, Long> {

}
