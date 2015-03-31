import locationmapping.*;
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
  DynamicMapper mapper = new DynamicMapper(this);
  // Mac Fix
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
  
  // Initialisierung Ende
    
  // Gefiltere TrackpointList erstellen.  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  LocationFilter frequencyfilter = new LocationFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  
  // Trackpoints zum zu zeichnenden Mapper-Objekt hinzufügen.  
  for ( Trackpoint tp : moeglichewohnorte ) {
    MarkerLabeled wohnort = new MarkerLabeled(tp);
    wohnort.setLabel("wohnort");
    wohnort.setColor(200, 0, 200);
    wohnort.setTransparency(50);
    mapper.addMarker(wohnort);
  }
    
    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}

