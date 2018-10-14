package service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringAccumulatorTest {

	private StringAccumulator stringAccumulator = null;

	@Before
	public void setUp() throws Exception {

		stringAccumulator = new StringAccumulator();
	}

	@Test
	public final void testAddBlankString() throws Exception {
		String inputString = "";
		int result = stringAccumulator.add(inputString);
		assertEquals(0, result);
	}

	@Test
	public final void testAddOneNumber() throws Exception {
		String inputString = "1";
		int result = stringAccumulator.add(inputString);
		assertEquals(1, result);
	}

	@Test
	public final void testAddTwoNumbers() throws Exception {
		String inputString = "1,2";
		int result = stringAccumulator.add(inputString);
		assertEquals(3, result);
	}

	@Test
	public final void testAddThreeNumbers() throws Exception {
		String inputString = "1\n2,3";
		int result = stringAccumulator.add(inputString);
		assertEquals(6, result);
	}

	@Test
	public final void testAddUnknownNumbers() throws Exception {
		String inputString = "1\n2,3,4\n5\n6\n7,8\n9,10";
		int result = stringAccumulator.add(inputString);
		assertEquals(55, result);
	}

	@Test(expected = NegativeNumberNotAllowedExeption.class)
	public final void testAddNegativeNumbers() throws Exception {

		String inputString = "-1\n-2,-3";

		int result = stringAccumulator.add(inputString);

	}

	@Test
	public final void testAddLargerThanThousand() throws Exception {
		String inputString = "//***\n12***34***1001";
		int result = stringAccumulator.add(inputString);
		assertEquals(46, result);
	}
	@Test
	public final void testAddDifferentDelimiters() throws Exception {
		String inputString = "//***\n12***34***23";
		int result = stringAccumulator.add(inputString);
		assertEquals(69, result);
	}

	@Test
	public final void testAddMultipleDifferentDelimiters() throws Exception {
		String inputString = "//***|%%%\n12***34%%%23";
		int result = stringAccumulator.add(inputString);
		assertEquals(69, result);
	}

}
