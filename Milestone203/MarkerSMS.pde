import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class MarkerSMS extends MarkerX {
  
  /* Konstruktor
  * @param location [Location]: Ortsangabe des Markers
  * @return Gibt einen neuen MarkerSMS zurück
  */
  public MarkerSMS(Location location) {
    super(location);
  }
  
  /* Alternativ Konstruktor
  * @param trackpoint [Trackpoint]: Der Trackpoint, aus dem der Marker erstellt wird
  * @return Erstellt einen neuen MarkerSMS 
  */
  public MarkerSMS(Trackpoint trackpoint) {
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
    pg.ellipse(x, y, size, size);  // Form
    
    //Zeichnung des Symbols für die obere Ecke
    pg.stroke(0, 0, 0, 200);
    //Umschlag 
    pg.rect(x + size/2 , y - size ,  size, size / 2);
    
    //Setzt die beiden Linien
    pg.line(x+ size/2 , y - size , x + size , y - size * 0.75  );
    pg.line(x + size , y - size * 0.75 , x + 1.5* size , y - size  );
    pg.popStyle();
  }
  

}

