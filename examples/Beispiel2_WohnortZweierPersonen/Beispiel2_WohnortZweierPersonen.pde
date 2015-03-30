import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/**
* In diesem Beispiel werden die Wohnorte von zwei Personen ausgegeben, die wie in Beispiel 1 durch Filter ermittelt werden.
* Hierfür werden zwei Datensätze eingelesen, die Trackpoints dieser nach der Häufigkeit (default=2000) und dem Zeitraum (02:00-05:00) gefiltert.
* Die Filterklassen werden zur Implemtierung verwandt.
*/


  
  
void setup() {
  // Initialiserung Anfang

  StaticMapper mapper = new StaticMapper(this);
  // Mac-Fix
  hint(ENABLE_RETINA_PIXELS);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList list1;
  list1 = mapper.importData("malte_spitz.csv");
  TrackpointList list2;
  list2 = mapper.importData("max_mittel.json");
   
  // Initialisierung Ende

  // Filter erstellen, TrackpointList durch Filter erzeugen.  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglicheWohnorteMalte;
  
  // Wohnort von Malte ermitteln
  moeglicheWohnorteMalte = wohnortfilter.apply(list1);
  moeglicheWohnorteMalte = frequencyfilter.apply(moeglicheWohnorteMalte);
  
  DateTimeFilter jsonwohnortfilter = new DateTimeFilter();
  jsonwohnortfilter.setStartTime("02:00");
  jsonwohnortfilter.setEndTime("05:00");
  TrackpointList moeglicheWohnorteMax;
  
  // Wohnort von Max ermiiteln
  moeglicheWohnorteMax = jsonwohnortfilter.apply(list2);
  moeglicheWohnorteMax = frequencyfilter.apply(moeglicheWohnorteMax);
  
  // Trackpoints von Malte zum zu zeichnenden Mapper-Objekt hinzufügen.  
  for ( Trackpoint tp : moeglicheWohnorteMalte ) {
    MarkerLabeled wohnortMalte = new MarkerLabeled(tp);
    wohnortMalte.setLabel("  möglicher Wohnort von Malte");
    wohnortMalte.setColor(200,0,200);
    wohnortMalte.setTransparency(50);
    mapper.addMarker(wohnortMalte);
  }
  
  // Trackpoints von Max zum zu zeichnenden Mapper-Objekt hinzufügen.
  for (Trackpoint tp : moeglicheWohnorteMax){
    MarkerLabeled wohnortMax = new MarkerLabeled(tp);
    wohnortMax.setColor(0,200,200);
    wohnortMax.setTransparency(50);
    wohnortMax.setLabel("  möglicher Wohnort von Max");
    mapper.addMarker(wohnortMax);
  }
    
    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}

