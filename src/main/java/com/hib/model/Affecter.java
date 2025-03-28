package com.hib.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "affecter")
public class Affecter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeaffecter;

    @ManyToOne
    @JoinColumn(name = "codeemp", referencedColumnName = "codeemp", nullable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "codelieu", referencedColumnName = "codelieu", nullable = false)
    private Lieu lieu;

    @Temporal(TemporalType.DATE)
    private Date date;

    // Constructeurs
    public Affecter() {
    }

    public Affecter(Employe employe, Lieu lieu, Date date) {
        this.employe = employe;
        this.lieu = lieu;
        this.date = date;
    }

    // Getters et Setters
    public int getCodeaffecter() {
        return codeaffecter;
    }

    public void setCodeaffecter(int codeaffecter) {
        this.codeaffecter = codeaffecter;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Affecter{" +
               "codeaffecter=" + codeaffecter +
               ", employe=" + (employe != null ? employe.getCodeemp() : "null") +
               ", lieu=" + (lieu != null ? lieu.getCodelieu() : "null") +
               ", date=" + date +
               '}';
    }
}