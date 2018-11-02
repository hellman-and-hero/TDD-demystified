package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import rgbring.IMqttClientForTest.TopicAndMessage;

public class MqttDeviceAdapterTest {

	protected TopicAndMessage received;
	
	@Rule
	public Timeout timeout = Timeout.seconds(5);


	@Test
	public void test() throws MqttException, InterruptedException {
		// TODO do not depend on eclipse infrastructure
		IMqttClient client = new MqttClient("tcp://iot.eclipse.org", "someledclient");
		client.connect();
		createReceiver();
		MqttDeviceAdapter sut = new MqttDeviceAdapter(client);

		sut.setLedColor(42, "#123456");

		do {
			// noop
		} while (received == null);
		assertThat(received.getTopic(), is("someLed/rgb/42"));
		assertThat(received.getPayload(), is("#123456"));

	}

	private void createReceiver() throws MqttException, MqttSecurityException {
		IMqttClient receivingClient = new MqttClient("tcp://iot.eclipse.org", "someOtherClient");
		receivingClient.connect();
		receivingClient.subscribe("someLed/rgb/#");
		MqttCallback callback = new MqttCallback() {

			public void messageArrived(String topic, MqttMessage message) throws Exception {
				received = new TopicAndMessage(topic, message.getPayload());

			}

			public void deliveryComplete(IMqttDeliveryToken token) {
				// TODO Auto-generated method stub

			}

			public void connectionLost(Throwable cause) {
				// TODO Auto-generated method stub

			}
		};
		receivingClient.setCallback(callback);
	}

}
