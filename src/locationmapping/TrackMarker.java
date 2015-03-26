package locationmapping;

import java.util.List;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.AbstractShapeMarker;
import de.fhpotsdam.unfolding.utils.MapPosition;


public class TrackMarker extends AbstractShapeMarker {
	Location start = null;
	Location end = null;
	
	
	public TrackMarker(Trackpoint tpstart, Trackpoint tpend) {
		this.start = tpstart.getLocation();
		this.end = tpend.getLocation();
}

	
	public void draw(PGraphics pg) {
		if ((start != null) && (end != null)) {
			pg.pushStyle();
			pg.strokeWeight(4);
			pg.line(start.x, start.y, end.x, end.y);
			pg.popStyle();
		}
	}


	@Override
	public void draw(PGraphics arg0, List<MapPosition> arg1) {
		// TODO Auto-generated method stub
		
	}
}