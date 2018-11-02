package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class RgbLedRingTest {

	private boolean[] leds = new boolean[2];
	
	@Test
	public void givenRingWith2LedsShouldEnlightNoLedWhenLevelIsZero() {
		setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelMoreThanZero() {
		setLevel(1);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelIsLessThan51() {
		setLevel(50);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneAndTwoWhenLevelMoreThanFifty() {
		setLevel(51);
		assertStates(true, true);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedAfterLevelDropsToZero() {
		setLevel(51);
		setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneWhenLevelIsLessThan26() {
		givenLeds(4);
		setLevel(25);
		assertStates(true, false, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoWhenLevelIsMoreThan25() {
		givenLeds(4);
		setLevel(26);
		assertStates(true, true, false, false);
	}
	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeWhenLevelIsMoreThan50() {
		givenLeds(4);
		setLevel(51);
		assertStates(true, true, true, false);
	}
	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeAndFourWhenLevelIsMoreThan75() {
		givenLeds(4);
		setLevel(76);
		assertStates(true, true, true, true);
	}

	private void assertStates(boolean... states) {
		for (int i = 0; i < leds.length; i++) {
			assertThat(leds[i], is(states[i]));
		}
	}


	private void givenLeds(int ledCount) {
		this.leds = new boolean[ledCount];
	}

	private void setLevel(int level) {
		if (leds.length==4) {
			boolean led1 = level > 0;
			leds[0] = led1;
			boolean led2 = level > 25;
			leds[1] = led2;
			boolean led3 = level > 50;
			leds[2] = led3;
			boolean led4 = level > 75;
			leds[3] = led4;
			return;
		}
		leds[0] = led1;
		leds[1] = led2;
	}

}
