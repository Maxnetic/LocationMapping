import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class ColoredMarker extends UpdateableMarker {
  
  public ColoredMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public ColoredMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
    this.setColor( color(100,100,100) );
  }*/
  
  
  public void draw(PGraphics pg, float x, float y) {
    pg.pushStyle();
    pg.noStroke();  // kein Rand
    pg.fill(0, 250, 0, 200);  // Farbe sowie sichtbarkeit 
    pg.ellipse(x, y, size, size);  // Form
    pg.popStyle();
  }
  

}

