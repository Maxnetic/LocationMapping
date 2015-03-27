/**
 * Milestone Freitag, 20.3.
 * 
 * Die CSV-Datei von Malte Spitz wird eingelesen.
 * Es können entweder alle Trackpoints angezeigt werden,
 * oder gefilterte TrackpointLists erstellt werden.
 * Diese koennen durch entsprechendes "rein"-kommentieren
 * sichtbar gemacht werden. (siehe unten)
 * Es werden zwei verschiedene Marker-Klassen benutzt.
 * Diese unterscheiden sich nur in der Form.
 * 
 * Aus Performancegruenden werden die Marker schon im Setup geadded. 
 * (~ 10^64 mal schneller)
 *
 * @author dbechinie
 */

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.geo.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import java.text.*; 

// ------ Anzuzeigende Sachen auf true setzen START -------

Boolean delay = false;

Boolean showall = true;
Boolean highfrequencyfilter = false;
Boolean datefilter = false;
Boolean weekdayfilter = false;
Boolean wohnortfilter = false;
Boolean growingmarkersfilter = false;
Boolean servicefilter = false;

// ------ Anzuzeigende Sachen auf true setzen END -------


// init variables
Iterator iter;
int speed = 1;
UnfoldingMap map; 
TrackpointList tpl;
int currentTrackpoint = 0;
String[] trackdata;
TrackpointList highfrequency;
TrackpointList date;
TrackpointList weekday;
TrackpointList wohnort;
TrackpointList growme;
TrackpointList service;




// all the shit
void setup() {
  size(1000, 600);
  if(frame != null){
    frame.setResizable(true);
  }
  smooth();
  //huebsch farbige Map
  //map = new UnfoldingMap(this, new Microsoft.RoadProvider());
  
  //default map
  map = new UnfoldingMap(this);
  
  map.setTweening(true); // richtiges smooth Movement
  
  map.zoomAndPanTo(new Location(52.5f, 13.4f), 5); // Ort und Zoomlevel Init
  MapUtils.createDefaultEventDispatcher(this, map); //für StandardInteraktion

  // lade Daten von MalteSpitz
  DatenImportMalte im= new DatenImportMalte();
  tpl = im.ladeStandardCSV("Daten_Malte_Spitz.csv");
  
  
  // alle Marker in default-Schwarz auf die Karte  -
  if(showall == true){
    if(delay == false){
      for ( Trackpoint tp : tpl ){
        MarkerRound marker = new MarkerRound(tp.getLocation());
        map.addMarker(marker);
      }
    }
    
    iter = tpl.iterator();
  }
  
  
  // ------ Filter START ------
  
  // 3 Tage: 2.10. - 5.10. in rot
  if(datefilter == true){
    FilterDate filterdate = new FilterDate();
    Timestamp ts1 = new Timestamp(2009,10,2,22,20,40,0); // entspricht 2.10.2009 22 Uhr 20 Minuten 40 sek
    filterdate.setStartDate(ts1);
    Timestamp ts2 = new Timestamp(2009,10,5,22,20,40,0); // entspricht 5.10.2009 22 Uhr 20 Minuten 40 sek
    filterdate.setEndDate(ts2);
    date = filterdate.apply(tpl);
    
    if(delay == false){
      for ( Trackpoint tp : date ){
        MarkerRound marker = new MarkerRound(tp.getLocation());
        marker.rot = 255;
        map.addMarker(marker);
      }
    }
    
    iter = date.iterator();    
  }
  
  // Sonntags in hellblau, wie kreatief...
  if(weekdayfilter == true){
    FilterWeekday filterweekday = new FilterWeekday();
    filterweekday.setWochentag("Sonntag");
    weekday = filterweekday.apply(tpl);
  
    if(delay == false){
      for ( Trackpoint tp : weekday ){
        MarkerRound marker = new MarkerRound(tp.getLocation());
        marker.gelb = 255;
        marker.blau = 255;
        map.addMarker(marker);
      }
    }
    
    iter = weekday.iterator();    
  }
  
  
  // Most Wanted Orte in blau
  if(highfrequencyfilter == true){
    FilterFrequency filterfrequency = new FilterFrequency();
    filterfrequency.setMinFrequency(400);
    highfrequency = filterfrequency.apply(tpl);
  
    if(delay == false){
      for ( Trackpoint tp : highfrequency ){
        MarkerRound marker = new MarkerRound(tp.getLocation());
        marker.blau = 255;
        map.addMarker(marker);
      }
    }
    
    iter = highfrequency.iterator();    
  }
  
  // Wohnort in rot eingerahmt. fast wie das Bild an seiner Wand
  if(wohnortfilter == true){
    FilterFrequency filterhome = new FilterFrequency();
    filterhome.setMinFrequency(4000);
    wohnort = filterhome.apply(tpl);

    if(delay == false){
      for ( Trackpoint tp : wohnort ){
        MarkerLabeled marker = new MarkerLabeled(tp.getLocation());
        marker.rot = 200;
        marker.gelb = 50;
        marker.setSize(9);
        marker.setLabel("Wohnort");
        marker.setTextSize(17);
        map.addMarker(marker);
      }
    }
    
    iter = wohnort.iterator();    
  }
  
  
  // wachsende Marker, fancy as shit! Aber nicht auf alles, dann more shit than fancy!
  // now in green!
  if(growingmarkersfilter == true){
    FilterFrequency grow = new FilterFrequency();
    grow.setMinFrequency(100);
    growme = grow.apply(tpl);
    
    if(delay == false){
      for (Trackpoint tp : growme){
        MarkerRound marker = new MarkerRound(tp);
        marker.setSize((int)Math.sqrt(growme.getFrequency(tp))/2);
        marker.setColor(0,150,50);
        map.addMarker(marker);
      }
    }
    
    iter = growme.iterator(); 
  }
  
  if(servicefilter == true){
   FilterService filterservice = new FilterService();
   filterservice.setService("GPRS");
   service = filterservice.apply(tpl); 
   
   if(delay == false){
     for(Trackpoint tp: service){
      MarkerInternet marker = new MarkerInternet(tp);
      marker.setColor(0,0,200);
      marker.setSize(14);
      map.addMarker(marker); 
     }
   }
   iter = service.iterator(); 
   
  }
  
  
  // ------ Filter ENDE ------
  
}

void draw() {
  
  map.draw();
  
  if(delay == true){
    if(iter.hasNext()){
      if(frameCount % speed == 0){
        Trackpoint curr = (Trackpoint) iter.next();
        MarkerRound tmp = new MarkerRound(curr);
        tmp.blau = 250;
        tmp.rot = 50;
        tmp.setSize(7);
        map.addMarker(tmp);
        //map.panTo(curr.getLocation());
        System.out.println(curr.getDateTime());
      }
    }
  }
      
      
}

