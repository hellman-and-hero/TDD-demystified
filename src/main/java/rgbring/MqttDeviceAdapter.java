package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MqttDeviceAdapter implements DeviceAdapter {

	private final IMqttClient mqttClient;

	public MqttDeviceAdapter(IMqttClient mqttClient) throws Exception {
		// TODO who call disconnect?
		mqttClient.connect();
		this.mqttClient = mqttClient;
	}

	public void setLedColor(int ledNum, String colorCode) {
		try {
			mqttClient.publish("someLed/rgb/" + ledNum, new MqttMessage(colorCode.getBytes()));
		} catch (MqttPersistenceException e) {
			throw new RuntimeException(e);
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

}
