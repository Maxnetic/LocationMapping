import locationmapping.*;
import de.fhpotsdam.unfolding.*;

LocationMap map = new LocationMap(this);
    
void setup() {
  size(600, 800);
  frame.setResizable(false);
  map.initalize();

//  // lade Daten von MalteSpitz
//  TrackpointList tpl;
//  DataImporter im = new DataImporter(this);
//  tpl = im.load("malte_spitz.csv");
//  
//  // Filter wird angewendet
//  Filter f = new Filter();
//  f.setMinFrequency(1);
//  TrackpointList filtered = f.apply(tpl);
  
} 
  
  
void draw() {
  //zeichne die Karte
  map.draw();
}
