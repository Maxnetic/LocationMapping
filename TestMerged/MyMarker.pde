import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.*;


/*
 * MarkerX Oberklasse für alle Marker
 * @param size Größe des Markers
 * @param rot, gelb, blau Farbe des Markers
 * @param label Text für die Beschriftung
 * @param style Auswahl des Designs
 * @param textsize Textgröße der Beschriftung
 */

public class MyMarker extends SimplePointMarker {
  //Initialisierung der Attribute und ihrer Defaulteinstellung
  int size = 30;
  int rot = 0;
  int gelb = 0;
  int blau = 0;
  String label = null;
  String style = "Default";
  int textsize = 12;
 
  /*
  * Konstruktor fuer UpdatableMarker Objekte
  * @param location [Location]: Ortsangabe des Markers
  * @return neues Objekt vom Typ Marker
  */
  public MyMarker(Location location) {
    super(location);
  }
  
  /*
   * Konstruktor der Trackpoint uebergeben bekommt
   * @param trackpoint [Trackpoint] : Trackpoint aus dem Marker gezeichnet werden soll
   * @return neues Objekt von Typ Marker
   */
  public MyMarker(Trackpoint trackpoint) {
    super(trackpoint.getLocation());
  }
  
  /*
  * updated die Größe des Markers
  * @param size [int]: die neue Größe des Markers
  */
  public void setSize(int size){
    this.size = size;
  }

  /*
  *Die Funktion updated die Farbe des Markers
  *@param rot [int]: Rotfarbanteil
  *@param gelb [int]: Gelbfarbanteil
  *@param blau [int]: Blaufarbanteil
  */
  public void setColor(int rot, int gelb, int blau){
    this.rot = rot;
    this.gelb = gelb;
    this.blau = blau;
  }
  
  /*
   * Label auslesen
   * @return String label
   */
  public String getLabel() {
    return this.label;
  }
  
  /*
   * Label festlegen
   * @param String label
   */  
  public void setLabel(String label) {
    this.label = label;
  }
  
  /*
   * Textgroesse festlegen
   * @param int size
   */
  public void setTextSize(int size){
    this.textsize = size;
  }
  
  /*
   * Textgroesse auslesen
   * @return int textsize
   */
  public int getTextSize(){
    return this.textsize;
  }

  /*
  * liefert den ausgewählten Style
  */
  public String getStyle() {
    return this.style;
  }
  
  /*
  * set-Methode für das Attribut Style
  *@param style [String]: Vorgegebene Styles: Default, Rectangle, Round, SMS, Labeled, Anruf, Internet
  */
  public void setStyle(String style) {
    this.style = style;
  }
  
  /*
  * Zeichenmethode, die je nach gewähltem Style andersaussehende Marker erzeugt
  */
  public void draw(PGraphics pg, float x, float y) {
    if (this.isHidden()) {
      return;
    }
    
    if (this.style == "Rectangle") {
      pg.pushStyle();
      pg.stroke(color(rot, gelb, blau));  // kein Rand
      pg.strokeWeight(2);
      pg.fill(color(rot, gelb, blau, 1));  // Farbe sowie sichtbarkeit 
      pg.rect(x, y, size, size);  // Form: Rechteck
      pg.popStyle();
      return;
    }
    if (this.style == "Round") {
      pg.pushStyle();
      pg.noStroke();  // kein Rand
      pg.fill(rot, gelb, blau, 150);  // Farbe sowie sichtbarkeit 
      pg.ellipse(x, y, size, size);  // Form
      pg.popStyle();
      return;
    }
    if ((this.style.contains("SMS"))) {
      pg.pushStyle();
      pg.noStroke();
      pg.fill(rot, gelb, blau, 100);  // Farbe sowie sichtbarkeit 
      pg.ellipse(x, y, size, size);  // Form
      pg.stroke(0, 0, 0, 200);  //Zeichnung des Symbols für die obere Ecke
      pg.rect(x + size/2 , y - size ,  size, size / 2);  //Umschlag
      
      //Setzt die beiden Linien
      pg.line(x+ size/2 , y - size , x + size , y - size * 0.75  );
      pg.line(x + size , y - size * 0.75 , x + 1.5* size , y - size  );
      pg.popStyle();
      return;
    }
    if (this.style == "Labeled") {
      pg.pushStyle();
      pg.stroke(color(rot, gelb, blau));  // kein Rand
      pg.strokeWeight(2);
      pg.fill(color(rot, gelb, blau, 1));  // Farbe sowie sichtbarkeit 
      pg.rect(x, y, size, size);  // Form: Rechteck
      text(label, x +15 , y +9);
      textSize(textsize);
      pg.popStyle();
      return;
    }
    if ((this.style.contains("Anruf")) || (this.style.contains("Telefonie"))) {
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
      return;
    }
    if (this.style.contains("Internet")) {
      pg.pushStyle();
      pg.noStroke();
      pg.fill(rot, gelb, blau, 100);  // Farbe sowie sichtbarkeit 
      pg.ellipse(x, y, size, size);  // Form
    
      //Zeichnung des Symbols für die obere Ecke
      textSize(size);
      stroke(5);
      pg.fill(0, 0, 0, 100); 
      text("@",x - size *0.45,y + size*0.35);
      pg.popStyle();
      return;
    }
    else {
      super.draw(pg,x,y);
    
    }
  }
}

