package dto;

import java.util.Date;

public class EtudiantDTO {

	private Long id;
    private String nom;
    private Date dateDeCreation;
    private double moyenne; // Field for the average

    // Constructor
    public EtudiantDTO(Long id, String nom, Date dateDeCreation, double moyenne) {
        this.id = id;
        this.nom = nom;
        this.dateDeCreation = dateDeCreation;
        this.moyenne = moyenne;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
}