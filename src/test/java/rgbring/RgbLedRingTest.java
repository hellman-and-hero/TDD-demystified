package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RgbLedRingTest {

	private RgbLedRing ring;

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedWhenLevelIsZero() {
		givenLeds(2);
		ring.setLevel(this, 0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelMoreThanZero() {
		givenLeds(2);
		ring.setLevel(this, 1);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelIsLessThan51() {
		givenLeds(2);
		ring.setLevel(this, 50);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneAndTwoWhenLevelMoreThanFifty() {
		givenLeds(2);
		ring.setLevel(this, 51);
		assertStates(true, true);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedAfterLevelDropsToZero() {
		givenLeds(2);
		ring.setLevel(this, 51);
		ring.setLevel(this, 0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneWhenLevelIsLessThan26() {
		givenLeds(4);
		ring.setLevel(this, 25);
		assertStates(true, false, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoWhenLevelIsMoreThan25() {
		givenLeds(4);
		ring.setLevel(this, 26);
		assertStates(true, true, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeWhenLevelIsMoreThan50() {
		givenLeds(4);
		ring.setLevel(this, 51);
		assertStates(true, true, true, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeAndFourWhenLevelIsMoreThan75() {
		givenLeds(4);
		ring.setLevel(this, 76);
		assertStates(true, true, true, true);
	}

	private void assertStates(boolean... states) {
		for (int i = 0; i < ring.getLeds().length; i++) {
			assertThat(ring.getLeds()[i], is(states[i]));
		}
	}

	private void givenLeds(int ledCount) {
		this.ring = new RgbLedRing(ledCount);
	}

}
