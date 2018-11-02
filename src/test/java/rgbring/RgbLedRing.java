package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;

public class RgbLedRing {

	private static final int MAX_LEVEL = 100;
	@Deprecated
	public IMqttClient mqttClient;
	private DeviceAdapter deviceAdapter;
	private int ledCount;

	public RgbLedRing(int ledCount, DeviceAdapter deviceAdapter) {
		this.ledCount = ledCount;
		this.mqttClient = mqttClient;
		this.deviceAdapter = deviceAdapter;
	}

	void setLevel(int level) {
		for (int i = 0; i < size(); i++) {
			boolean ledState = level > MAX_LEVEL / size() * i;
			deviceAdapter.setLedState(this, i, ledState);
		}
	}

	public int size() {
		return ledCount;
	}
}