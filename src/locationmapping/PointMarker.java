package locationmapping;

import java.util.HashMap;

import org.joda.time.DateTime;

import processing.core.PGraphics;
import processing.core.PFont;

import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.geo.Location;

/**
 * Die Klasse PointMarker stellt die Visualisierung von Trackpoints zur Verfügung.
 * Für einen bestimmten Trackpoint kann ein Marker auf die Karte gezeichnet werden,
 * der in Groesse, Form, Farbe und Beschriftung variiert werden kann.
 * PointMarker erweitern SimplePointMarker um Label, Zeitstempel, HSB Farbwerte,
 * Häufigkeitswerte, Schriftarten und Textgrößen.
 *
 * @author FU-Berlin Softwarepraktikum 2015
 * @version 1.0
 */

public class PointMarker extends SimplePointMarker implements Const{
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
    int transparency = 30;
    /**
    * Alpha Wert (Transparenz) für Farbe der Umrandung Markers
    */
    int transparencyBorder = 100;
    /**
    * Textgroesse der Beschriftung
    */
    int fontsize = 12;


    /**
    * Konstruktor für PointMarker Objekte
    *
    * @param location Ortskoordinaten des Markers
    */
    public PointMarker(Location location) {
        super(location, new HashMap<String, Object>());
        this.setColor(Const.DEFAULT_HIGHLIGHT_COLOR);
        this.setStrokeColor(Const.DEFAULT_HIGHLIGHT_COLOR);
        this.setStrokeWeight(3);
        this.setRadius(20);
    }

    /**
     * Konstruktor für PointMarker Objekte
     *
     * @param trackpoint Trackpoint aus dem Marker gezeichnet werden soll
     */
    public PointMarker(Trackpoint trackpoint) {
        this(trackpoint.getLocation());
        this.setProperty("time", trackpoint.getTime());
    }


    public void setProperty(String key, Object value){
        this.properties.put(key, value);
    }

    /**
     * Setzt Zeitkoordinate
     *
     * @param time neues Label für den Marker
     * @return das PointMarker-Objekt für Method-Chaining
     */
    public PointMarker setTime(DateTime time) {
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
     * Setzt Ortshäufigkeit
     *
     * @param frequency neue Ortshäufigkeit für den Marker
     * @return das PointMarker-Objekt für Method-Chaining
     */
    public PointMarker setFrequency(Integer frequency) {
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
     * @return das PointMarker-Objekt für Method-Chaining
     */
    public PointMarker setLabel(String label) {
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
     * @return das PointMarker-Objekt für Method-Chaining
     */
    public PointMarker setFontsize(int size){
        this.fontsize = size;
        return this;
    }
    /**
     * Setzt Schriftart
     *
     * @param font Schriftart
     * @return das PointMarker-Objekt für Method-Chaining
     */
    public PointMarker setFont(PFont font){
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
    * Setzt Radius des Markers
    *
    * @param radius neuer Radius des Markers in Pixeln
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setRadius(int radius){
        super.setRadius(radius);
        return this;
    }
    /**
    * Setzt Radius des Markers
    *
    * @param radius neuer Radius des Markers in Pixeln
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setSize(float radius){
        super.setRadius(radius);
        return this;
    }
    /**
    * Setzt die Größe der Fläche des Markers
    *
    * @param area neue Größe der Fläche des Markers in Quadratpixeln
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setArea(double area){
        super.setRadius((float) Math.sqrt(area/Math.PI));
        return this;
    }


    /**
    * Setzt die Farbe des Markers durch HSB Kodierung
    *
    * @param hue Farbwert
    * @param saturation Sättigungswert
    * @param brightness Helligkeitswert
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setColor(int hue, int saturation, int brightness){
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        return this;
    }
    /**
    * Setzt Farbtonwert der Farbe des Markers
    *
    * @param hue Farbwert
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setHue(int hue){
        this.hue = hue;
        return this;
    }
    /**
    * Setzt Sättigungswert der Farbe des Markers
    *
    * @param saturation Sättigungswert
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setSaturation(int saturation){
        this.saturation = saturation;
        return this;
    }
    /**
    * Setzt Helligkeitswert der Farbe des Markers
    *
    * @param brightness Helligkeitswert
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setBrightness(int brightness){
        this.brightness = brightness;
        return this;
    }
    /**
    * Setzt die Farbe des Markers durch HSB Kodierung inklusive Transparenz und setzt Marker randlos
    *
    * @param hue Farbwert
    * @param saturation Sättigungswert
    * @param brightness Helligkeitswert
    * @param transparency Transparenzwert des Marker
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setColor(int hue, int saturation, int brightness, int transparency){
        this.transparency = transparency;
        super.setStrokeWeight(0);
        return this.setColor(hue, brightness, saturation);
    }
    /**
    * Setzt die Transparenz des Markers und setzt Marker randlos
    *
    * @param transparency neuer Transparenzwert des Markers
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setTransparency(int transparency){
        this.transparency = transparency;
        return this.setNoBorder();
    }
    /**
    * Setzt Marker randlos
    *
    * @return das PointMarker-Objekt für Method-Chaining
    */
    public PointMarker setNoBorder(){
        super.setStrokeWeight(0);
        return this;
    }



    /**
    * Zeichnet Marker
    * @param pg Objekt das gezeichnet werden soll
    * @param x X-Koordinate des Markers
    * @param y Y-Koordinate
    */
    public void draw(PGraphics pg, float x, float y){
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
        this.setStrokeColor(pg.color(this.hue, this.saturation, this.brightness, this.transparencyBorder));

        // Zeichne Marker
        super.draw(pg, x, y);

        try {
            pg.fill(pg.color(this.hue, this.saturation, this.brightness, this.transparencyBorder));
            pg.textFont(this.getFont(), this.fontsize);
            pg.text(this.getLabel(), x+this.radius, y+this.fontsize/2-2);
        } catch(Exception e){;}

    }
}