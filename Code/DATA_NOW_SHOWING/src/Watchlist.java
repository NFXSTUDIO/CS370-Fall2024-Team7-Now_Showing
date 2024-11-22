import org.bson.types.ObjectId;
import java.util.ArrayList;

interface Watchlist extends AccessUserData {
    public static Boolean add_to_watchlist(String username, ObjectId media_id) {
        ArrayList<String> watchlist = AccessUserData.get_user_watchlist(username);
        String media = media_id.toString();
        watchlist.add(media);
        return AccessUserData.update_user_watchlist(username, watchlist);
    }

    public static Boolean remove_from_watchlist(String username, ObjectId media_id) {
        ArrayList<String> watchlist = get_watchlist(username);
        String media = media_id.toString();
        watchlist.remove(media);
        return AccessUserData.update_user_watchlist(username, watchlist);
    }

    public static ArrayList<String> get_watchlist(String username) {
        return AccessUserData.get_user_watchlist(username);
    }

    public static Boolean remove_watchlist(String username) {
        ArrayList<String> watchlist = new ArrayList<String>();
        return AccessUserData.update_user_watchlist(username, watchlist);
    }
}
