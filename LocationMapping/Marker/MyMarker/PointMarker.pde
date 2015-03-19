

public class PointMarker extends MyMarker{
  
  
  public PointMarker(Trackpoint trackpoint){
    super(trackpoint);
  }
  
    public void draw(PGraphics pg, float x, float y) {    
  
  //überprüft ob der Marker sichtbar ist, wenn nicht wird er nicht gezeichnet
    if(this.isHidden())
      return;
    
    //Hier geschieht die eigentliche Zeichnung
    
      pg.pushStyle();
      pg.noStroke();  // kein Rand
      pg.fill(red, yellow, blue, 200);  // Farbe sowie sichtbarkeit 
      pg.ellipse(x, y, size, size);  // Form
      pg.popStyle();
    }
}
