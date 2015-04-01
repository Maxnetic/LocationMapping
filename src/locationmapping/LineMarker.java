package locationmapping;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import processing.core.PGraphics;
import processing.core.PFont;

import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import de.fhpotsdam.unfolding.utils.MapPosition;

/**
 * Die Klasse ColoredLinesMarker stellt Marker zur Verfuegung,
 * in Form von Linien zwischen Trackpoints.
 * Möglich ist dies zwischen zweien oder mit mehreren Trackpoints.
 * ColoredLinesMarker erweitert SimpleLinesMarker.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class LineMarker extends SimpleLinesMarker {
    /**
    * Farbtonwert der HSB Farbe (0-360, -1 = nicht gesetzt)
    */
    int hue = -1;
    /**
    * Sättigungswert der HSB Farbe (0-100, -1 = nicht gesetzt)
    */
    int saturation = -1;
    /**
    * Helligkeitswert der HSB Farbe (0-100, -1 = nicht gesetzt)
    */
    int brightness = -1;
    /**
    * Alpha Wert (Transparenz) für Farbe des Markers
    */
    int transparency = 100;
    /**
    * Textgroesse der Beschriftung
    */
    int fontsize = 12;


    /**
    * Konstruktor für LineMarker Objekte
    *
    * @param location Ortskoordinaten des Markers
    */
    public LineMarker(Location startLocation, Location endLocation) {
        super(startLocation, endLocation);
        this.setProperties(new HashMap<String, Object>());
        this.setColor(Const.DEFAULT_HIGHLIGHT_COLOR);
        this.setStrokeWeight(3);
    }

    /**
     * Konstruktor für LineMarker Objekte
     *
     * @param trackpoint Trackpoint aus dem Marker gezeichnet werden soll
     */
    public LineMarker(Trackpoint startTrackpoint, Trackpoint endTrackpoint) {
        this(startTrackpoint.getLocation(), endTrackpoint.getLocation());
        this.setTime(startTrackpoint.getTime());
        long duration = Math.abs(startTrackpoint.getSeconds() - endTrackpoint.getSeconds());
        double distance = Math.abs(startTrackpoint.locationDistanceTo(endTrackpoint));
        float speed = (float)Math.abs(distance/(duration/3600d));
        this.setDuration(duration);
        this.setDistance(distance);
        this.setSpeed(speed);
    }


    public void setProperty(String key, Object value){
        this.properties.put(key, value);
    }

    /**
     * Setzt Zeitkoordinate
     *
     * @param time neues Label für den Marker
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setTime(DateTime time) {
        this.setProperty("time", time);
        return this;
    }
    /**
     * Gibt die Zeitkoordinate des Marker zurück
     *
     * @return Zeitkoordinate des Marker
     * @throws RuntimeException falls keine Zeitkoordinate für Marker gesetzt ist
     */
    public DateTime getTime(){
        Object time = super.getProperty("time");
        if ( time != null && time instanceof DateTime )
            return (DateTime) time;
        else
            throw new RuntimeException("no proper time set for Marker");
    }

    /**
     * Setzt Zeitdauer
     *
     * @param duration neue Zeitdauer für den Marker in Sekunden
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setDuration(Long duration) {
        this.setProperty("duration", duration);
        return this;
    }
    /**
     * Gibt das Zeitdauer des Marker zurück
     *
     * @return Zeitdauer des Marker in Sekunden
     * @throws RuntimeException falls keine Zeitdauer für Marker gesetzt ist
     */
    public long getDuration(){
        Object duration = super.getProperty("duration");
        if ( duration != null && duration instanceof Long )
            return (long) duration;
        else
            throw new RuntimeException("no proper duration set for Marker");
    }

    /**
     * Setzt Länge
     *
     * @param distance neue Länge des Markers in Kilometern
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setDistance(Double distance) {
        this.setProperty("distance", distance);
        return this;
    }
    /**
     * Gibt das Länge des Marker zurück
     *
     * @return Länge des Marker in Kilometern
     * @throws RuntimeException falls keine Länge für Marker gesetzt ist
     */
    public double getDistance(){
        Object distance = super.getProperty("distance");
        if ( distance != null && distance instanceof Double )
            return (double) distance;
        else
            throw new RuntimeException("no proper distance set for Marker");
    }

    /**
     * Setzt Geschwindigkeitswert
     *
     * @param speed neuer Geschwindigkeitswert für den Marker
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setSpeed(Float speed) {
        this.setProperty("speed", speed);
        return this;
    }
    /**
     * Gibt den Geschwindigkeitswert des Marker zurück
     *
     * @return Geschwindigkeitswert für Marker
     * @throws RuntimeException falls keinee Geschwindigkeit für Marker gesetzt ist
     */
    public float getSpeed(){
        Object speed = super.getProperty("speed");
        if ( speed != null && speed instanceof Float )
            return (float) speed;
        else
            throw new RuntimeException("no proper speed set for Marker");
    }

    /**
     * Setzt Ortshäufigkeit
     *
     * @param frequency neue Ortshäufigkeit für den Marker
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setFrequency(Integer frequency) {
        this.setProperty("frequency", frequency);
        return this;
    }
    /**
     * Gibt die gespeicherte Ortshäufigkeit des Marker zurück
     *
     * @return Ortshäufigkeit des Marker
     * @throws RuntimeException falls keine Ortshäufigkeit für Marker gesetzt ist
     */
    public int getFrequency(){
        Object frequency = super.getProperty("frequency");
        if ( frequency != null && frequency instanceof Integer)
            return (int) frequency;
        else
            throw new RuntimeException("no proper frequency set for Marker");
    }

    /**
     * Setzt Label
     *
     * @param label neues Label für den Marker
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setLabel(String label) {
        this.setProperty("label", label);
        return this;
    }
    /**
     * Gibt Label aus
     *
     * @return Label des Markers
     * @throws RuntimeException falls kein Label für Marker gesetzt ist
     */
    public String getLabel() {
        Object label = this.getProperty("label");
        if ( label != null && label instanceof String)
            return (String) label;
        else
            throw new RuntimeException("no proper label set for Marker");
    }



    /**
     * Setzt Textgroesse
     *
     * @param size Textgröße in Punkt
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setFontsize(int size){
        this.fontsize = size;
        return this;
    }
    /**
     * Setzt Schriftart
     *
     * @param font Schriftart
     * @return das LineMarker-Objekt für Method-Chaining
     */
    public LineMarker setFont(PFont font){
        this.setProperty("font", font);
        return this;
    }
    /**
     * Gibt Schriftart des Markers zurück
     *
     * @return Schriftart des Markers
     * @throws RuntimeException falls keine Schriftart für Marker gesetzt ist
     */
    public PFont getFont(){
        Object font = this.getProperty("font");
        if ( font != null && font instanceof PFont)
            return (PFont) font;
        else
            throw new RuntimeException("no proper font set for Marker");
    }



    /**
    * Setzt Liniendicke des Markers
    *
    * @param width neue Liniendicke des Markers in Pixeln
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setWidth(int width){
        super.setStrokeWeight(width);
        return this;
    }



    /**
    * Setzt die Farbe des Markers durch HSB Kodierung
    *
    * @param hue Farbwert
    * @param saturation Sättigungswert
    * @param brightness Helligkeitswert
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setColor(int hue, int saturation, int brightness){
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        return this;
    }
    /**
    * Setzt Farbtonwert der Farbe des Markers
    *
    * @param hue Farbwert
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setHue(int hue){
        this.hue = hue;
        return this;
    }
    /**
    * Setzt Sättigungswert der Farbe des Markers
    *
    * @param saturation Sättigungswert
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setSaturation(int saturation){
        this.saturation = saturation;
        return this;
    }
    /**
    * Setzt Helligkeitswert der Farbe des Markers
    *
    * @param brightness Helligkeitswert
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setBrightness(int brightness){
        this.brightness = brightness;
        return this;
    }
    /**
    * Setzt die Farbe des Markers durch HSB Kodierung inklusive Transparenz
    *
    * @param hue Farbwert
    * @param saturation Sättigungswert
    * @param brightness Helligkeitswert
    * @param transparency Transparenzwert des Marker
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setColor(int hue, int saturation, int brightness, int transparency){
        this.transparency = transparency;
        return this.setColor(hue, brightness, saturation);
    }
    /**
    * Setzt die Transparenz des Markers und setzt Marker randlos
    *
    * @param transparency neuer Transparenzwert des Markers
    * @return das LineMarker-Objekt für Method-Chaining
    */
    public LineMarker setTransparency(int transparency){
        this.transparency = transparency;
        return this;
    }




    /**
     * Zeichnet Marker
     *
     * @param pg Objekt das gezeichnet werden soll
     * @param mapPositions Koordinaten der Linie
     */
    public void draw(PGraphics pg, List<MapPosition> mapPositions){
        // Setze Farbmodus auf HSB
        pg.colorMode(pg.HSB, 360, 100, 100, 100);

        // stellt sicher, dass alle hsb-Werte gesetzt sind
        if ( this.hue == -1 )
            this.hue = (int) pg.hue(this.color);
        if ( this.saturation == -1 )
            this.saturation = (int) pg.saturation(this.color);
        if ( this.brightness == -1 )
            this.brightness = (int) pg.brightness(this.color);

        // Setze Frben
        this.setColor(pg.color(this.hue, this.saturation, this.brightness, this.transparency));

        // Zeichne Marker
        super.draw(pg, mapPositions);

        try {
            pg.fill(pg.color(this.hue, this.saturation, this.brightness, this.transparency));
            pg.textFont(this.getFont(), this.fontsize);
            pg.text(this.getLabel(), mapPositions.get(0).x, mapPositions.get(0).y);
        } catch(Exception e){;}

    }
}