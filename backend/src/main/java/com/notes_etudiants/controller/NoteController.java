package com.notes_etudiants.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notes_etudiants.entity.Etudiant;
import com.notes_etudiants.entity.Note;
import com.notes_etudiants.service.EtudiantService;
import com.notes_etudiants.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final EtudiantService etudiantService;

    
    public NoteController(NoteService noteService, EtudiantService etudiantService) {
        this.noteService = noteService;
        this.etudiantService = etudiantService;
    }

    @GetMapping("/{etudiantId}")
    public List<Note> getNotesByEtudiantId(@PathVariable Long etudiantId) {
        return noteService.getNotesByEtudiantId(etudiantId);
    }

    @PostMapping("/{etudiantId}")
    public Note addNoteToEtudiant(@PathVariable Long etudiantId,
                                  @RequestParam String nomDuCours,
                                  @RequestParam double valeurDeNote) {
        Etudiant etudiant = etudiantService.getEtudiantById(etudiantId);
        return noteService.addNoteToEtudiant(etudiant, nomDuCours, valeurDeNote);
    }
}
