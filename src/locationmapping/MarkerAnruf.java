package locationmapping;

import processing.core.PGraphics;
import de.fhpotsdam.unfolding.geo.Location;

public class MarkerAnruf extends StandardMarker {

	public MarkerAnruf(Location location){
		super(location);
	}
	
	public MarkerAnruf(Trackpoint trackpoint) {
	    super(trackpoint.getLocation());
	}
		
	public void draw(PGraphics pg, float x, float y){
		if (!this.isHidden()){
			pg.pushStyle();
		    pg.noStroke();
		    pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
		    pg.ellipse(x, y, size, size);  // Zeichnet einen Kreis

		    //Zeichnung des Symbols für die obere Ecke
		    pg.stroke(0,0,0);
		    pg.rect(x + size/2 , y - size ,  size * 0.4f, size / 2f);
		    pg.rect(x + size * 0.74f , y - size * 1.24f ,  size/6f, size/4f);
		    pg.rect(x + size * 0.57f, y - size * 0.93f, size * 0.25f, size * 0.2f);
		    pg.popStyle();
		}
	}
}
