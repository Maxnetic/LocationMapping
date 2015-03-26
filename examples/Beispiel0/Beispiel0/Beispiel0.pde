import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);

/**
* In dem Beispiel werden zwei Filterobjekt erzeugt, die zum einen Aufethaltsorte am Wochenende filtern
* und zum anderen Aufenthaltsorte Montag und Dienstag filtern
*
*/

void setup(){
 
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
    
  Filter wochenende = new Filter();
  Filter montagDienstag = new Filter();
  
  //Wochenendefilter wird gesetzt
  wochenende.setWeekday("Samstag,Sonntag");
  wochenende.setStarttime("20:00");
  wochenende.setEndtime("21:00");
  
  //Filter für Montag und Dienstag wird gesetzt
  montagDienstag.setWeekday("Montag,Dienstag");
  montagDienstag.setStarttime("08:00");
  montagDienstag.setEndtime("12:00");
  
  //Anwenden des Wochenendfilters
  TrackpointList wochenendetpl;
  wochenendetpl = wochenende.apply(all);
  
  //Anwenden des MontagDienstag Filters
  TrackpointList montagDienstagtpl;
  montagDienstagtpl = wochenende.apply(all);
    
  //Die Marker werden der Karte hinzugefügt  
  for ( Trackpoint tp : wochenendetpl) {
    StandardMarker marker = new StandardMarker(tp);
    marker.setSize(2);
    marker.setStyle("Rectangle");
    mapper.addMarker(marker);
  } 
  
    
  //Die Marker werden der Karte hinzugefügt 
  for ( Trackpoint tp : montagDienstagtpl) {
    StandardMarker marker = new StandardMarker(tp);
    mapper.addMarker(marker);
  } 
}
  
void draw(){

}  
  
  

