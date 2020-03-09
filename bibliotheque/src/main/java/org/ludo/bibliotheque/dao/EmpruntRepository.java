package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

}
