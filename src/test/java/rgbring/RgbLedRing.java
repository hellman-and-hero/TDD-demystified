package rgbring;

public class RgbLedRing {
	
	private boolean[] leds;

	public RgbLedRing() {
	}

	public RgbLedRing(int ledCount) {
		setLeds(new boolean[ledCount]);
	}

	public boolean[] getLeds() {
		return leds;
	}

	public void setLeds(boolean[] leds) {
		this.leds = leds;
	}
}