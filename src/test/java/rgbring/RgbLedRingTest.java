package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

import rgbring.IMqttClientForTest.TopicAndMessage;

public class RgbLedRingTest {

	private static final String OFF = "#000000";
	private static final String ON = "#ffffff";

	private RgbLedRing ring;
	private IMqttClientForTest mqttClient = new IMqttClientForTest();

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedWhenLevelIsZero() throws Exception {
		givenLeds(2);
		ring.setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelMoreThanZero() throws Exception {
		givenLeds(2);
		ring.setLevel(1);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneWHenLevelIsLessThan51() throws Exception {
		givenLeds(2);
		ring.setLevel(50);
		assertStates(true, false);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightLedOneAndTwoWhenLevelMoreThanFifty() throws Exception {
		givenLeds(2);
		ring.setLevel(51);
		assertStates(true, true);
	}

	@Test
	public void givenRingWith2LedsShouldEnlightNoLedAfterLevelDropsToZero() throws Exception {
		givenLeds(2);
		ring.setLevel(51);
		mqttClient.clearMessages();

		ring.setLevel(0);
		assertStates(false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneWhenLevelIsLessThan26() throws Exception {
		givenLeds(4);
		ring.setLevel(25);
		assertStates(true, false, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoWhenLevelIsMoreThan25() throws Exception {
		givenLeds(4);
		ring.setLevel(26);
		assertStates(true, true, false, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeWhenLevelIsMoreThan50() throws Exception {
		givenLeds(4);
		ring.setLevel(51);
		assertStates(true, true, true, false);
	}

	@Test
	public void givenRingWith4LedsShouldEnlightLedOneAndTwoAndThreeAndFourWhenLevelIsMoreThan75() throws Exception {
		givenLeds(4);
		ring.setLevel(76);
		assertStates(true, true, true, true);
	}

	@Test
	public void givenRingWith6ColoredLedsShouldEnlightFirstTwoLedsWhenLevelIs33() throws Exception {
		givenLeds(6);
		ring.setLevel(33);
		assertStates("#00ff00", "#00ff00", OFF, OFF, OFF, OFF);
	}

	@Test
	public void givenRingWith6ColoredLedsShouldEnlightAllLedsWhenLevelIs100() throws Exception {
		givenLeds(6);
		ring.setLevel(100);
		assertStates("#00ff00", "#00ff00", "#ffff00", "#ffff00", "#ff0000", "#ff0000");
	}

	@Test
	@Ignore // TODO talk to PO how to handle this (color schema?)
	public void givenRingWith4ColoredLedsShouldEnlightAllLedsWhenLevelIs100() throws Exception {
		givenLeds(4);
		ring.setLevel(100);
		assertStates("#00ff00",  "#ffff00", "#ffff00", "#ff0000");
	}

	private void assertStates(String... colors) {
		for (int i = 0; i < ring.size(); i++) {
			assertColor(i, colors[i]);
		}

	}

	private void assertStates(boolean... states) {
		for (int i = 0; i < ring.size(); i++) {
			TopicAndMessage topicAndMessage = mqttClient.getTopicAndMessages().get(i);
			assertThat(topicAndMessage.getTopic(), is("someLed/rgb/" + i));
			if (states[i]) {
				assertThat(topicAndMessage.getPayload(), is(not(OFF)));
			} else {
				assertThat(topicAndMessage.getPayload(), is(OFF));
			}
		}
	}

	private void assertColor(int i, String color) {
		TopicAndMessage topicAndMessage = mqttClient.getTopicAndMessages().get(i);
		assertThat(topicAndMessage.getTopic(), is("someLed/rgb/" + i));
		assertThat(topicAndMessage.getPayload(), is(color));
	}

	private void givenLeds(int ledCount) throws Exception {
		this.ring = new RgbLedRing(ledCount, new MqttDeviceAdapter(mqttClient));
	}

}
