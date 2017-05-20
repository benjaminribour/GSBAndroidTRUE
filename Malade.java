package com.example.ben.projetgsb;

import java.io.Serializable;

public class Malade implements Serializable {
	
	private int id;
	private String nom;
	private String prenom;
	private double glycemie;

	public Malade(String unNom, String unPrenom, double uneGlycemie){
		this.nom = unNom;
		this.prenom = unPrenom;
		this.glycemie = uneGlycemie; 
	} 
	public int getId() {
		return this.id;
	}	
	public void setId(int unId){
		this.id = unId;
	}
	public String getNom() {
		return this.nom;
	}	
	public String getPrenom() {
		return this.prenom;
	}	
	public double getGlycemie() {
		return this.glycemie;
	}

	@Override
	public String toString() {
		return "Le Malade : " + nom + " " +prenom +" est à " + glycemie + " glycémie";

	}
}

