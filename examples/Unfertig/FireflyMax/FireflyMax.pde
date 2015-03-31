import locationmapping.*;
import java.util.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/**
* In diesem Beispiel werden die Trackpoints nach einer bestimmten Häufigkeit (2000) und einem Zeitraum (02:00-05:00) gefiltert.
* Daraus ergibt sich der mögliche Wohnort der Person.
* Hierfür wurde die Filterklassen benutzt, die einene gefilterte TrackpointList erstellen können.
* Diese wird dann ausgeben und besteht bei den default-Konfigurationen in der Regel aus einem Punkt. (Wohnort)
*/

  

void setup() {
  
  // Initialisierung Anfang 
  DynamicMapper mapper = new DynamicMapper(this); //funktioniert nicht da irgendwo cast auf standard marker? sonst bis auf die Farbe alles schick
  // Mac Fix
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
  // setzt den ColorMode
  colorMode(HSB,360,100,100);
  //erstellt Trackpointlist  
  TrackpointList all;
  //in die Malte Spitz`s Daten eingelesen werden
  all = mapper.importData("malte_spitz.csv");
  // Erster Trackpoint wird festgelegt
  Trackpoint start = null;
  // Iterator wird erstellt
  Iterator<Trackpoint> iter;
  //
  iter =   all.iterator();
  
  // Initialisierung Ende
  
  // TrackpointList durchlaufen und zum zuzeichnenden Mapper-Objekt hinzufügen.
  while (iter.hasNext()){
    
    Trackpoint end = iter.next();
    if ((start != null) && (end != null)){
      FireflyLine track = new FireflyLine(start.getLocation(), end.getLocation());
      mapper.addMarker(track);
    }
    start = end;
    
  }    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}

