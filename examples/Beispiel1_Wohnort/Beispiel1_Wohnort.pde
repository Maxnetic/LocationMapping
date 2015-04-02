import locationmapping.*;
import de.fhpotsdam.unfolding.*;


/**
* In diesem Beispiel werden die Trackpoints nach einer bestimmten Häufigkeit (2000) und einem Zeitraum (02:00-05:00) gefiltert.
* Daraus ergibt sich der mögliche Wohnort der Person.
* Hierfür wurde die Filterklassen benutzt, die einene gefilterte TrackpointList erstellen können.
* Diese wird dann ausgeben und besteht bei den default-Konfigurationen in der Regel aus einem Punkt. (Wohnort)
*/

  

void setup() {
  
  // Initialisierung Anfang 
  DynamicMapper mapper = new DynamicMapper(this);
  mapper.init();
    
  TrackpointList all = mapper.importData("../../data/personX.csv");
  
  // Initialisierung Ende
    
  // Gefiltere TrackpointList erstellen.  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  LocationFilter frequencyfilter = new LocationFilter();
  
  wohnortfilter.setStartTime("02:00").setEndTime("05:00");
  
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  
  // Trackpoints zum zu zeichnenden Mapper-Objekt hinzufügen.  
  for ( Trackpoint tp : moeglichewohnorte ) {
    PointMarker wohnort = new PointMarker(tp);
    wohnort.setLabel("wohnort").setFontsize(20).setColor(200, 50, 60);
    mapper.addMarker(wohnort);
  }
    
    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}
