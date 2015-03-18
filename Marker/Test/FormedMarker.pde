import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class FormedMarker extends UpdateableMarker {
  
  
  //Konstruktor
  public FormedMarker(Location location) {
    super(location);
  }
  
  /*
  //sobald Trackpoints verfügbar sind:  
  public FormedMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
    this.setColor( color(100,100,100) );
  }*/
  
  //Zeichenmethode
  public void draw(PGraphics pg, float x, float y) {
    
    //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
      
    //Hier wird gezeichnet
    pg.pushStyle();
    pg.stroke(color(rot, gelb, blau));  // kein Rand
    pg.strokeWeight(4);
    pg.fill(color(rot, gelb, blau, 100));  // Farbe sowie sichtbarkeit 
    pg.rect(x, y, size, size);  // Form: Rechteck
    pg.popStyle();
  }
  
}

