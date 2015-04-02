package locationmapping;

import java.util.HashMap;

import org.joda.time.DateTime;

import processing.core.PGraphics;
import processing.core.PFont;

import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.geo.*;

/**
 * Die Klasse ServiceMarker erweitert PointMarker um
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class ServiceMarker extends PointMarker implements Const{
    /**
     * Konstruktor für ServiceMarker Objekte, setzt Service Symbol als Label
     *
     * @param trackpoint Trackpoint aus dem Marker gezeichnet werden soll
     */
    public ServiceMarker(Trackpoint trackpoint) {
        super(trackpoint);
        this.setService(trackpoint.getService());
        this.setFontsize(20);
    }

    /**
    * Konstruktor für ServiceMarker Objekte
    *
    * @param location Ortskoordinaten des Markers
    */
    public ServiceMarker(Location location) {
        super(location);
    }

    /**
     * Setzt Service Symbol als Label
     *
     * @param service Service für den Label Symbol gesetzt wird
     * @return das ServiceMarker-Objekt für Method-Chaining
     */
    public ServiceMarker setService(String service){
        service = service.toLowerCase();
        if ( service.contains("telephonie") || service.contains("telefonie") || service.contains("phone") || service.contains("call") )
            super.setLabel(Const.PHONE);
        else if ( service.contains("sms") || service.contains("text") || service.contains("chat") )
            super.setLabel(Const.CHAT);
        else if ( service.contains("email") || service.contains("mail") )
            super.setLabel(Const.EMAIL);
        else if ( service.contains("internet") || service.contains("browser") )
            super.setLabel(Const.INTERNET);
        else
            super.setLabel("");
        return this;
    }
    /**
     * Setzt Service Symbol als Label
     *
     * @param trackpoint Trackpoint, dessen Service als Label Symbol gesetzt wird
     * @return das ServiceMarker-Objekt für Method-Chaining
     */
    public ServiceMarker setService(Trackpoint trackpoint){
        return this.setService(trackpoint.getService());
    }
}