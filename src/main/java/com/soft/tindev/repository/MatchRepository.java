package com.soft.tindev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soft.tindev.modelo.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{
	
}
