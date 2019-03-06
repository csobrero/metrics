package com.challenge.metrics.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Metric {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@OneToMany(mappedBy = "metric", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
	private List<MetricValue> values;
	
	public Metric() {
	}

	public Metric(String name) {
		this.name = name;
	}
	
	public void addValue(Double value) {
		if(values==null) {
			values=new ArrayList<MetricValue>();
		}
		values.add(new MetricValue(this, value));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MetricValue> getValues() {
		return values;
	}

	public void setValues(List<MetricValue> values) {
		this.values = values;
	}

	
}
