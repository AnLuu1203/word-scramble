package controllers;

import java.io.IOException;
import java.util.List;

import models.Entry;
import play.mvc.*;
import services.ScrambleWord;
import io.ebean.*;

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
//    	List<Entry> test = Entry.find.query().select("word").setDistinct(true).where().ilike("word", "_a_")..findList();
    	for (String t : scrambleWord.getValidSubWords()) {
    		System.out.println(t);
    	}
//    	System.out.println(Entry.isWordExists("Text"));
        return ok(views.html.index.render(scrambleWord.getOriginWord(), scrambleWord.getShuffleWord()));
    }

}
