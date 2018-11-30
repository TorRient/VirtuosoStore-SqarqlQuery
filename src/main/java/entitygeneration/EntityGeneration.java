package entitygeneration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EntityGeneration {
	private static final Random RANDOM = new Random();
	private static List<String> extractedLinkList = new ArrayList<String>();
	private static List<String> extractedDateList = new ArrayList<String>();
	
	public void setExtractedLinkList(int noLink) {
		String prefix = "http://www.randomlink.org/link";
		
		for(int i = 0; i < noLink; i++) {
			extractedLinkList.add(prefix + i);
		}
	}
	
	public List<String> getExtractedLinkList() {
		return extractedLinkList;
	}
	
	public void setExtractedDateList(int noDate, String startDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		for(int i = 0; i < noDate; i++) {
			extractedDateList.add(dateFormat.format(calendar.getTime()));
			calendar.add(Calendar.DATE, 1);
		}
	}
	
	public Random getRandom() {
		return RANDOM;
	}
	
	public String generateRandomExtractedLink() {
		return extractedLinkList.get(RANDOM.nextInt(extractedLinkList.size()));
	}
	
	public String generateRandomExtractedDate() {
		return extractedDateList.get(RANDOM.nextInt(extractedDateList.size()));
	}
}
