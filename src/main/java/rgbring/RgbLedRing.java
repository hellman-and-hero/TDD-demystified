package rgbring;

public class RgbLedRing {

	private static final String RED = "#ff0000";
	private static final String YELLOW = "#ffff00";
	private static final int AMOUNT_OF_COLORS = 3;
	private static final String GREEN = "#00ff00";
	private static final String OFF = "#000000";
	private static final double MAX_LEVEL = 100;

	private final int ledCount;
	private final DeviceAdapter deviceAdapter;

	public RgbLedRing(int ledCount, DeviceAdapter deviceAdapter) {
		this.ledCount = ledCount;
		this.deviceAdapter = deviceAdapter;
	}

	public void setLevel(int level) {
		for (int i = 0; i < size(); i++) {
			int value = (int) (MAX_LEVEL / size() * i);
			boolean ledState = level > value;
			String color = ledState ? determineColor(i) : OFF;
			deviceAdapter.setLedColor(i, color);
		}
	}

	private String determineColor(int led) {
		if (led < ledCount / AMOUNT_OF_COLORS * 1) {
			return GREEN;
		} else if (led < ledCount / AMOUNT_OF_COLORS * 2) {
			return YELLOW;
		} else {
			return RED;
		}
	}

	public int size() {
		return ledCount;
	}
}