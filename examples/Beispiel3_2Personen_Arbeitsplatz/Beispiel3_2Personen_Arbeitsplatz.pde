import locationmapping.*;
import de.fhpotsdam.unfolding.*;


/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
* Eine Filterung nach der Häufigkeit wäre hier ergänzend nötig um die Ergebnisse gut bewerten zu können.
*/

void setup(){
  StaticMapper mapper = new StaticMapper(this);
  mapper.init(1024, 768);
    
  // Import
  TrackpointList xtpl = mapper.importData("../../data/personX.csv");
  TrackpointList ytpl = mapper.importData("../../data/personY.csv");
  
  //setzen des Arbeitsfilters
  DateTimeFilter arbeitsfilter = new DateTimeFilter();
  arbeitsfilter.setStartTime("10:00").setEndTime("16:00");
  arbeitsfilter.setWeekDays("mo-fr");
  
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(200);
  
  DateTimeFilter arbeitsfilter2 = new DateTimeFilter();
  arbeitsfilter2.setStartTime("10:00").setEndTime("16:00");
  arbeitsfilter2.setWeekDays("mo-fr");
  
  //Anwenden des Arbeitsfilters
  xtpl = arbeitsfilter.apply(xtpl);
  xtpl = frequencyfilter.apply(xtpl);
  
  ytpl = arbeitsfilter2.apply(ytpl);
  ytpl = frequencyfilter.apply(ytpl);

  
  for(Trackpoint tp: xtpl){
      PointMarker marker = new PointMarker(tp);
      marker.setSize(10);
      marker.setColor(Const.YELLOW);
      mapper.addMarker(marker);
  }
  
  for ( Trackpoint tp : ytpl) {
      PointMarker marker = new PointMarker(tp);
      marker.setSize(10);
      marker.setColor(Const.GREEN);
      mapper.addMarker(marker);
  } 
  
}

void draw(){
  
}
