import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;
import java.sql.Timestamp;

class TestTrackpoint {
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		Location location = new Location(53.13, 13.22);
		System.out.println(location);

		Trackpoint tp = new Trackpoint(timestamp, location);
		System.out.println(tp);

		tp.setLabel("Ein Test Label");
		System.out.println(tp);
	}
}