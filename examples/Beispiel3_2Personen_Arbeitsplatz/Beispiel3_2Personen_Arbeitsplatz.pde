import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
* Eine Filterung nach der Häufigkeit wäre hier ergänzend nötig um die Ergebnisse gut bewerten zu können.
*/

void setup(){
  StaticMapper mapper = new StaticMapper(this);
  colorMode(HSB, 360,100,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList xtpl;
  TrackpointList ytpl;
  //Import
  xtpl = mapper.importData("../../data/personX.csv");
  ytpl = mapper.importData("../../data/personY.csv");
  
  //setzen des Arbeitsfilters
  DateTimeFilter arbeitsfilter = new DateTimeFilter();
  arbeitsfilter.setStartTime("10:00");
  arbeitsfilter.setEndTime("16:00");
  arbeitsfilter.setWeekDays("montag-freitag");
  
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(200);
  
  DateTimeFilter arbeitsfilter2 = new DateTimeFilter();
  arbeitsfilter2.setStartTime("10:00");
  arbeitsfilter2.setEndTime("16:00");
  arbeitsfilter2.setWeekDays("montag-freitag");
  
  //Anwenden des Arbeitsfilters
  xtpl = arbeitsfilter.apply(xtpl);
  ytpl = arbeitsfilter2.apply(ytpl);
  
  for(Trackpoint tp: xtpl){
      MarkerRectangle marker = new MarkerRectangle(tp);
      marker.setSize(10);
      marker.setColor("rot");
      mapper.addMarker(marker);
  }
  
  for ( Trackpoint tp : ytpl) {
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(10);
      marker.setColor("gelb");
      mapper.addMarker(marker);
  } 
  
}

void draw(){
  
}
