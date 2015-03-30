import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;

/**
 * In diesem Beispiel werden sowohl der Wohnort, als auch
 * mögliche Arbeitsplätze der getrackten Person angezeigt.
 */


void setup(){
  StaticMapper mapper = new StaticMapper(this);
  colorMode(HSB, 360, 100, 100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList all;
  all = mapper.importData("malte_spitz.csv");
  
  //Wohnortfilter
  DateTimeFilter wohnortfilter = new DateTimeFilter();
  wohnortfilter.setStartTime("02:00");
  wohnortfilter.setEndTime("05:00");
  LocationFilter frequencyfilter = new LocationFilter();
  frequencyfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(all);
  moeglichewohnorte = frequencyfilter.apply(moeglichewohnorte);
  
  for ( Trackpoint tp : moeglichewohnorte ) {
    MarkerLabeled wohnort = new MarkerLabeled(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setColor("rot");
    mapper.addMarker(wohnort);
  }
    
  //Arbeitsplatzfilter
  DateTimeFilter arbeitsfilter = new DateTimeFilter();
  arbeitsfilter.setStartTime("10:00");
  arbeitsfilter.setEndTime("16:00");
  arbeitsfilter.setWeekDays("montag-freitag");
  frequencyfilter.setMinFrequency(500);
  TrackpointList moeglicherArbeitsplatz;
  moeglicherArbeitsplatz = arbeitsfilter.apply(all);
  moeglicherArbeitsplatz = frequencyfilter.apply(moeglicherArbeitsplatz);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    MarkerLabeled arbeit = new MarkerLabeled(tp);
    arbeit.setLabel("möglicher Arbeitsplatz");
    arbeit.setColor("gelb");
    mapper.addMarker(arbeit);
  }
  
  //Nachtsfilter
  DateTimeFilter nachtsfilter = new DateTimeFilter();
  nachtsfilter.setStartTime("00:00");
  nachtsfilter.setEndTime("06:00");
  TrackpointList nachts;
  nachts = nachtsfilter.apply(all);
  nachts = frequencyfilter.apply(nachts);
  
  for ( Trackpoint tp : moeglicherArbeitsplatz ) {
    MarkerRectangle marker = new MarkerRectangle(tp);
    marker.setColor("blau");
    mapper.addMarker(marker);
  }


}

void draw(){
  
}
