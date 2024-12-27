package com.notes_etudiants.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.notes_etudiants.entity.Etudiant;
import com.notes_etudiants.entity.Note;
import com.notes_etudiants.repository.EtudiantRepository;
import com.notes_etudiants.repository.NoteRepository;

import dto.EtudiantDTO;

@Service
public class EtudiantService {
	private final EtudiantRepository etudiantRepository;
    private final NoteRepository noteRepository;

    public EtudiantService(EtudiantRepository etudiantRepository, NoteRepository noteRepository) {
        this.etudiantRepository = etudiantRepository;
        this.noteRepository = noteRepository;
    }

    public Etudiant createEtudiant(String nom) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setDateDeCreation(new Date());
        return etudiantRepository.save(etudiant);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }
    
    public List<EtudiantDTO> getAllEtudiantsWithAverages() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<EtudiantDTO> etudiantDTOs = new ArrayList<>();

        for (Etudiant etudiant : etudiants) {
            double moyenne = calculateAverageNoteForEtudiant(etudiant.getId());
            EtudiantDTO dto = new EtudiantDTO(
                etudiant.getId(),
                etudiant.getNom(),
                etudiant.getDateDeCreation(),
                moyenne
            );
            etudiantDTOs.add(dto);
        }

        return etudiantDTOs;
    }

    private double calculateAverageNoteForEtudiant(Long etudiantId) {
        List<Note> notes = noteRepository.findByEtudiantId(etudiantId);
        if (notes.isEmpty()) {
            return 0.0;
        }

        double sum = notes.stream()
            .mapToDouble(Note::getValeurDeNote)
            .sum();

        return sum / notes.size();
    }
}
