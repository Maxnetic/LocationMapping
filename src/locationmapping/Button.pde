
class Button {
  float x, y, w, h;
  
  Button(float x, float y, float w, float h) {
    /**
     * Die X-Koordinate des Buttons
     */
    this.x = x;
    /**
     * Die Y-Koordinate des Buttons
     */
    this.y = y;
    /**
     * Die Breite des Buttons
     */
    this.w = w;
    /**
     * Die Höhe des Buttons
     */
    this.h = h;
  } 
  
  boolean mouseOver() {
    /**
     * mouseOver ist true, wenn die Maus darüber gehalten wird. Benötigt zum Erkennen von Klicks auf den Button
     */
    return (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h);
  }
  
  void draw() {
    strokeWeight(1);
    stroke(80);
    fill(mouseOver() ? 255 : 220);
    rect(x,y,w,h); 
    //ellipse(x,y,w,h); 
  }
  
}

class SliderButton extends Button {
    SliderButton(float x, float y, int w, int h){
     /**
     * Zeichne Skala
     */
        super(w-980, h-580, x, y);
    }
    
    void draw(){
       super.draw(); 
       /**
         * Zeichne den Schieberegler des Zooms
         */
       rect(20 + (150 * (map.getZoom() / 262144) ),height-581.0, 5.0, 5.0);
    }
}

class ZoomButton extends Button {
  
  boolean in = false;
  
  ZoomButton(float x, float y, float w, float h, boolean in) {
    super(x, y, w, h);
    this.in = in;
  }
  
  void draw() {
    super.draw();
    stroke(0);
    line(x+3,y+h/2,x+w-3,y+h/2);
    if (in) {
      line(x+w/2,y+3,x+w/2,y+h-3);
    }
  }
  
}



class PlayButton extends Button {
  PlayButton(int w, int h) {
    //zeichne Rechteck
    super(w/2-16 , h-56, 28, 28);
  }
  
  
  void draw() {
    super.draw();
    
    //zeichne einen Kreis über das Rechteck
    fill(mouseOver() ? 255 : 220);    
    stroke(150);
    ellipse(width/2-2, height-42, 40, 40);
    
    
    noStroke();
    fill(120);
    /**
     * Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
     */
    if(pause == false) {
       rect(width/2-9,height-49, 5, 15);
       rect(width/2+1, height-49, 5, 15);
    }
    else {
      triangle(width/2-5, height-48, width/2-5, height-34, width/2+5, height-42);
    }
  }
  

}

