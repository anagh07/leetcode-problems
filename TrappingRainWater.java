
public class TrappingRainWater {
	/*
	 * Two pointer technique
	 */
	public int trapTwopointer(int[] height) {
		int water, pL;
		water = pL = 0;
		int pR = height.length - 1;
		int maxLeft = height[pL];
		int maxRight = height[pR];

		while (pL < pR) {
			if (maxLeft < maxRight) {
				pL++;
				maxLeft = (height[pL] > maxLeft) ? height[pL] : maxLeft;
				water += maxLeft - height[pL];
			} else {
				pR--;
				maxRight = (height[pR] > maxRight) ? height[pR] : maxRight;
				water += maxRight - height[pR];
			}
		}
		return water;
	}

	/*
	 * Brute force
	 */
	public int trapBrute(int[] height) {
		int water = 0;
		int sum = 0;
		int currMax = 0;
		int[] maxLeft = new int[height.length];
		int[] maxRight = new int[height.length];

		// Determine maxLeft & maxRight
		for (int i = 0; i < height.length; i++) {
			maxLeft[i] = currMax;
			if (height[i] > currMax)
				currMax = height[i];
		}
		currMax = 0;
		for (int i = height.length - 1; i >= 0; i--) {
			maxRight[i] = currMax;
			if (height[i] > currMax)
				currMax = height[i];
		}

		// Calculate water at each point
		for (int i = 0; i < height.length; i++) {
			water = min(maxLeft[i], maxRight[i]) - height[i];
			water = (water < 0) ? 0 : water;
			sum += water;
		}

		return sum;
	}

	public int min(int i, int j) {
		return (i > j) ? j : i;
	}
}
