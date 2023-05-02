package com.ciudades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ciudad")
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idciudad")
	int id;
	
	@Column(name="nomciudad")
	String nom;
	
	@Column(name="numhabs")
	long habs;
	
	public Ciudad() {
		super();
	}

	public Ciudad(String nom, long habs) {
		super();
		this.nom = nom;
		this.habs = habs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getHabs() {
		return habs;
	}

	public void setHabs(long habs) {
		this.habs = habs;
	}
}
