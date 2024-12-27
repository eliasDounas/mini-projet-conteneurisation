package com.notes_etudiants.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_du_cours", nullable = false)
    private String nomDuCours;

    @Column(nullable = false)
    private double valeurDeNote;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

	public String getNomDuCours() {
		return nomDuCours;
	}

	public void setNomDuCours(String nomDuCours) {
		this.nomDuCours = nomDuCours;
	}

	public double getValeurDeNote() {
		return valeurDeNote;
	}

	public void setValeurDeNote(double valeurDeNote) {
		this.valeurDeNote = valeurDeNote;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
    
    
    
    
}
