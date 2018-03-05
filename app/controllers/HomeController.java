package controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import models.Entry;
import play.mvc.*;
import play.libs.Json;
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
    public Result index() {
        session().clear();
        ScrambleWord scrambleWord = new ScrambleWord();
        session("originWord", scrambleWord.getOriginWord());
        return ok(views.html.index.render(scrambleWord.getOriginWord(), scrambleWord.getShuffleWord()));
    }

    public Result validate(String answer) {
        ScrambleWord scrambleWord = new ScrambleWord(session("originWord"));
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("valid", scrambleWord.validateWord(answer));
        result.put("answer", answer);

        return ok(Json.toJson(result));
    }

}
