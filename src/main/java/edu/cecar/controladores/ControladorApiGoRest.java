package edu.cecar.controladores;

import edu.cecar.modelo.Users;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import static sun.applet.AppletResourceLoader.getImage;

/**
 *
 * @author 1102888499
 */
public class ControladorApiGoRest {

    public ControladorApiGoRest() {
        try {
            System.out.println("Cargando a la Coleccion");
            Users user;
            ArrayList<Users> users = new ArrayList<>();
            JSONObject jsonUsers = new Resty().json("https://gorest.co.in/public-api/users?_format=json&access-token=ejnYGsJy6juNHxIHqDTzRZLMKXt7Rh28KyPB").object();
            JSONObject jsonPost = new Resty().json("https://gorest.co.in/public-api/posts?_format=json&access-token=ejnYGsJy6juNHxIHqDTzRZLMKXt7Rh28KyPB").object();
            JSONObject jsonComments = new Resty().json("https://gorest.co.in/public-api/comments?_format=json&access-token=ejnYGsJy6juNHxIHqDTzRZLMKXt7Rh28KyPB").object();

            JSONArray jsonArrayResultUsers = jsonUsers.getJSONArray("result");
            JSONArray jsonArrayResultPost = jsonPost.getJSONArray("result");
            JSONArray jsonArrayResultComments = jsonComments.getJSONArray("result");

            for (int i = 0; i < jsonArrayResultUsers.length() && i<jsonArrayResultPost.length() && i<jsonArrayResultComments.length(); i++) {
                Image img = getImage(new URL(jsonArrayResultUsers.getJSONObject(i).getJSONObject("_links").getJSONObject("avatar").get("href").toString()));
                ImageIcon imagen = new ImageIcon(img);
                    
                user = new Users(Integer.parseInt(jsonArrayResultUsers.getJSONObject(i).get("id").toString()),
                                 jsonArrayResultUsers.getJSONObject(i).get("first_name").toString(),
                                 jsonArrayResultUsers.getJSONObject(i).get("last_name").toString(),
                                 jsonArrayResultUsers.getJSONObject(i).get("gender").toString(), 
                                 jsonArrayResultUsers.getJSONObject(i).get("dob").toString(), 
                                 jsonArrayResultUsers.getJSONObject(i).get("email").toString(), 
                                 jsonArrayResultUsers.getJSONObject(i).get("phone").toString(), 
                                 jsonArrayResultUsers.getJSONObject(i).get("website").toString(),
                                 jsonArrayResultUsers.getJSONObject(i).get("address").toString(), 
                                 jsonArrayResultUsers.getJSONObject(i).get("status").toString(),
                                 jsonArrayResultPost.getJSONObject(i).get("body").toString(),
                                 jsonArrayResultComments.getJSONObject(i).get("body").toString(), 
                                 imagen);
                users.add(user);
            }
            System.out.println("Archivos cargados");

        } catch (Exception ex) {
            Logger.getLogger(ControladorApiGoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}