package rgbring;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MqttDeviceAdapter implements DeviceAdapter {

	public static final String OFF = "#000000";
	public static final String ON = "#ffffff";

	
	private final IMqttClient mqttClient;

	public MqttDeviceAdapter(IMqttClient mqttClient) {
		this.mqttClient = mqttClient;
	}

	/* (non-Javadoc)
	 * @see rgbring.DeviceAdapter#setLedState(java.lang.Object, int, boolean)
	 */
	public void setLedState(Object rgbLedRing, int ledNum, boolean ledState) {
		try {
			String payload = ledState ? ON : OFF;
			mqttClient.publish("someLed/rgb/" + ledNum, new MqttMessage(payload.getBytes()));
		} catch (MqttPersistenceException e) {
			throw new RuntimeException(e);
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

}
