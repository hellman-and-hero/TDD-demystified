package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;

public class DeviceAdapter {

	private IMqttClient mqttClient;

	public DeviceAdapter(IMqttClient mqttClient) {
		this.mqttClient = mqttClient;
		// TODO Auto-generated constructor stub
	}

}
