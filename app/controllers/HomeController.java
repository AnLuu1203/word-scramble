package controllers;

import java.io.IOException;

import models.Entry;
import play.mvc.*;
import services.ScrambleWord;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * @throws IOException 
     */
    public Result index() throws IOException {
    	ScrambleWord scrambleWord = new ScrambleWord();
    	Entry test = new Entry();
        return ok(views.html.index.render(scrambleWord.getOriginWord(), scrambleWord.getShuffleWord()));
    }

}
