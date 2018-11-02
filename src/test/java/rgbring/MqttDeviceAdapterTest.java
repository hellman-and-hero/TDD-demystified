package rgbring;

import static org.junit.Assert.fail;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

public class MqttDeviceAdapterTest {

	@Test
	public void test() throws MqttException {
		// TODO do not depend on eclipse infrastructure
		IMqttClient client = new MqttClient("tcp://iot.eclipse.org", "someledclient");
		client.connect();
		IMqttClient receivingClient = new MqttClient("tcp://iot.eclipse.org", "someOtherClient");
		receivingClient.connect();
		receivingClient.subscribe("someLed/rgb/#");
		MqttDeviceAdapter sut = new MqttDeviceAdapter(client);

	}

}
