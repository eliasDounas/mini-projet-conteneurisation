package com.notes_etudiants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.notes_etudiants.entity.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}

