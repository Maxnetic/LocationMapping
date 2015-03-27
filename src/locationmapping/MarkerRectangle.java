package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class MarkerRectangle extends StandardMarker {

	public MarkerRectangle(Location location){
		super(location);
	}
	
	public MarkerRectangle(Trackpoint trackpoint) {
	    super(trackpoint.getLocation());
	  }
	
	public void draw(PGraphics pg, float x, float y){
		if (!this.isHidden()){
			pg.pushStyle();
			pg.stroke(hsb_h, hsb_s, hsb_b, transparency);  // kein Rand
			pg.strokeWeight(2);
			pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
			pg.rect(x, y, size, size);  // Form: Rechteck
			pg.popStyle();
	    return;
		}
	}
}