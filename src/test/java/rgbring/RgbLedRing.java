package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class RgbLedRing {
	
	private static final int MAX_LEVEL = 100;
	private static final String OFF = "#000000";
	private static final String ON = "#ffffff";
	private IMqttClient mqttClient;
	private DeviceAdapter deviceAdapter;
	private int ledCount;

	public RgbLedRing(int ledCount, IMqttClient mqttClient, DeviceAdapter deviceAdapter) {
		this.ledCount = ledCount;
		this.mqttClient = mqttClient;
		this.deviceAdapter = deviceAdapter;
	}

	void setLevel(int level) {
		for (int i = 0; i < size(); i++) {
			boolean ledState = level > MAX_LEVEL / size() * i;
			setLedState(i, ledState);
		}
	}

	private void setLedState(int ledNum, boolean ledState) {
		try {
			String payload = ledState ? ON : OFF;
			mqttClient.publish("someLed/rgb/" + ledNum, new MqttMessage(payload.getBytes()));
		} catch (MqttPersistenceException e) {
			throw new RuntimeException(e);
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

	public int size() {
		return ledCount;
	}
}