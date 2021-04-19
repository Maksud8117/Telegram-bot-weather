import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //bc347582ccda4a2b3880cc2f3e93c7fd
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=bc347582ccda4a2b3880cc2f3e93c7fd");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while(in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONObject wind = object.getJSONObject("wind");
        model.setSpeed(wind.getDouble("speed"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i=0; i<getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemp() + " C" + "\n" +
                "Main: " + model.getMain() + "\n" +
                "Humidity: " + model.getHumidity() + " %" + "\n" +
                "Wind speed: " + model.getSpeed() + " m/s" + "\n" +
                "http://openweathermap.org/img/wn/" + model.getIcon() + ".png";
    }
}

