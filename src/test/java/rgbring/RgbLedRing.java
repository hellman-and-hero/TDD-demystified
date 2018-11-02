package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class RgbLedRing {
	
	private final boolean[] leds;
	private IMqttClient mqttClient;

	public RgbLedRing(int ledCount, IMqttClient mqttClient) {
		this.mqttClient = mqttClient;
		this.leds = new boolean[ledCount];
	}

	void setLevel(int level) {
		for (int i = 0; i < leds.length; i++) {
			leds[i] = level > 100 / leds.length * i;
			try {
				mqttClient.publish("someLed/rgb/" + i, new MqttMessage("#ffffff".getBytes()));
			} catch (MqttPersistenceException e) {
				throw new RuntimeException(e);
			} catch (MqttException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public boolean getLed(int num) {
		return leds[num];
	}

	public int size() {
		return leds.length;
	}
}