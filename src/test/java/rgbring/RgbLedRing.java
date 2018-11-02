package rgbring;

public class RgbLedRing {
	
	private final boolean[] leds;

	public RgbLedRing() {
	}

	public RgbLedRing(int ledCount) {
		this.leds = new boolean[ledCount];
	}

	public boolean[] getLeds() {
		return leds;
	}
}