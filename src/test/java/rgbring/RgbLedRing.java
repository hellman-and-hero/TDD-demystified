package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;

public class RgbLedRing {
	
	private final boolean[] leds;

	public RgbLedRing(int ledCount, IMqttClient mqttClient) {
		this.leds = new boolean[ledCount];
	}

	void setLevel(int level) {
		for (int i = 0; i < leds.length; i++) {
			leds[i] = level > 100 / leds.length * i;
		}
	}

	public boolean getLed(int num) {
		return leds[num];
	}

	public int size() {
		return leds.length;
	}
}