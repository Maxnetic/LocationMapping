import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class ColoredMarker extends UpdateableMarker {
  
  //Konstruktor
  public ColoredMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public ColoredMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }*/
  
  
  public void draw(PGraphics pg, float x, float y) {
    
    //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
    
    //Hier geschieht die eigentliche Zeichnung
    pg.pushStyle();
    pg.noStroke();  // kein Rand
    pg.fill(rot, gelb, blau, 200);  // Farbe sowie sichtbarkeit 
    pg.ellipse(x, y, size, size);  // Form
    pg.popStyle();
  }
  

}

