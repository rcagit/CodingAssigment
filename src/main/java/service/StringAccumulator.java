package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.google.common.base.Splitter;

public class StringAccumulator {

	final static Logger logger = Logger.getLogger(StringAccumulator.class);

	public static void main(String[] args) throws Exception {
		StringAccumulator accumulator = new StringAccumulator();
		System.out.println(accumulator.add("1\n2,3,4\n1001"));

		System.out.println(accumulator.add("1"));
		System.out.println(accumulator.add(""));
		System.out.println(accumulator.add("1,\n"));
		System.out.println(accumulator.add("//***|%%%\n12***34%%%23"));

	}

	public int add(String numbers) throws NegativeNumberNotAllowedExeption  {

		Queue<String> delimiterQueue = new LinkedList<>();

		String numbersToProcess = null;

		String delimiter = null;

		if ("".equals(numbers)) {
			return 0;
		} else if (numbers.startsWith("//")) {

			delimiter = numbers.substring(numbers.indexOf("//") + 2,
					numbers.indexOf("\n"));

			delimiterQueue = new LinkedList<>(Arrays.asList(delimiter
					.split("|")));
			numbersToProcess = numbers.substring(numbers.indexOf("\n") + 1,
					numbers.length());
		}

		else if (Character.isDigit(numbers.charAt(numbers.length() - 1))) {

			delimiterQueue.add(",");
			delimiterQueue.add("\n");
			numbersToProcess = numbers;
		} else {
			System.out.println(" Input is not OK");

			return 0;

		}

		List<Integer> negativeNumbers = new ArrayList<>();
		
		logger.debug(" The inputString "+numbers);
		logger.debug(" The numbersToProcess "+numbersToProcess);
		logger.debug(" The delimiters "+delimiterQueue);

		List<Integer> result = add(numbersToProcess, delimiterQueue,
				negativeNumbers);

		if (result.get(0) < 0) {

			logger.debug(" The negative numbers "+negativeNumbers);

			throw new NegativeNumberNotAllowedExeption("Negatives not allowed " + result);
		}

		return result.get(0);
	}

	private List<Integer> add(String integerString,
			Queue<String> delimiterQueue, List<Integer> negativeNumbers) {
		final AtomicInteger sum = new AtomicInteger(0);

		List<Integer> result = new ArrayList<>();

		if (delimiterQueue.isEmpty()) {
			result.add(Integer.parseInt(integerString));
			return result;
		}
		String delimiter = delimiterQueue.poll();
		Splitter.on(delimiter)
				.omitEmptyStrings()
				.splitToList(integerString)
				.forEach(
						s -> {
							{
								try {
									int n = Integer.parseInt(s);
									if (n < 0) {
										/*
										 * throw new RuntimeException(
										 * "Negatives are not allowed");
										 */
										negativeNumbers.add(n);
									}
									if (n <= 1000) {
										sum.addAndGet(n);
									}
								} catch (NumberFormatException nm) {
									Queue<String> localQueue = new LinkedList<>(
											delimiterQueue);
									sum.addAndGet(add(s, localQueue,
											negativeNumbers).get(0));

								}
							}
						});
		if (negativeNumbers.size() > 0) {
			result = negativeNumbers;
		} else {

			result.add(sum.intValue());
		}

		return result;
	}
}
