import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainGET {
  static int printTemp(JSONArray arr,long dt)
  {
      for (int i = 0; i < arr.size(); i++)
      {
        JSONObject new_obj = (JSONObject) arr.get(i);
        if (((long)(new_obj.get("dt")))==dt) {
          System.out.println(((JSONObject)new_obj.get("main")).get("temp"));
          return 0;
        }

      }
  return 1;
  }

  static int printWindSpeed(JSONArray arr,long dt)
  {
      for (int i = 0; i < arr.size(); i++)
      {
        JSONObject new_obj = (JSONObject) arr.get(i);
        if (((long)(new_obj.get("dt")))==dt) {
          System.out.println(((JSONObject)new_obj.get("wind")).get("speed"));
          return 0;
        }

      }
  return 1;
  }
  static int printPressure(JSONArray arr,long dt)
  {
      for (int i = 0; i < arr.size(); i++)
      {
        JSONObject new_obj = (JSONObject) arr.get(i);
        if (((long)(new_obj.get("dt")))==dt) {
          System.out.println(((JSONObject)new_obj.get("main")).get("pressure"));
          return 0;
        }

      }
  return 1;
  }

    public static void main(String[] args) {
        try {

            URL url = new URL("https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d28e10d714a6e88b30761fae22");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                //Get the required object from the above created object
                JSONArray arr = (JSONArray) data_obj.get("list");




                while(true)
                {

                  int input=new Scanner(System.in).nextInt();
                  if(input==0)
                    break;
                  long dt;
                  System.out.print("Enter Date : ");
                  dt=new Scanner(System.in).nextLong();
                  switch(input)
                  {

                  case 1 :System.out.print("Temperature : ");
                          printTemp(arr,dt);
                          break;
                  case 2 :System.out.print("Wind Speed : ");
                          printWindSpeed(arr,dt);
                          break;
                  case 3 : System.out.print("Pressure : ");
                          printPressure(arr,dt);
                          break;
                  }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
