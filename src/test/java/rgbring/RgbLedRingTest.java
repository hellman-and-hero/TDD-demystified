package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RgbLedRingTest {

	private boolean led1, led2, led3, led4;

	@Test
	public void shouldEnlightNoLedWhenLevelIsZero() {
		setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void shouldEnlightLedOneWHenLevelMoreThanZero() {
		setLevel(1);
		assertStates(true, false);
	}

	@Test
	public void shouldEnlightLedOneWHenLevelIsLessThan51() {
		setLevel(50);
		assertStates(true, false);
	}

	@Test
	public void shouldEnlightLedOneAndTwoWhenLevelMoreThanFifty() {
		setLevel(51);
		assertStates(true, true);
	}

	@Test
	public void shouldEnlightNoLedAfterLevelDropsToZero() {
		setLevel(51);
		setLevel(0);
		assertStates(false, false);
	}

	@Test
	// TODO this is a test for ring with 4 leds, so find matching name for test
	public void shouldEnlightLedOneWhenLevelIsLessThan26() {
		setLevel(25);
		assertStates(true, false, false, false);
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

	private void setLevel(int level) {
		led1 = level > 0;
		led2 = level > 50;
	}

}
