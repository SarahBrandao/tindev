package com.soft.tindev.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Match {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Usuario liking;
	@ManyToOne
	private Usuario liked;
	
	public Match() {
		
	}
	
	public Match(Usuario liking, Usuario liked) {
		this.liking = liking;
		this.liked = liked;
	}
	
	
	public Usuario getLiking() {
		return liking;
	}
	
	public void setLiking(Usuario liking) {
		this.liking = liking;
	}
	
	public Usuario getLiked() {
		return liked;
	}
	
	public void setLiked(Usuario liked) {
		this.liked = liked;
	}
	
	public int getId() {
		return id;
	}
	
	
}
