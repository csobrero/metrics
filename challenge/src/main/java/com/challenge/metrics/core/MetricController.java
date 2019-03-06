package com.challenge.metrics.core;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.metrics.exception.MetricNotFoundException;

@RestController
public class MetricController {

	@Autowired
	private MetricRepository metricRepository;

	@GetMapping("/metrics/metric")
	public List<Metric> retrieveAllMetrics() {
		return metricRepository.findAll();
	}

	@GetMapping("/metrics/metric/{name}")
	public Metric retrieveMetric(@PathVariable String name) {
		Optional<Metric> Metric = metricRepository.findByName(name);

		if (!Metric.isPresent())
			throw new MetricNotFoundException("id-" + name);

		return Metric.get();
	}

	@PostMapping("/metrics/register/{name}")
	public ResponseEntity<Object> createMetric(@PathVariable String name) {
		Metric savedMetric = metricRepository.save(new Metric(name));

		return ResponseEntity.created(null).build();
	}
	

	@PostMapping("/metrics/add/{metricName}/{value}")
	public ResponseEntity<Object> addMetricValue(@PathVariable String metricName, @PathVariable Double value) {
		
		Optional<Metric> optional = metricRepository.findByName(metricName);

		if (!optional.isPresent())
			throw new MetricNotFoundException("name-" + optional );
			
		Metric metric = optional .get();
		metric.addValue(value);
		
		Metric savedMetric = metricRepository.save(metric);

		return ResponseEntity.created(null).build();
	}
	
	@GetMapping(value="/metrics/summary/{operation}/{metricName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> summaryOperation(@PathVariable String operation, @PathVariable String metricName) throws JSONException {
		Optional<Metric> MetricOpt = metricRepository.findByName(metricName);

		if (!MetricOpt.isPresent())
			throw new MetricNotFoundException("name-" + MetricOpt );
		
		Operation op = Operation.valueOf(operation.toUpperCase());
		
		Double result = op.calculate(MetricOpt.get().getValues(), MetricValue::getValue);
		
		HashMap<String, Object> map = new HashMap<>();
	    map.put("metricName", MetricOpt.get().getName());
        map.put(operation, result);
	    return map;
		
	}

}
