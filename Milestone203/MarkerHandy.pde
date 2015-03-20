import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class MarkerHandy extends MarkerX {
  
  /* Konstruktor
  * @param location [Location]: Ortsangabe des Markers
  * @return Gibt einen neuen Handy Marker zurück
  */
  public MarkerHandy(Location location) {
    super(location);
  }
  
  /* Alternativ Konstruktor
  * @param trackpoint [Trackpoint]: Der Trackpoint, aus dem der Marker erstellt wird
  * @return Erstellt einen neuen MarkerHandy
  */
  public MarkerHandy(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }
  
  /*
  * Zeichenmethode
  * @param pg [PGraphics]: Ein PGraphic Object, das zum zeichnen benötigt wird
  * @param x [float]: x Koordinate der location
  * @param y [float]: y Koordinate der Location
  */
  public void draw(PGraphics pg, float x, float y) {
    
    //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
    
    //Hier geschieht die eigentliche Zeichnung
    pg.pushStyle();
    pg.noStroke();
    pg.fill(rot, gelb, blau, 100);  // Farbe sowie sichtbarkeit 
    pg.ellipse(x, y, size, size);  // Zeichnet einen Kreis

    //Zeichnung des Symbols für die obere Ecke
    pg.stroke(0,0,0);
    pg.rect(x + size/2 , y - size ,  size * 0.4, size / 2);
    pg.rect(x + size * 0.74 , y - size * 1.24 ,  size/6, size / 4);
    pg.rect(x + size * 0.57, y - size * 0.93, size * 0.25, size * 0.2);
    pg.popStyle();
  }
  

}

