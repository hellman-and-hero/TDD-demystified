package rgbring;

public class RgbLedRing {

	private static final int AMOUNT_OF_COLORS = 3;
	private static final String GREEN = "#00ff00";
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

			if (ledCount == 6) {
				deviceAdapter.setLedColor(i, i < ledCount / AMOUNT_OF_COLORS ? GREEN : OFF);
			} else {
				if (ledState) {
					deviceAdapter.setLedColor(i, ON);
				} else {
					deviceAdapter.setLedColor(i, OFF);
				}
			}

		}
	}

	public int size() {
		return ledCount;
	}
}