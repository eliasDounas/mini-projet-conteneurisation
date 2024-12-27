package com.notes_etudiants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notes_etudiants.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByEtudiantId(Long etudiantId);
}
