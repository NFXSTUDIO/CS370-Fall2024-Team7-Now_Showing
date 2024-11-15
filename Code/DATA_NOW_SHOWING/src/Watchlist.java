import org.bson.types.ObjectId;
import java.util.ArrayList;

public class Watchlist extends AccessUserData {
    public static void add_to_watchlist(String username, ObjectId media_id) {
        ArrayList<String> watchlist = getWatchlist(username);
        String media = media_id.toString();
        watchlist.add(media);
        updateWatchlist(username, watchlist);
        System.out.println("Media selected add to watchlist !");
    }

    public static void add_to_watchlist(String username, ArrayList<ObjectId> media_id) {
        ArrayList<String> watchlist = getWatchlist(username);
        for (ObjectId objectId : media_id) {
            watchlist.add(objectId.toString());
        }
        updateWatchlist(username, watchlist);
        System.out.println("All media selected add to watchlist !");
    }

    public static void remove_from_watchlist(String username, ObjectId media_id) {
        ArrayList<String> watchlist = getWatchlist(username);
        String media = media_id.toString();
        watchlist.remove(media);
        updateWatchlist(username, watchlist);
        System.out.println("Media selected remove from watchlist !");
    }

    public static void remove_from_watchlist(String username, ArrayList<ObjectId> media_id) {
        ArrayList<String> watchlist = getWatchlist(username);
        for (ObjectId objectId : media_id) {
            watchlist.remove(objectId.toString());
        }
        updateWatchlist(username, watchlist);
        System.out.println("All media selected remove from watchlist !");
    }

    public static ArrayList<String> get_watchlist(String username) {
        return getWatchlist(username);
    }

    public static void remove_watchlist(String username) {
        ArrayList<String> watchlist = new ArrayList<String>();
        updateWatchlist(username, watchlist);
        System.out.println("Watchlist clear !");
    }
}
