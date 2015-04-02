import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import java.util.Iterator;

/**
* In diesem Beispiel werden alle vorhandenen Trackpoints zu schwarzen Linien verbunden.
* Daraus kann man die Routen der Person nachvollziehen.
* Hierfür wurde die von Unfolding gelieferte Klasse SimpleLinesMarker benutzt.
* 
* Allerdings ist insbesondere im städtischen Raum nur schwer nachzuvollziehen, welche Routen, die Person häufig frequentiert hat.
* Hier könnte ein Filter diese nach Häufigkeiten sortieren und so auch für den urbanen Raum ein klareres Bild liefern.
* Gut zu erkennen sind Flüge, die die Person zurücklegt.
*/

void setup() {
  Mapper mapper = new DynamicMapper(this);
  mapper.init(1024, 768);
  
  TrackpointList all = mapper.importData("../../data/personX.csv");
  
  // TrackpointList durchlaufen und zum zuzeichnenden Mapper-Objekt hinzufügen.
  Iterator<Trackpoint> iter = all.iterator();
  Trackpoint start = iter.next();
  while (iter.hasNext()){
    Trackpoint end = iter.next();
    
    LineMarker track = new LineMarker(start, end);
    mapper.addMarker(track);
    
    start = end;
  }
}    

void draw() {}
