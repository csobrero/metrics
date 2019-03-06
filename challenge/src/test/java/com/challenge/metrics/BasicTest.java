package com.challenge.metrics;

import java.util.List;
import java.util.stream.DoubleStream;

import org.junit.Test;

import com.challenge.metrics.core.Metric;
import com.challenge.metrics.core.MetricValue;
import com.challenge.metrics.core.Operation;

public class BasicTest {

	@Test
	public void test() {
		
		Metric metric = new Metric("m1");
		
		metric.addValue(5D);
		metric.addValue(2.4D);
		metric.addValue(2.6D);
		metric.addValue(3D);
		metric.addValue(1D);
		
		List<MetricValue> list = metric.getValues();
		
		DoubleStream values = list.stream().mapToDouble(MetricValue::getValue);
		
		System.out.println("average: " + values.average());
		
		values = list.stream().mapToDouble(MetricValue::getValue);
		System.out.println("max: " + values.max());
		
		values = list.stream().mapToDouble(MetricValue::getValue);
		System.out.println("min: " + values.min());
		
		values = list.stream().mapToDouble(MetricValue::getValue);
		values = values.sorted();
		double median = list.size()%2 == 0?
			    values.skip(list.size()/2-1).limit(2).average().getAsDouble():        
			    values.skip(list.size()/2).findFirst().getAsDouble();
			    
		System.out.println("median: " + median);
		
		Double opMin = Operation.valueOf("average".toUpperCase()).calculate(list, MetricValue::getValue);
		System.out.println("min: " + opMin);
		
		
		
	}
	
	@Test
	public void test2() {
	}

}
