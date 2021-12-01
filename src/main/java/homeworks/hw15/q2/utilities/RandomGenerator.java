package homeworks.hw15.q2.utilities;

import java.util.Random;

public class RandomGenerator {
	public static int getRandomInt(int base, int max) {
		return base + new Random().nextInt(max - base + 1);
	}
}
