// Amountbubble ist ein Objekt um die Häufigkeit des Passierens von Malte Spitz
// an einem bestimmten Punkt zu messen


class Amountbubble {
  
  Location location; // Position der Bubble
  float minDistance = 0.002; //Mindestabstand der zur Vergrößerung des Punkts führt
  float diameter; // Durchmesser
  int counter; // allgemeiner Zähler, wie oft schon passiert wurde
  int callCounter; // Zähler, wie oft telefoniert wurde
  int gprsCounter; // Zähler, wie oft Internet auf dem Handy benutzt wurde
  int smsCounter; // Zähler, wie oft SMS benutzt wurden

  public Amountbubble(Location punkt) {
    this.location = punkt;
    this.diameter = 10;
    this.counter = 1;
  }

  // Durchmesser der Bubble vergrößern und der allgemeine Zähler erhöht
  void increaseSize() {
    this.diameter = diameter+0.05;
    this.counter++;
  }
  
  // Telefonier-Zähler erhöhen
  void increaseCallCounter() {
    this.callCounter++;
  }

  // Internet-Zähler erhöhen
  void increaseGprsCounter() {
    this.gprsCounter++;
  }

  // SMS-Zähler erhöhen
  void increaseSmsCounter() {
    this.smsCounter++;
  }

  // Überprüft ob zwei Bubbles an der gleichen Stelle sind
  public boolean equalsOther(Location that) {
    float distance = (float)Math.sqrt(Math.pow((that.lat - this.location.lat), 2) + Math.pow((that.lon - this.location.lon), 2));
    return distance < minDistance;
  }

  void draw(InteractiveMap map, boolean highlight) {
    Point2f punkt = map.locationPoint(location);
    draw(punkt.x, punkt.y, highlight);
  }



  // ################################################################################ //
  // ################################################################################ //
  
  // Amountbubble wird gezeichnet
  // Hier können Änderungen vorgenommen werden, um das Aussehen der Bubble anzupassen 
  
  // ################################################################################ //
  // ################################################################################ //

  void draw(float x, float y, boolean highlight) {

    switch(bubbleMode) {
      case 1: 
        // Bubble in hellem rot, mit Größenänderung
        noStroke();
        fill(176,37,68, 100);
        ellipse(x,y,diameter,diameter);
        break;

      case 2:
        // Bubble ändert die Farbe von Gelb nach Rot, mit Größenänderung
        //von Gelb dann Rot: von (255,255,0) nach (255,0,0)
        noStroke();
        int gelb = 255;
        fill(255, max(gelb-counter, 0), 0, 90);
        ellipse(x, y, diameter, diameter);
        break;

      case 3:
         // Bubble mit Zähler in der Mitte und Highlight-Funktion um die aktuelle Bubble zu markieren, mit Größenänderung
        if (!highlight) {
         fill(0, 0, 0, 60);
        } else {
         fill(255, 0, 0, 90);
        }
        ellipse(x, y, diameter, diameter);
         
        fill(0, 100);
        textSize(round(2 * this.diameter));
        textAlign(CENTER);
        String counterText = str(this.counter);
        text(counterText, x, y + 4);   
        break;

      case 4:
        // Bubble als Kuchendiagramm aufgeschlüsselt nach den Services, mit Größenänderung
        strokeWeight(1);
        stroke(150);
        float diameter = 2*this.diameter;
        float z = max(this.callCounter + this.smsCounter + this.gprsCounter, 1);
        int[] angs = {round((this.callCounter/z)*360), round((this.smsCounter/z)*360), round((this.gprsCounter/z)*360)};
       
        // Farben der unterschiedlichen Services
        color[] pieColors = {color(199,208,217), color(242,215,215), color(215,242,226)};
       
        float lastAng = 0;
       
        for (int i = 0; i < angs.length; i++) {
         fill(pieColors[i]);
         arc(x, y, diameter, diameter, lastAng, lastAng+radians(angs[i]));
         lastAng += radians(angs[i]);
        }
        break;
      case 5:
        // Bubble ändert die Farbe von Gelb nach Rot, ohne Größenänderung
        //von Gelb dann Rot: von (255,255,0) nach (255,0,0)
        noStroke();
        gelb = 255;
        fill(255, max(gelb-counter, 0), 0, 90);
        ellipse(x, y, 50, 50);
        break;
        
      default:
        // Bubble mit einem x in der Mitte, ohne Größenänderung    
        stroke(255,0,0, 40);
        int l = 2;
        line(x-l, y-l, x+l, y+l);
        line(x-l, y+l, x+l, y-l);
    }
  }
}


class LocationBubble extends Amountbubble {
  
  String picture;
  
  LocationBubble(Location location, String picture) {
    super(location);
    this.picture = picture;
    this.minDistance = 0.007;
  }
  
  void draw(InteractiveMap map, boolean highlight) {
    super.draw(map, highlight);
    //(punkt.x, punkt.y);
    
    PShape s;
    s = loadShape(picture);
    Point2f punkt = map.locationPoint(location);
    int radius = int(diameter/2);
    shape(s, punkt.x - radius + (diameter * 0.15), punkt.y - radius + (diameter * 0.15), diameter * 0.7, diameter * 0.7);
  }
  
}


