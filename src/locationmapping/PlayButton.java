package locationmapping;

import processing.core.PApplet;

public class PlayButton extends Button {

    public PlayButton(LocationMap map, int w, int h) {
        //zeichne Rechteck
        super(map, w/2-16 , h-56, 28, 28);
    }


    void draw() {
        super.draw();

        //zeichne einen Kreis über das Rechteck
        app.fill(mouseOver() ? 255 : 220);
        app.stroke(150);
        app.ellipse(app.width/2-2, app.height-42, 40, 40);


        app.noStroke();
        app.fill(120);
        /**
         * Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
         */
        if(map.paused == false) {
            app.rect(app.width/2-9, app.height-49, 5, 15);
            app.rect(app.width/2+1, app.height-49, 5, 15);
        }
        else {
            app.triangle(app.width/2-5, app.height-48, app.width/2-5, app.height-34, app.width/2+5, app.height-42);
        }
    }


}

