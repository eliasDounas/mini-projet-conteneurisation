package com.notes_etudiants.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.notes_etudiants.entity.Etudiant;
import com.notes_etudiants.entity.Note;
import com.notes_etudiants.repository.NoteRepository;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getNotesByEtudiantId(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId);
    }

    public Note addNoteToEtudiant(Etudiant etudiant, String nomDuCours, double valeurDeNote) {
        Note note = new Note();
        note.setNomDuCours(nomDuCours);
        note.setValeurDeNote(valeurDeNote);
        note.setEtudiant(etudiant);
        return noteRepository.save(note);
    }
}
