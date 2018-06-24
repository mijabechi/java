package PrimeCalculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PrimeCalculation {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * This method always return true, when a number is prime.
	 * This solution use "for" to evaluate the behavior.
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this solution is 2375 % slower than the custom option 
	 * 
	 * The result of this test to evaluate 524287 is 0.034682762 seconds 
	 * @param  number to be evaluate to know if is or not a prime
	 * @return     true when it is a prime number
	 */
	public boolean isPrimeNumberOptionFor(int number) {
		int max = (int) (number / 2);
		for (int i = 2; i < max+1; i++) {
			if((number % i) == 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method always return true, when a number is prime.
	 * This solution use "recursive function" to evaluate the behavior.
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this solution is 9241 %  slower than the custom option 
	 * 
	 * The result of this test to evaluate 524287 is 0.134948752 seconds
	 * @param  number to be evaluate to know if is or not a prime
	 * @return     true when it is a prime number
	 */
	public boolean isPrimeNumberOptionRecursive(int numberToEvaluate) {
		int max= (int) (numberToEvaluate / 2);
		return calculateIsPrimeNumber(2,numberToEvaluate,max+1);
	}
	
	private boolean calculateIsPrimeNumber(int increment ,int number, int max2) {
		if(increment == max2)
			return true;
		else if((number % increment) == 0){
			return false;
		}
		boolean result = calculateIsPrimeNumber(increment+1,number, max2);
		return result;
	}

	/**
	 * This method always return true, when a number is prime.
	 * This solution use "custom implementation that use combinations of for 
	 * and a math formula" to evaluate the behavior.
	 * 
	 * The math formula consist in:
	 * 
	 * 1. use the squareRoot to avoid evaluate big numbers
	 * 2. after is necessary identify a list of primes numbers 
	 * between 1 to the next prime number of the result of squareRoot
	 * 3. evaluate the number module every number of the list of prime numbers
	 * if the validations are different of number % list = 0 then it is a prime number.
	 * 
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this is the best implementation
	 * 
	 * Option 1 = 0.034682762 seconds
	 * Option 2 = 0.134948752 seconds
	 * Option 3 = 0.001460264 seconds *******Best*******
	 * 
	 * The result of this test to evaluate 524287 is 0.001460264 seconds 
	 * @param  number to be evaluate to know if is or not a prime
	 * @return     true if it is a prime number
	 */
	public boolean isPrimeNumberOptionCustom(int numberToEvaluate) {
		double dato = (Math.sqrt(numberToEvaluate));
		int trimdato = (int) dato;
		int limit = getNextPrimeNumber(trimdato);
		List<Integer> primes = getPrimes(limit,(int) limit);
		return isPrimeNumberOptionCustom(numberToEvaluate, primes);
	}
	
	private boolean isPrimeNumberOptionCustom(long value, List<Integer> primes) {
		boolean istrue = isPrimeShortValidationImprovement(value);
		if(istrue) {
			double squareRoot = (Math.sqrt(value));
			int trimSquareRoot = (int)squareRoot;
			long limit = getNextPrimeNumber(trimSquareRoot);
			istrue = isPrimeCustomValidation(primes,value, limit);
		}else {
			//System.out.println(value);
		}
		return istrue;
	}
	
	private int getNextPrimeNumber(int value) {
		int result = 0;
		for (int i = value+1; i < (value + value); i++) {
			if(isPrimeNumberOptionFor(i)) {
				result = i;
				break;
			}
		}
		return result;
	}
	
	private boolean isPrimeShortValidationImprovement(long refere) {
		int max = (int) (refere > 12 ? 11: refere -1);
		boolean result = true;
		for (int i = max; i >= 2;i--) {
			if(refere % i == 0) {
				result = false;
				break;
			}
		}/*
		for (int i = 2; i < max;i++) {
			if(refere % i == 0) {
				result = false;
				break;
			}
		}*/
		return result;
	}

	private boolean isPrimeCustomValidation(List<Integer> primes,long val, long limit) {
		boolean isPrime = true;
		for (int i = 0; i < primes.size(); i++) {
			int number = primes.get(i);
			if(number > limit) {
				break;
			}
			else if( val % number == 0) {				
				isPrime = false;
				break;
			} 
				
		}
		return isPrime;
	}
	
	private List<Integer> getPrimes(long value, int max) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 2; i <= max; i++) {
			if(isPrimeNumberOptionFor(i)) {
				result.add(i);
			}
		}
		return result;
	}
	
	/**
	 * This method get the quantity of prime numbers between 1 to 1000000.
	 * This solution use "for" to evaluate the behavior.
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this solution is  5837 %  slower than the custom option 
	 * 
	 * The result of this test is 66.098248051 seconds 
	 * @param  limit number of limit to be evaluate
	 * @return  quantity of numbers between 1 to 1000000.
	 */
	public List<Integer> getQuantityOfPrimesForOption(int limit) {
		List<Integer> primesNumbers = new ArrayList<>();
		for (int i = 2; i <= limit; i++) {
			if(isPrimeNumberOptionFor(i)) {
				primesNumbers.add(i);
			}
		}
		return primesNumbers;
	}

	/**
	 * This method get the quantity of prime numbers between 1 to 1000000.
	 * This solution use "recursive function" to evaluate the behavior.
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this solution is  9674 % slower than the custom option 
	 * 
	 * The result of this test is 109.540253942 seconds
	 * @param  limit number of limit to be evaluate
	 * @return  quantity of numbers between 1 to 1000000.
	 */
	public List<Integer> getQuantityOfPrimesForRecursive(int valueToEvaluate) {
		List<Integer> primesNumbers = new ArrayList<>();
		for (int i = 2; i <= valueToEvaluate; i++) {
			if(isPrimeNumberOptionRecursive(i)) {
				primesNumbers.add(i);
			}
		}
		return primesNumbers;
	}
	
	/**
	 * This method get the quantity of prime numbers between 1 to 1000000.
	 * This solution use "custom implementation that use combinations of for 
	 * and a math formula" to evaluate the behavior.
	 * 
	 * The math formula consist in:
	 * 
	 * 1. use the squareRoot to avoid evaluate big numbers
	 * 2. after is necessary identify a list of prime numbers 
	 * between 1 to the next prime number of the result of squareRoot
	 * 3. evaluate the number module every number of the list of prime numbers
	 * if the validations are different of number % list = 0 then it is a prime number.
	 * 
	 * It has an improvement to avoid evaluate the entire number, for example: number = 11
	 * when it is evaluating if the number is prime it just use the middle of the number
	 * to be evaluate => 11%2,11%3,11%5,11%7 if it don't have occurrences then it is a prime number.
	 * 
	 * this is the best implementation
	 * 
	 * Option 1 = 66.098248051 seconds
	 * Option 2 = 109.540253942 seconds
	 * Option 3 = 1.132324021 seconds *******Best*******
	 * 
	 * The result of this test is 1.132324021 seconds
	 * @param  limit number of limit to be evaluate
	 * @return  quantity of numbers between 1 to 1000000.
	 */
	public HashMap getQuantityOfPrimesCustomOption(int numberToEvaluate) {
		HashMap primesNumbers = new HashMap<>();
		double squareRoot = (Math.sqrt(numberToEvaluate));
		int trimSquareRoot = (int) squareRoot;
		int limit = getNextPrimeNumber(trimSquareRoot);
		List<Integer> primes = getPrimes(limit,(int) limit);
		HashMap circularNumbers = new HashMap<>();
		int increment = 0;
		for (int i = numberToEvaluate; i >= 2 ; i--) {
			HashMap temporalCircularNumbers = new HashMap<>();
			if(isPrimeNumberOptionCustom(i,primes)) {
				primesNumbers.put(i, 0);
				}
		}
		return primesNumbers;
	}




	public HashMap evaluateCircularPrimeNumbers(int numberToEvaluate) {
		HashMap result = new HashMap<>(); 
		//List<Integer> returnr = new ArrayList<>();
		HashMap primesNumbers = new HashMap<>();
		double squareRoot = (Math.sqrt(numberToEvaluate));
		int trimSquareRoot = (int) squareRoot;
		int limit = getNextPrimeNumber(trimSquareRoot);
		List<Integer> primes = getPrimes(limit,(int) limit);
		HashMap circularNumbers = new HashMap<>();
		for (int i = numberToEvaluate; i >= 2 ; i--) {
			int lenghtBaseNumber = getLength(i);
			HashMap temporalCircularNumbers = new HashMap<>();
			if(isPrimeNumberOptionCustom(i,primes)) {
				int pow = (int) Math.pow(10, lenghtBaseNumber-1);
				int calculatePow = (i % pow)*10;
				int numberDiv = i / pow;
				primesNumbers.put(i, 0);
				int nextValue = calculatePow + numberDiv;
				temporalCircularNumbers.put(nextValue,0);
				if(primesNumbers.containsKey(nextValue)) {
					if(lenghtBaseNumber == 1)
					{
						circularNumbers.put(nextValue, 0);
					}
					for (int j = 1; j < lenghtBaseNumber; j++) {
						
						calculatePow = (nextValue % pow)*10;
						numberDiv = nextValue / pow;
						nextValue = calculatePow + numberDiv;
						temporalCircularNumbers.put(nextValue,0);
						if(lenghtBaseNumber != getLength(nextValue) || nextValue > numberToEvaluate)
							break;
						boolean iiValid = primesNumbers.containsKey(nextValue);
						if(iiValid) {
							if(j == lenghtBaseNumber-1)
								circularNumbers.putAll(temporalCircularNumbers);
						}else {
							break;
						}
					}
					
				}
			}
		}
		result.put(0, primesNumbers);
		result.put(1, circularNumbers);
		return result;
	}

	public List<Integer> evaluateCircularPrimeNumbers(List<Integer> numbers) {
		List<Integer> circularNumbers = new java.util.ArrayList<>();
		int specialCase = 11;
		for (int i = 0; i < numbers.size(); i++) {
				List<Integer> temporalCircularNumbers = new java.util.ArrayList<>();
				Integer number = numbers.get(i);
				evaluateCircularPrimeNumber(numbers, circularNumbers, number, temporalCircularNumbers, number);
				if(temporalCircularNumbers.size() == getLength(number) || number == specialCase) {
					circularNumbers.addAll(temporalCircularNumbers);
				}
			}
		return circularNumbers;
	}

	private void evaluateCircularPrimeNumber(List<Integer> numbers, List<Integer> circularNumbers, int numberBase, List<Integer> temporalCircularNumbers, Integer number) {
		
		int pow = (int) Math.pow(10, getLength(numberBase)-1);
		int calculatePow = (number % pow)*10;
		int numberDiv = number / pow;
		int nextValue = calculatePow + numberDiv;
		if(numbers.contains(nextValue) && !circularNumbers.contains(number) && !temporalCircularNumbers.contains(number)) {
			temporalCircularNumbers.add(number);
		    evaluateCircularPrimeNumber(numbers, circularNumbers, numberBase, temporalCircularNumbers, nextValue);
		}
	}
	
	private int getLength(int number) {
		
		if (number < 100000) {
		    if (number < 100) {
		        if (number < 10) {
		            return 1;
		        } else {
		            return 2;
		        }
		    } else {
		        if (number < 1000) {
		            return 3;
		        } else {
		            if (number < 10000) {
		                return 4;
		            } else {
		                return 5;
		            }
		        }
		    }
		} else {
		    if (number < 10000000) {
		        if (number < 1000000) {
		            return 6;
		        } else {
		            return 7;
		        }
		    } else {
		        if (number < 100000000) {
		            return 8;
		        } else {
		            if (number < 1000000000) {
		                return 9;
		            } else {
		                return 10;
		            }
		        }
		    }
		}
	}

}
