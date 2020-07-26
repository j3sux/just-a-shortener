package com.shortener.app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shortener.app.model.Urlmap;

@Repository
public interface UrlmapRepository  extends JpaRepository<Urlmap, Long>{
	
	Urlmap findByAlias(String alias);
	Urlmap findByUrl(String url);
	
}