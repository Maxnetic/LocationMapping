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
  list1 = mapper.importData("../../data/personX.csv");
  TrackpointList list2;
  list2 = mapper.importData("../../data/personY.csv");
   
  // Initialisierung Ende

  // Filter erstellen, TrackpointList durch Filter erzeugen.  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglicheWohnorteX;
  
  // Wohnort von Malte ermitteln
  moeglicheWohnorteX = wohnortfilter.apply(list1);
  moeglicheWohnorteX = frequencyfilter.apply(moeglicheWohnorteX);
  
  DateTimeFilter jsonwohnortfilter = new DateTimeFilter();
  jsonwohnortfilter.setStartTime("02:00");
  jsonwohnortfilter.setEndTime("05:00");
  TrackpointList moeglicheWohnorteY;
  
  // Wohnort von Max ermiiteln
  moeglicheWohnorteY = jsonwohnortfilter.apply(list2);
  moeglicheWohnorteY = frequencyfilter.apply(moeglicheWohnorteY);
  
  // Trackpoints von Malte zum zu zeichnenden Mapper-Objekt hinzufügen.  
  for ( Trackpoint tp : moeglicheWohnorteX ) {
    MarkerLabeled wohnortX = new MarkerLabeled(tp);
    wohnortX.setLabel("  möglicher Wohnort von PersonX");
    wohnortX.setColor(200,0,200);
    wohnortX.setTransparency(50);
    mapper.addMarker(wohnortX);
  }
  
  // Trackpoints von Max zum zu zeichnenden Mapper-Objekt hinzufügen.
  for (Trackpoint tp : moeglicheWohnorteY){
    MarkerLabeled wohnortY = new MarkerLabeled(tp);
    wohnortY.setColor(0,200,200);
    wohnortY.setTransparency(50);
    wohnortY.setLabel("  möglicher Wohnort von PersonY");
    mapper.addMarker(wohnortY);
  }
    
    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}

