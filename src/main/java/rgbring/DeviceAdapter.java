package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;

public class DeviceAdapter {

	private final IMqttClient mqttClient;

	public DeviceAdapter(IMqttClient mqttClient) {
		this.mqttClient = mqttClient;
	}

}
