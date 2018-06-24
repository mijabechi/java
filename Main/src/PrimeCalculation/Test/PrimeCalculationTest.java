package PrimeCalculation.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import PrimeCalculation.*;

/*
 * It was tested in:
 * Processor : 2,5 GHz Intel Core i5
 * Memory : 4 GB 1600 MHz DDR3
 * System : macintosh 
 * Adding paremeter Xss10m to JVM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class PrimeCalculationTest {

	private int numberToEvaluate = 1000000;
	private int primeNumber = 524287;
	
	@Test
	public void test00PrintLn() {
		System.out.println("*********************** IsPrime  ***********************");
		System.out.println("");
	}
	
	/*
	 * Test 1 = 4007888
	 * Test 2 = 2752418
	 * Test 3 = 3978058
	 * Test 4 = 3383154
	 * Test 5 = 3219863
	 * Prom   = 3468276 nanoseconds => 0.0034682762 seconds
	 *
	 */
	@Test
	public void test01ShouldVerifyIfIsPrimeNumberUsingFor() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		boolean isPrime = calculation.isPrimeNumberOptionFor(primeNumber);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 1 FOR");
		System.out.println("Is Prime "+ primeNumber+": "+isPrime+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		assertTrue(isPrime);
	}
	
	/*
	 * This solution must enable the Xss10m in the JVM to avoid the StackOverflow
	 * 
	 * Test 1 = 10002934
	 * Test 2 = 27374986
	 * Test 3 = 18513397
	 * Prom   = 18630439 nanoseconds => 0.018630439 seconds
	 *
	 */
	@Test
	public void test02ShouldVerifyIfIsPrimeNumberUsingRecursiveFuntion() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		boolean isPrime = calculation.isPrimeNumberOptionRecursive(primeNumber);
		
		long afterTime = System.nanoTime();
		System.out.println("Use Option 2 Recursive Function");
		System.out.println("Is Prime "+ primeNumber+": "+isPrime+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		assertTrue(isPrime);
	}
	
	/*
	 * Test 1 = 864401
	 * Test 2 = 674997
	 * Test 3 = 786780
	 * Prom   = 775392 nanoseconds => 0.000775392 seconds 
	 *
	 */
	@Test
	public void test03ShouldVerifyIfIsPrimeNumberUsingCustom() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		boolean isPrime = calculation.isPrimeNumberOptionCustom(primeNumber);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 3 Custom");
		System.out.println("Is Prime "+ primeNumber+": "+isPrime+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(isPrime);
	}
	
	@Test
	public void test04PrintLn1() {
		System.out.println("");
		System.out.println("***************** Count prime numbers  *****************");
		System.out.println("");
	}
	
	/*
	 * Test 1 = 63669442300
	 * Test 2 = 63385793103
	 * Test 3 = 62292016425
	 * Test 4 = 77210125427
	 * Test 5 = 63933863001
	 * Prom   = 66098248051 nanoseconds => 66.098248051 seconds 
	 *
	 */
	@Test
	public void test05ShouldGetQuantityOfPrimeNumbersUsingFor() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		List<Integer> numbers = calculation.getQuantityOfPrimesForOption(numberToEvaluate);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 1 FOR");
		System.out.println("Count prime numbers Between 1 to 1000000: "+numbers.size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(78498 == numbers.size());
	}
	
	/*
	 * This solution must enable the Xss10m in the JVM to avoid the StackOverflow
	 * 
	 * Test 1 = 104747305330
	 * Test 2 = 106483809087
	 * Test 3 = 105917608028
	 * Test 4 = 117177406923
	 * Test 5 = 113375140341
	 * Prom   = 109540253942 nanoseconds => 109.540253942 seconds
	 *
	 */
	@Test
	public void test06ShouldGetQuantityOfPrimesNumbersUsingRecursive() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		List<Integer> numbers = calculation.getQuantityOfPrimesForRecursive(numberToEvaluate);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 2 Recursive");
		System.out.println("Count prime numbers Between 1 to 1000000: "+numbers.size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(78498 == numbers.size());
	}
	
	/*
	 * Test 1 = 500432459
	 * Test 2 = 475120853
	 * Test 3 = 475928746
	 * Prom   = 483827352 nanoseconds => 0.483827352 seconds
	 *
	 */
	@Test
	public void test07ShouldGetQuantityOfPrimesCustomOption() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		HashMap numbers = calculation.getQuantityOfPrimesCustomOption(numberToEvaluate);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 3 Custom");
		System.out.println("Count prime numbers Between 1 to 1000000: "+numbers.size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(78498 == numbers.size());
	}
	
	@Test
	public void test08PrintLn2() {
		System.out.println("");
		System.out.println("************ Count circular prime numbers  *************");
		System.out.println("");
	}
	
	/*
	 * Test 1 = 71375032369
	 * Test 2 = 72167685094
	 * Test 3 = 70917268477
	 * Prom   = 71486661980 nanoseconds => 71.48666198 seconds 
	 *
	 */
	@Test
	public void test09ShouldGetQuantityOfCircularPrimesNumbersUsingFor() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		List<Integer> numbers = calculation.getQuantityOfPrimesForOption(numberToEvaluate);
		List<Integer> circularPrimeNumbers = calculation.evaluateCircularPrimeNumbers(numbers);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 1 FOR");
		System.out.println("Circular prime numbers: "+circularPrimeNumbers);
		System.out.println("Count circular prime numbers Between 1 to 1000000: "+circularPrimeNumbers.size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(78498 == numbers.size());
	}
	
	/*
	 * This solution must enable the Xss10m in the JVM to avoid the StackOverflow
	 * 
	 * Test 1 = 117361302618
	 * Test 2 = 116909162372
	 * Test 3 = 119533258384
	 * Prom   = 117934574458 nanoseconds => 117.934574458 seconds
	 *
	 */
	@Test
	public void test10ShouldGetQuantityOfCircularPrimesNumbersUsingRecursive() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		List<Integer> numbers = calculation.getQuantityOfPrimesForRecursive(numberToEvaluate);
		List<Integer> circularPrimeNumbers = calculation.evaluateCircularPrimeNumbers(numbers);
		long afterTime = System.nanoTime();
		System.out.println("Use Option 2 Recursive");
		System.out.println("Circular prime numbers: "+circularPrimeNumbers);
		System.out.println("Count circular prime numbers Between 1 to 1000000: "+circularPrimeNumbers.size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");
		
		assertTrue(78498 == numbers.size());
	}
	
	/*
	 * This is the best option to evaluate circular prime numbers
	 * 
	 * Test 1 = 630982238
	 * Test 2 = 641587946
	 * Test 3 = 668850330
	 * Prom   = 647140171 nanoseconds => 0.647140171 seconds
	 *
	 */
	@Test
	public void test11ShouldGetQuantityOfCircularPrimesCustomOption() {
		PrimeCalculation calculation = new PrimeCalculation();
		long beforetime = System.nanoTime();
		HashMap numbers = calculation.evaluateCircularPrimeNumbers(numberToEvaluate);
		long afterTime = System.nanoTime();
	    SortedSet<String> keys = new TreeSet<String>(((HashMap) numbers.get(1)).keySet());
		System.out.println("Use Option 3 Custom");
		System.out.println("Circular prime numbers: "+keys);
		System.out.println("Count circular prime numbers Between 1 to 1000000: "+((HashMap) numbers.get(1)).size()+" Elapsed Time: "+(afterTime - beforetime) + " Nanoseconds");

		assertTrue(78498 == ((HashMap) numbers.get(0)).size());
	}
}
