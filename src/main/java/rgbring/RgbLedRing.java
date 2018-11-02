package rgbring;

public class RgbLedRing {

	private static final String OFF = "#000000";
	private static final String ON = "#ffffff";


	private static final int MAX_LEVEL = 100;
	
	private final int ledCount;
	private final DeviceAdapter deviceAdapter;

	public RgbLedRing(int ledCount, DeviceAdapter deviceAdapter) {
		this.ledCount = ledCount;
		this.deviceAdapter = deviceAdapter;
	}

	void setLevel(int level) {
		for (int i = 0; i < size(); i++) {
			boolean ledState = level > MAX_LEVEL / size() * i;
			deviceAdapter.setLedColor(i, ledState ? ON : OFF);
		}
	}

	public int size() {
		return ledCount;
	}
}