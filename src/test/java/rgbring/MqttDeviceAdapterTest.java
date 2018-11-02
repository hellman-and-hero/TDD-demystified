package rgbring;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import rgbring.IMqttClientForTest.TopicAndMessage;

public class MqttDeviceAdapterTest {

	protected TopicAndMessage received;

	@Rule
	public Timeout timeout = Timeout.seconds(5);

	@Before
	public void setup() throws MqttSecurityException, MqttException {
		createReceiver4Test("someLed/rgb/");
	}

	@Test
	public void mqttDeviceAdapterShouldPublishToBroker()
			throws MqttSecurityException, MqttException, InterruptedException {
		// TODO do not depend on eclipse infrastructure
		MqttDeviceAdapter sut = new MqttDeviceAdapter(createMqttClient());
		sut.setLedColor(42, "#123456");
		waitForResponse();
		assertThat(received.getTopic(), is("someLed/rgb/42"));
		assertThat(received.getPayload(), is("#123456"));
	}

	private IMqttClient createMqttClient() throws MqttException, MqttSecurityException {
		IMqttClient client = new MqttClient("tcp://iot.eclipse.org", "someledclient");
		client.connect();
		return client;
	}

	private void waitForResponse() throws InterruptedException {
		do {
			TimeUnit.MILLISECONDS.sleep(100);
		} while (received == null);
	}

	private void createReceiver4Test(String topic) throws MqttException, MqttSecurityException {
		IMqttClient receivingClient = new MqttClient("tcp://iot.eclipse.org", "someOtherClient");
		receivingClient.connect();
		receivingClient.subscribe(topic + "#");
		receivingClient.setCallback(new MqttCallback() {

			public void messageArrived(String topic, MqttMessage message) throws Exception {
				received = new TopicAndMessage(topic, message.getPayload());

			}

			public void deliveryComplete(IMqttDeliveryToken token) {
				// ignore
			}

			public void connectionLost(Throwable cause) {
				// ignore
			}
		});
	}

}
