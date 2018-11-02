package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class RgbLedRing {
	
	private static final String OFF = "#000000";
	private static final String ON = "#ffffff";
	private IMqttClient mqttClient;
	private int ledCount;

	public RgbLedRing(int ledCount, IMqttClient mqttClient) {
		this.ledCount = ledCount;
		this.mqttClient = mqttClient;
	}

	void setLevel(int level) {
		for (int i = 0; i < size(); i++) {
			boolean ledState = level > 100 / size() * i;
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