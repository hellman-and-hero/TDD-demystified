package rgbring;

public class RgbLedRing {
	
	private final boolean[] leds;

	public RgbLedRing(int ledCount) {
		this.leds = new boolean[ledCount];
	}

	public boolean[] getLeds() {
		return leds;
	}

	void setLevel(RgbLedRingTest unused, int level) {
		for (int i = 0; i < getLeds().length; i++) {
			getLeds()[i] = level > 100 / getLeds().length * i;
		}
	}
}