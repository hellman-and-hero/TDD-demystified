package rgbring;

import static org.junit.Assert.fail;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;

public class MqttDeviceAdapterTest {

	@Test
	public void test() throws MqttException {

		IMqttClient client = new MqttClient("tcp://iot.eclipse.org", "someledclient");
		client.connect();
		MqttDeviceAdapter sut = new MqttDeviceAdapter(client);

	}

}
