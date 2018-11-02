package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import rgbring.IMqttClientForTest.TopicAndMessage;

public class RgbLedRingTest {

	private static final String OFF = "#000000";
	private static final String ON = "#ffffff";


	private RgbLedRing ring;
	private IMqttClientForTest mqttClient = new IMqttClientForTest();

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedWhenLevelIsZero() {
		givenLeds(2);
		ring.setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelMoreThanZero() {
		givenLeds(2);
		ring.setLevel(1);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelIsLessThan51() {
		givenLeds(2);
		ring.setLevel(50);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneAndTwoWhenLevelMoreThanFifty() {
		givenLeds(2);
		ring.setLevel(51);
		assertStates(true, true);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedAfterLevelDropsToZero() {
		givenLeds(2);
		ring.setLevel(51);
		mqttClient.clearMessages();

		ring.setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneWhenLevelIsLessThan26() {
		givenLeds(4);
		ring.setLevel(25);
		assertStates(true, false, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoWhenLevelIsMoreThan25() {
		givenLeds(4);
		ring.setLevel(26);
		assertStates(true, true, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeWhenLevelIsMoreThan50() {
		givenLeds(4);
		ring.setLevel(51);
		assertStates(true, true, true, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeAndFourWhenLevelIsMoreThan75() {
		givenLeds(4);
		ring.setLevel(76);
		assertStates(true, true, true, true);
	}

	private void assertStates(boolean... states) {
		for (int i = 0; i < ring.size(); i++) {
			TopicAndMessage topicAndMessage = mqttClient.getTopicAndMessages().get(i);
			assertThat(topicAndMessage.getTopic(), is("someLed/rgb/" + i));
			String expected = states[i] ? ON : OFF;
			assertThat(topicAndMessage.getPayload(), is(expected));
		}
	}

	private void givenLeds(int ledCount) {
		this.ring = new RgbLedRing(ledCount, new MqttDeviceAdapter(mqttClient));
	}

}
