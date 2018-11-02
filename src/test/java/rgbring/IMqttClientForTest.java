package rgbring;

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

public class IMqttClientForTest implements IMqttClient {

	private final List<TopicAndMessage> topicAndMessages = new ArrayList<TopicAndMessage>();

	public List<TopicAndMessage> getTopicAndMessages() {
		return topicAndMessages;
	}

	public void clearMessages() {
		topicAndMessages.clear();
	}

	public static class TopicAndMessage {

		private final String topic;
		private final String payload;

		public TopicAndMessage(String topic, byte[] payload) {
			this.topic = topic;
			this.payload = new String(payload);
		}

		public String getPayload() {
			return payload;
		}

		public String getTopic() {
			return topic;
		}

	}

	public void unsubscribe(String[] topicFilters) throws MqttException {
		// TODO Auto-generated method stub

	}

	public void unsubscribe(String topicFilter) throws MqttException {
		// TODO Auto-generated method stub

	}

	public IMqttToken subscribeWithResponse(String[] topicFilters, int[] qos, IMqttMessageListener[] messageListeners)
			throws MqttException {
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
		topicAndMessages.add(new TopicAndMessage(topic, message.getPayload()));
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