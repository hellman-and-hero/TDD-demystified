package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class RgbLedRing {
	
	private IMqttClient mqttClient;
	private int ledCount;

	public RgbLedRing(int ledCount, IMqttClient mqttClient) {
		this.ledCount = ledCount;
		this.mqttClient = mqttClient;
	}

	void setLevel(int level) {
		boolean[] leds = new boolean[ledCount];
		for (int i = 0; i < size(); i++) {
			leds[i] = level > 100 / leds.length * i;
			try {
				boolean tmp = leds[i];
				boolean led = tmp;
				String payload = led ? "#ffffff" : "#000000";
				mqttClient.publish("someLed/rgb/" + i, new MqttMessage(payload.getBytes()));
			} catch (MqttPersistenceException e) {
				throw new RuntimeException(e);
			} catch (MqttException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public int size() {
		return ledCount;
	}
}