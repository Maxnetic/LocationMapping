import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


StaticMapper mapper = new StaticMapper(this);

void setup(){
 
  colorMode(HSB, 360, 100, 100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
  
  //Wohnortfilter
  Filter wohnortfilter = new Filter();
  wohnortfilter.setStarttime("02:00");
  wohnortfilter.setEndtime("05:00");
  wohnortfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  
  for ( Trackpoint tp : moeglichewohnorte ) {
    MarkerLabeled wohnort = new MarkerLabeled(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setColor("rot");
    mapper.addMarker(wohnort);
  }
    
  //Arbeitsplatzfilter
  Filter arbeitsfilter = new Filter();
  arbeitsfilter.setStarttime("10:00");
  arbeitsfilter.setEndtime("16:00");
  arbeitsfilter.setWeekday("Montag-Freitag");
  arbeitsfilter.setMinFrequency(500);
  TrackpointList moeglicherArbeitsplatz;
  moeglicherArbeitsplatz = arbeitsfilter.apply(all);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    MarkerLabeled arbeit = new MarkerLabeled(tp);
    arbeit.setLabel("möglicher Arbeitsplatz");
    arbeit.setColor("gelb");
    mapper.addMarker(arbeit);
  }
  
  //Nachtsfilter
  Filter nachtsfilter = new Filter();
  nachtsfilter.setStarttime("00:00");
  nachtsfilter.setEndtime("06:00");
  nachtsfilter.setMinFrequency(500);
  TrackpointList nachts;
  nachts = nachtsfilter.apply(all);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    MarkerRectangle marker = new MarkerRectangle(tp);
    marker.setColor("blau");
    mapper.addMarker(marker);
  }


}
