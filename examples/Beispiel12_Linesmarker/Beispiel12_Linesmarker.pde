import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;

/**
* In diesem Beispiel werden alle vorhandenen Trackpoints zu schwarzen Linien verbunden.
* Daraus kann man die Routen der Person nachvollziehen.
* Hierfür wurde die von Unfolding gelieferte Klasse SimpleLinesMarker benutzt.
* 
* Allerdings ist insbesondere im städtischen Raum nur schwer nachzuvollziehen, welche Routen, die Person häufig frequentiert hat.
* Hier könnte ein Filter diese nach Häufigkeiten sortieren und so auch für den urbanen Raum ein klareres Bild liefern.
* Gut zu erkennen sind Flüge, die die Person zurücklegt.
*/

 // Initialisierung Anfang
Trackpoint start = null;  
int i = 0;


void setup() {
  StaticMapper mapper = new StaticMapper(this); //DynamicMapper funktioniert nicht
  // Mac-Fix
  hint(ENABLE_RETINA_PIXELS);
  
  mapper.init();
  
  TrackpointList all;
  all = mapper.importData("../../data/personX.csv");
  
  Iterator<Trackpoint> iter;
  iter =   all.iterator();
  
  // Initialisierung Ende
  
  // TrackpointList durchlaufen und zum zuzeichnenden Mapper-Objekt hinzufügen.
  while (iter.hasNext()){
    
    Trackpoint end = iter.next();
    if ((start != null) && (end != null)){
      SimpleLinesMarker track = new SimpleLinesMarker(start.getLocation(), end.getLocation());
      track.setColor(0);
      mapper.addMarker(track);
    }
    start = end;
    
  }
}    
  


// Mapper-Objekt wird gezeichnet
void draw() {
  
}

// Anregungen:
// Screenshot-Funktion?
// GPX Import?
// Extend SimpleLinesMarker to get more functions

