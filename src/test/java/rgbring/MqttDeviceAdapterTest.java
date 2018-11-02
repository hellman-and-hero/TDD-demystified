package rgbring;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.junit.Test;

import rgbring.IMqttClientForTest.TopicAndMessage;

public class MqttDeviceAdapterTest {

	@Test
	public void test() throws MqttException {
		// TODO do not depend on eclipse infrastructure
		IMqttClient client = new MqttClient("tcp://iot.eclipse.org", "someledclient");
		client.connect();
		createReceiver();
		MqttDeviceAdapter sut = new MqttDeviceAdapter(client);
		
		sut.setLedColor(42, "#123456");
		TopicAndMessage received = receivingCLientHasReceived();
		assertThat(received.getTopic(), "someLed/rgb/42");
		assertThat(received.getPayload(), "#123456");

	}

	private TopicAndMessage receivingCLientHasReceived() {
		return new TopicAndMessage("", "".getBytes());
	}

	private void createReceiver() throws MqttException, MqttSecurityException {
		IMqttClient receivingClient = new MqttClient("tcp://iot.eclipse.org", "someOtherClient");
		receivingClient.connect();
		receivingClient.subscribe("someLed/rgb/#");
	}

}
