package mm.aeon.com.common;

import com.google.myanmartools.TransliterateZ2U;
import com.google.myanmartools.ZawgyiDetector;

public class demo {
	private static final ZawgyiDetector detector = new ZawgyiDetector();
	private static final TransliterateZ2U converter = new TransliterateZ2U("Zawgyi to Unicode");

	public static void main(String[] args) {
		// Unicode string:
		String input1 = "အပြည်ပြည်ဆိုင်ရာ လူ့အခွင့်အရေး ကြေညာစာတမ်း";
		// Zawgyi string:
		String input2 = "အျပည္ျပည္ဆိုင္ရာ လူ႔အခြင့္အေရး ေၾကညာစာတမ္း";

		// Detect that the second string is Zawgyi:
		double score1 = detector.getZawgyiProbability(input1);
		double score2 = detector.getZawgyiProbability(input2);
		assert score1 < 0.001;
		assert score2 > 0.999;
		System.out.format("Unicode Score: %.6f%n", score1);
		System.out.format("Zawgyi Score: %.6f%n", score2);

		// Convert the second string to Unicode:
		String input2converted = converter.convert(input2);
		// assert input1.equals(input2converted);s
		System.out.format("Converted Text: %s%n", input2converted);
	}
}
