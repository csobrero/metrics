package com.challenge.metrics.core;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.DoubleStream;

public enum Operation {

	AVERAGE {
		@Override
		public <E> Double calculate(List<E> list, ToDoubleFunction<? super E> function) {
			return list.stream().mapToDouble(function).average().getAsDouble();
		}
	},
	MEDIAN {
		@Override
		public <E> Double calculate(List<E> list, ToDoubleFunction<? super E> function) {
			DoubleStream values = list.stream().mapToDouble(function).sorted();
			double median = list.size() % 2 == 0 ? 
					values.skip(list.size() / 2 - 1).limit(2).average().getAsDouble() : 
					values.skip(list.size() / 2).findFirst().getAsDouble();
			return median;
		}
	},
	MAX {
		@Override
		public <E> Double calculate(List<E> list, ToDoubleFunction<? super E> function) {
			return list.stream().mapToDouble(function).max().getAsDouble();
		}
	},
	MIN {
		@Override
		public <E> Double calculate(List<E> list, ToDoubleFunction<? super E> function) {
			return list.stream().mapToDouble(function).min().getAsDouble();
		}
	};

	public abstract <E> Double calculate(List<E> list, ToDoubleFunction<? super E> function);

}
