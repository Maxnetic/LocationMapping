import locationmapping.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;


DynamicMapper mapper = new DynamicMapper(this);

/**
* In dem Beispiel werden zwei Datensätze nach Aufenthalt von 10 bis 16 Uhr und Monatg bis Freitag gefiltert,
* um den höchstwahrscheinlichen Arbeitsplatz zu bestimmen.
*
*/

void setup(){
 
   colorMode(HSB,360,100,100);
  //mapper.setResizable(false); //not possible in eclipse
  mapper.init();
    
  TrackpointList maltetpl;
  maltetpl = mapper.importData("malte_spitz.csv");
  
  Filter serviceFilterSMS = new Filter();
  arbeitsfilter.setService("SMS");
  TrackpointList serviceSMS;
  serviceSMS = serviceFilterSMS.apply(maltetpl);
  
  Filter serviceFilterTelefonie = new Filter();
  arbeitsfilter.setService("Telefonie");
  TrackpointList serviceTelefonie;
  serviceTelefonie = serviceFilterTelefonie.apply(maltetpl);
  
  Filter serviceFilterInternet = new Filter();
  arbeitsfilter.setService("Internet");
  TrackpointList serviceInternet;
  serviceInternet = serviceFilterInternet.apply(maltetpl);
  
  Filter wohnortfilter = new Filter();
  wohnortfilter.setStarttime("02:00");
  wohnortfilter.setEndtime("05:00");
  wohnortfilter.setMinFrequency(2000);
  TrackpointList moeglichewohnorte;
  moeglichewohnorte = wohnortfilter.apply(maltetpl);
  
  
  
  for ( Trackpoint tp : serviceSMS) {
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(10);
      marker.setStyle("SMS");
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : serviceTelefonie) {
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(10);
      marker.setStyle("Telefonie");
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : serviceInternet) {
      StandardMarker marker = new StandardMarker(tp);
      marker.setSize(10);
      marker.setStyle("Internet");
      mapper.addMarker(marker);
  } 
  
  for ( Trackpoint tp : moeglichewohnorte ) {
    StandardMarker wohnort = new StandardMarker(tp);
    wohnort.setLabel("möglicher Wohnort");
    wohnort.setStyle("Labeled");
    wohnort.setColor("rot");
    mapper.addMarker(wohnort);
  }

}
