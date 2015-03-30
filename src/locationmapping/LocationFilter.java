package locationmapping;

import java.util.*;

import de.fhpotsdam.unfolding.geo.*;

/**
 * LocationFilter erweitert Filter.
 * Filtert eine TrackpointList nach Ortsvariablen.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class LocationFilter extends Filter {
    /**
     * Mindestfrequenz für den Frequenzfilter
     */
    int minfrequency = 0;
    /**
    * Location, nach der gefiltert wird
    */
    Location location;
    /**
    * Radius um Location, die im Radiusfilter genutzt werden
    */
    int radius = 0;
    /**
    * Service, der im Servicefilter gefiltert wird (SMS, Internet, GPRS)
    */
    String service;


    /**
    * Setzt die Mindesthaeufigkeit
    * @param minf Mindesthaeufigkeit
    */
    public void setMinFrequency(int minf){
        minfrequency = minf;
    }

    /**
    * Setzt den Radius
    *
    * @param radius Radius, um den gefiltert wird
    */
    public void setRadius(int radius){
        this.radius = radius;
    }

    /**
    * Setzt die Location
    *
    * @param location Ort, um den gefiltert wird
    */
    public void setLocation(Location location){
        this.location = location;
    }

    /**
    * Setzt den Service
    *
    * @param service Service, nach dem gefiltert werden soll
    */
    public void setService(String service){
        this.service = service;
    }

    /**
 	 * Vergleicht zwei TrackpointLists auf aehnliche Orte
	 *
	 * @param tpl1 erste TrackpointList zum Vergleichen
	 * @param tpl2 zweite TrackpointList zum Vergleichen
	 * @param radius Radius, um Ueberschneidungen in beiden TrackpointLists zu finden
	 * @return Liste mit Trackpoints, die innerhalb des Radius waren 
	 */
    public TrackpointList compareLocation(TrackpointList tpl1, TrackpointList tpl2, int radius){
        TrackpointList newtpl = new TrackpointList();
        for ( Trackpoint tp1 : tpl1 ){
           for ( Trackpoint tp2 : tpl2 ){
                if ( tp1.locationDistanceTo(tp2.getLocation()) <= radius ){
                    newtpl.add(tp1);
                    break;
                }
            }
        }
        return newtpl;
    }

   	/**
 	 * Vergleicht zwei TrackpointLists auf aehnliche Orte zu aehnlichen Zeiten
	 *
	 * @param tpl1 erste TrackpointList zum Vergleichen
	 * @param tpl2 zweite TrackpointList zum Vergleichen
	 * @param radius Radius, um Ueberschneidungen in beiden TrackpointLists zu finden
	 * @param minutes Minuten, die eine Ueberschneidung auseinander liegen darf
	 * @return Liste mit Trackpoints, die innerhalb des Radius waren 
	 */
    public TrackpointList compareLocationAndTime(TrackpointList tpl1, TrackpointList tpl2, int radius, int minutes){
        TrackpointList newtpl = new TrackpointList();
        for (Trackpoint tp1 : tpl1){
            for (Trackpoint tp2 : tpl2){
                if (  (tp1.locationDistanceTo(tp2.getLocation()) <= radius) && (tp1.locationDistanceTo(tp2) < minutes * 60) ){
                    newtpl.add(tp1);
                 break;
                }
            }
        }
        return newtpl;
    }


    /**
    * Filtert nach Frequenz
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void frequencyFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(trackpointlist.getFrequency(tp) < minfrequency)
                tp.setVisible(false);
        }
    }

    /**
    * Filtert nach Location und Radius
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void locationFilter(TrackpointList trackpointlist){
        for( Trackpoint tp : trackpointlist ){
            if (tp.locationDistanceTo(location) > radius)
                tp.setVisible(false);
        }
    }

    /**
    * Filtert nach Service
    * @param trackpointlist Liste, die gefiltert werden soll
    */
    private void serviceFilter(TrackpointList trackpointlist){
        for(Trackpoint tp : trackpointlist){
            if(!(tp.getService().equals(service))){
                tp.setVisible(false);
            }
        }
    }


    /**
     * apply Methode, die je nachdem, wie die Attribute gesetzt wurden filtert
     * Es wird ueberprueft, ob und welche Attribute gesetzt wurden, so dass bestimmte Filter aufgerufen werden
     *
     * @param trackpointlist Die TrackpointList, die gefiltert wird
     * @return gibt eine TrackpointList zurueck, in der die visible Eigenschaft veraendert wurde
    */
    public TrackpointList apply(TrackpointList trackpointlist) {

     // Frequenzfilter
     if (minfrequency > 1) {
        frequencyFilter(trackpointlist);
     }

     // Radiusfilter
     if (radius != 0 && location != null) {
        locationFilter(trackpointlist);
     }

     //Servicefilter
     if (service != null) {
        serviceFilter(trackpointlist);
     }

     //ueberschreiben der Rauszufilternden Punkte
     TrackpointList filteredtpl = new TrackpointList();
     for(Trackpoint tp : trackpointlist){
         if(tp.getVisible() == true){
             filteredtpl.add(tp);
         }else{
             tp.setVisible(true);
         }
     }
     return filteredtpl;
    }
}
