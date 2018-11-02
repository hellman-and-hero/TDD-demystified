package rgbring;

public interface DeviceAdapter {

	void setLedState(Object rgbLedRing, int ledNum, boolean ledState);

	void setLedColor(int ledNum, String colorCode);

}