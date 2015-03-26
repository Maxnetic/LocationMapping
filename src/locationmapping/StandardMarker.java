package locationmapping;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;
import processing.core.PGraphics;


public class StandardMarker extends SimplePointMarker {
  //Initialisierung der Attribute und ihrer Defaulteinstellung
 /*
 * @param size Größe des Markers
 * @param label Text für die Beschriftung
 * @param style Auswahl des Designs
 * @param textsize Textgröße der Beschriftung
 * @param hsb_h Farbwert der HSB Farbe
 * @param hsb_s Sättigungswert der HSB Farbe
 * @param hsb_b Helligkeitswert der HSB Farbe
 * @param transparency Transparenz der Farbe des Markers
 */
  int size = 30;
  int hsb_h = 0;
  int hsb_s = 0;
  int hsb_b = 0;
  int transparency = 100;
  String label = null;
  String style = "Default";
  int textsize = 12;

  /*
  * Konstruktor fuer UpdatableMarker Objekte
  * @param location [Location]: Ortsangabe des Markers
  * @return neues Objekt vom Typ Marker
  */
  public StandardMarker(Location location) {
    super(location);
  }

  /*
   * Konstruktor der Trackpoint uebergeben bekommt
   * @param trackpoint [Trackpoint] : Trackpoint aus dem Marker gezeichnet werden soll
   * @return neues Objekt von Typ Marker
   */
  public StandardMarker(Trackpoint trackpoint) {
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
  * Die Funktion updated die Transparenz des Markers
  * @param trans [int]: Transparenzwert
  */
  public void setTransparency(int trans){
	transparency = trans;
  }
  
  /*
  *Die Funktion updated die Farbe des Markers
  *@param color [int]: Farbwert
  */
  public void setColor(int hsb_h, int hsb_s, int hsb_b){
    this.hsb_h =hsb_h;
	this.hsb_s = hsb_s;
	this.hsb_b = hsb_b;
  }
  
  /*
  * setColor Funktion mit konstanten Strings
  * @param colorstr [String]: Die Farbe des Strings
  */
  
  public void setColor(String colorstr){
    colorstr.toLowerCase();
	if (colorstr.equals("rot")){
		hsb_h = 0;
		hsb_s = 99;
		hsb_b = 99;	
	} else	if (colorstr.equals("blau")){
		hsb_h = 240;
		hsb_s = 99;
		hsb_b = 99;
	} else if (colorstr.equals("grün")){
		hsb_h = 100;
		hsb_s = 99;
		hsb_b = 99;
	} else if (colorstr.equals("gelb")){
		hsb_h = 60;
		hsb_s = 99;
		hsb_b = 99;
	} else if (colorstr.equals("grau")){
		hsb_h = 0;
		hsb_s = 1;
		hsb_b = 60;
	}else{
	System.out.println("Die Farbe ist nicht in der Liste! Farbe mit HSB Codierung möglich!");
	}
		
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
      pg.stroke(hsb_h, hsb_s, hsb_b, transparency);  // kein Rand
      pg.strokeWeight(2);
      pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
      pg.rect(x, y, size, size);  // Form: Rechteck
      pg.popStyle();
      return;
    }
    if (this.style == "Round") {
      pg.pushStyle();
      pg.noStroke();  // kein Rand
      pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
      pg.ellipse(x, y, size, size);  // Form
      pg.popStyle();
      return;
    }
    if ((this.style.contains("SMS"))) {
      pg.pushStyle();
      pg.noStroke();
      pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
      pg.ellipse(x, y, size, size);  // Form
      pg.stroke(0, 0, 0, 200);  //Zeichnung des Symbols für die obere Ecke
      pg.rect(x + size/2 , y - size ,  size, size / 2);  //Umschlag

      //Setzt die beiden Linien
      pg.line(x+ size/2 , y - size , x + size , y - size * 0.75f );
      pg.line(x + size , y - size * 0.75f , x + 1.5f * size , y - size  );
      pg.popStyle();
      return;
    }
    if (this.style == "Labeled") {
      pg.pushStyle();
      pg.stroke(hsb_h,hsb_s,hsb_b);  // kein Rand
      pg.strokeWeight(2);
      pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
      pg.rect(x, y, size, size);  // Form: Rechteck
      pg.text(label, x + size + 5 , y + size/2);
      pg.textSize(textsize);
      pg.popStyle();
      return;
    }
    if ((this.style.contains("Anruf")) || (this.style.contains("Telefonie"))) {
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
      return;
    }
    if (this.style.contains("Internet")) {
      pg.pushStyle();
      pg.noStroke();
      pg.fill(hsb_h, hsb_s, hsb_b, transparency);  // Farbe sowie sichtbarkeit
      pg.ellipse(x, y, size, size);  // Form

      //Zeichnung des Symbols für die obere Ecke
      pg.textSize(size);
      pg.stroke(5);
      pg.fill(0, 0, 0, 100);
      pg.text("@", x - size * 0.45f, y + size*0.35f);
      pg.popStyle();
      return;
    }
    else {
      super.draw(pg,x,y);

    }
  }
}

