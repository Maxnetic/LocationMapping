import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
  colorMode(HSB, 360, 100 ,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList maltetpl;
  TrackpointList maxtpl;
  //Import
  maltetpl = mapper.importData("malte_spitz.csv");
  maxtpl = mapper.importData("max_mittel.json");
  
  //setzen des Arbeitsfilters
  Filter arbeitsfilter = new Filter();
  arbeitsfilter.setStarttime("10:00");
  arbeitsfilter.setEndtime("16:00");
  arbeitsfilter.setWeekday("Montag-Freitag");
  arbeitsfilter.setMinFrequency(500);
  
  //Anwenden des Arbeitsfilters
  maltetpl = arbeitsfilter.apply(maltetpl);
  maxtpl = arbeitsfilter.apply(maxtpl);
  
  for(Trackpoint tp: maltetpl){
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(20);
      marker.setColor("rot");
      marker.setStyle("Labeled");
      marker.setLabel("Möglicher Arbeitsplatz");
      mapper.addMarker(marker);
  }
  
  for ( Trackpoint tp : maxtpl) {
      StandardMarker marker = new StandardMarker(tp);
      marker.setStyle("Round");
      marker.setColor("gelb");
      marker.setStyle("Labeled");
      marker.setLabel("Möglicher Arbeitsplatz");
      marker.setSize(20);
      mapper.addMarker(marker);
  } 
  
}

void draw(){
  
}
