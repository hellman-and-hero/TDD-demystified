package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.junit.Test;

public class RgbLedRingTest {

	private static class TopicAndMessage {

		private String payload;

		public TopicAndMessage(byte[] bs) {
			this.payload = new String(bs);
		}

		public String getPayload() {
			return payload;
		}

	}

	private final class IMqttClientForTest implements IMqttClient {
		public void unsubscribe(String[] topicFilters) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void unsubscribe(String topicFilter) throws MqttException {
			// TODO Auto-generated method stub

		}

		public IMqttToken subscribeWithResponse(String[] topicFilters, int[] qos,
				IMqttMessageListener[] messageListeners) throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String topicFilter, int qos, IMqttMessageListener messageListener)
				throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String[] topicFilters, int[] qos) throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String[] topicFilters, IMqttMessageListener[] messageListeners)
				throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String topicFilter, int qos) throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String topicFilter, IMqttMessageListener messageListener)
				throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String[] topicFilters) throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttToken subscribeWithResponse(String topicFilter) throws MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public void subscribe(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners)
				throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String topicFilter, int qos, IMqttMessageListener messageListener) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String[] topicFilters, IMqttMessageListener[] messageListeners) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String topicFilter, IMqttMessageListener messageListener)
				throws MqttException, MqttSecurityException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String[] topicFilters, int[] qos) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String topicFilter, int qos) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String[] topicFilters) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void subscribe(String topicFilter) throws MqttException, MqttSecurityException {
			// TODO Auto-generated method stub

		}

		public void setManualAcks(boolean manualAcks) {
			// TODO Auto-generated method stub

		}

		public void setCallback(MqttCallback callback) {
			// TODO Auto-generated method stub

		}

		public void publish(String topic, byte[] payload, int qos, boolean retained)
				throws MqttException, MqttPersistenceException {
			// TODO Auto-generated method stub

		}

		public void publish(String topic, MqttMessage message) throws MqttException, MqttPersistenceException {
			messageToSend.add(new String(message.getPayload()));
			topicAndMessages.add(new TopicAndMessage(message.getPayload()));
		}

		public void messageArrivedComplete(int messageId, int qos) throws MqttException {
			// TODO Auto-generated method stub

		}

		public boolean isConnected() {
			// TODO Auto-generated method stub
			return false;
		}

		public MqttTopic getTopic(String topic) {
			// TODO Auto-generated method stub
			return null;
		}

		public String getServerURI() {
			// TODO Auto-generated method stub
			return null;
		}

		public IMqttDeliveryToken[] getPendingDeliveryTokens() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getClientId() {
			// TODO Auto-generated method stub
			return null;
		}

		public void disconnectForcibly(long quiesceTimeout, long disconnectTimeout) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void disconnectForcibly(long disconnectTimeout) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void disconnectForcibly() throws MqttException {
			// TODO Auto-generated method stub

		}

		public void disconnect(long quiesceTimeout) throws MqttException {
			// TODO Auto-generated method stub

		}

		public void disconnect() throws MqttException {
			// TODO Auto-generated method stub

		}

		public IMqttToken connectWithResult(MqttConnectOptions options) throws MqttSecurityException, MqttException {
			// TODO Auto-generated method stub
			return null;
		}

		public void connect(MqttConnectOptions options) throws MqttSecurityException, MqttException {
			// TODO Auto-generated method stub

		}

		public void connect() throws MqttSecurityException, MqttException {
			// TODO Auto-generated method stub

		}

		public void close() throws MqttException {
			// TODO Auto-generated method stub

		}
	}

	private RgbLedRing ring;
	private IMqttClient client;
	private final List<String> messageToSend = new ArrayList<String>();
	private final List<TopicAndMessage> topicAndMessages = new ArrayList<TopicAndMessage>();

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
			assertThat(ring.getLed(i), is(states[i]));

			assertThat(messageToSend.get(0), is("#ffffff"));
			TopicAndMessage topicAndMessage = topicAndMessages.get(0);
			assertThat(topicAndMessage.getPayload(), is("#ffffff"));

		}
	}

	private void givenLeds(int ledCount) {
		client = new IMqttClientForTest();
		this.ring = new RgbLedRing(ledCount, client);
	}

}
