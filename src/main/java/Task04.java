import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task04 {
    public static void main(String[] args) {
        LowerCaseHandler obj = new LowerCaseHandler();
        System.out.println(obj.handleMessage("Very impotant message"));
        AlphabetHandler obj1 = new AlphabetHandler();
        System.out.println(obj1.handleMessage("Very impotant message"));
    }
}

interface Handler{
    String handleMessage(String message);
}

class LowerCaseHandler implements Handler{
    String str;
    public String handleMessage(String message){
        str = message.toLowerCase().trim();
        return str;
    }
}
class AlphabetHandler implements Handler {
    public String handleMessage(String message) {

        if (check(message) == false)
            return null;
        else
            return message;
    }

    public static boolean check(String str) {
        Pattern p = Pattern.compile("([^A-z\\s]+)");
        Matcher m = p.matcher(str);
        if (m.find())
            return false;
        else return true;
    }
}
