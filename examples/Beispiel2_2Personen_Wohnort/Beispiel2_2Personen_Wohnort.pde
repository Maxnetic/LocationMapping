import locationmapping.*;
import de.fhpotsdam.unfolding.*;

/**
* In diesem Beispiel werden die Wohnorte von zwei Personen ausgegeben, die wie in Beispiel 1 durch Filter ermittelt werden.
* Hierfür werden zwei Datensätze eingelesen, die Trackpoints dieser nach der Häufigkeit (default=2000) und dem Zeitraum (02:00-05:00) gefiltert.
* Die Filterklassen werden zur Implemtierung verwandt.
*/

void setup() {
  // Initialiserung Anfang
  StaticMapper mapper = new StaticMapper(this);
  mapper.init(1024, 768);
    
  TrackpointList list1;
  list1 = mapper.importData("../../data/personX.csv");
  TrackpointList list2;
  list2 = mapper.importData("../../data/personY.csv");


  // Filter erstellen, TrackpointList durch Filter erzeugen.  
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00").setEndTime("05:00");
  
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  
  // Wohnort von PersonX ermitteln
  TrackpointList moeglicheWohnorteX;
  moeglicheWohnorteX = wohnortfilter.apply(list1);
  moeglicheWohnorteX = frequencyfilter.apply(moeglicheWohnorteX);
  
  DateTimeFilter jsonwohnortfilter = new DateTimeFilter();
  jsonwohnortfilter.setStartTime("02:00").setEndTime("05:00");
  
  // Wohnort von PersonY ermiiteln
  TrackpointList moeglicheWohnorteY;
  moeglicheWohnorteY = jsonwohnortfilter.apply(list2);
  moeglicheWohnorteY = frequencyfilter.apply(moeglicheWohnorteY);
  
  // Trackpoints von Malte zum zu zeichnenden Mapper-Objekt hinzufügen.  
  for ( Trackpoint tp : moeglicheWohnorteX ) {
    PointMarker wohnortX = new PointMarker(tp);
    wohnortX.setLabel("möglicher Wohnort von PersonX").setColor(Const.YELLOW);
    mapper.addMarker(wohnortX);
  }
  
  // Trackpoints von Max zum zu zeichnenden Mapper-Objekt hinzufügen.
  for (Trackpoint tp : moeglicheWohnorteY){
    PointMarker wohnortY = new PointMarker(tp);
    wohnortY.setLabel("möglicher Wohnort von PersonY").setColor(Const.GREEN);
    mapper.addMarker(wohnortY);
  }
    
    
      
}

// Mapper-Objekt zeichnen.
void draw() {
      
}
