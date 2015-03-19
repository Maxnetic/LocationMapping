import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


public class ColoredMarker extends UpdateableMarker {

  /* Konstruktor
  * @param location [Location]: Ortsangabe des Markers
  * @return Gibt einen neuen farblichen Marker zurück
  */
  public ColoredMarker(Location location) {
    super(location);
  }
  public ColoredMarker(Trackpoint trackpoint) {
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
    pg.noStroke();  // kein Rand
    pg.fill(rot, gelb, blau, 200);  // Farbe sowie sichtbarkeit
    pg.ellipse(x, y, size, size);  // Form
    pg.popStyle();
  }


}

