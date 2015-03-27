package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;

public class MarkerLabeled extends StandardMarker {

	public MarkerLabeled(Location location){
		super(location);
	}
	
	public MarkerLabeled(Trackpoint trackpoint) {
	    super(trackpoint.getLocation());
	}
		
	public void draw(PGraphics pg, float x, float y){
		if (!this.isHidden()){
			pg.pushStyle();
		    pg.stroke(hsb_h,hsb_s,hsb_b);  // kein Rand
		    pg.strokeWeight(2);
		    pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
		    pg.rect(x, y, size, size);  // Form: Rechteck
		    pg.text(label, x + size + 5 , y + size/2);
		    pg.textSize(textsize);
		    pg.popStyle();
		}
	}
}