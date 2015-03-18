import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class FormedMarker extends UpdateableMarker {
  
  public FormedMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public FormedMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
    this.setColor( color(100,100,100) );
  }*/
  
  
  public void draw(PGraphics pg, float x, float y) {
    pg.pushStyle();
    pg.stroke(color(200,10,10));  // kein Rand
    pg.strokeWeight(4);
    pg.fill(255, 0, 0, 100);  // Farbe sowie sichtbarkeit 
    pg.rect(x, y, size, size);  // Form 
    pg.popStyle();
  }
  
}

