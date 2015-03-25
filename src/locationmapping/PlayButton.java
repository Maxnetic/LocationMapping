package locationmapping;

public class PlayButton extends Button {
    LocationMapper mapper;
    /**
     * Durchmesser des Buttons
     */
    float d;


    public PlayButton(LocationMapper mapper, float d) {
        super(mapper, d, d, d, d);
        this.mapper = mapper;
        this.d = d;
    }

    boolean mouseOver(int xM, int yM) {
        return (xM > x-d/2 && xM < x+d/2 && yM > y-d/2 && yM < y+d/2 );
    }

    void draw() {
        this.x = app.width/2f;
        this.y = app.height - this.d/2f - 32;

        app.fill( mouseOver(app.mouseX, app.mouseY) ? 255 : 220);
        app.stroke(150);
        app.ellipse(x, y, d, d);

        // Je nach Pausezustand wird Play- oder Stop-Symbol gezeichnet
        app.noStroke();
        app.fill(120);
        if(mapper.paused == false) {
            app.rect(x-d/7-d/14, y-d/4, d/7, d/2);
            app.rect(x+d/7-d/14, y-d/4, d/7, d/2);
        } else {
            app.triangle(x-d/7, y-d/4, x-d/7, y+d/4, x+d/4, y);
        }
    }


}

