package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

public class RgbLedRingTest {

	private boolean led1, led2, led3, led4;
	private int ledCount = 2;

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
		setLevel(75);
		assertStates(true, true, true, true);
	}

	private void assertStates(boolean state1, boolean state2, boolean state3, boolean state4) {
		assertThat(led1, is(state1));
		assertThat(led2, is(state2));
		assertThat(led3, is(state3));
		assertThat(led4, is(state4));
	}

	private void assertStates(boolean state1, boolean state2) {
		assertThat(led1, is(state1));
		assertThat(led2, is(state2));
	}

	private void givenLeds(int ledCount) {
		this.ledCount = ledCount;
	}

	private void setLevel(int level) {
		if (ledCount==4) {
			led1 = level > 0;
			led2 = level > 25;
			led3 = level > 50;
			led4 = level > 75;
			return;
		}
		led1 = level > 0;
		led2 = level > 50;
	}

}
