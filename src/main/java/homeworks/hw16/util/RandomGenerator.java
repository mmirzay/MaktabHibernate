package homeworks.hw16.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
	public static int getRandomInt(int base, int max) {
		return base + new Random().nextInt(max - base + 1);
	}

	public static List<Integer> getRandomIntArray(int length, int base, int max) {
		List<Integer> randomList = new ArrayList<>(length);
		Random random = new Random();
		int counter = 0;
		while (counter < length) {
			int r = base + random.nextInt(max - base + 1);
			if (randomList.contains(r))
				continue;
			randomList.add(r);
			counter++;
		}
		return randomList;
	}
}
