package com.shortener.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "urlmap")
public class Urlmap {
	
	@Transient
	@JsonIgnore
	@GeneratedValue
	private long id;
	private String url;
	private String alias;
	
	public Urlmap() {
	}
	
	public Urlmap(String url, String alias) {
		this.url = url;
		this.alias = alias;
	}	
	
	public String getUrl() {
		return url;
	}
	
	@Column(name = "url", nullable = false)
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "alias", nullable = false)
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }    

}
