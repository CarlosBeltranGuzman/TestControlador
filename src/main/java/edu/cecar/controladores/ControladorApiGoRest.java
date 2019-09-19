package edu.cecar.controladores;

import edu.cecar.componentes.singletons.SingletonConexionBD;
import edu.cecar.modelo.Users;
import edu.cecar.vistas.SingIn;
import edu.cecar.vistas.Vista;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static sun.applet.AppletResourceLoader.getImage;
import us.monoid.json.JSONException;

/**
 *
 * @author 1102888499
 */
public class ControladorApiGoRest {
    private static ResultSet r = null;
    private static PreparedStatement sql=null;
    
    public ArrayList<Users> ControladorApiGoRest() throws IOException, JSONException {
        
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
        return users;  
    }
    
    
    public static void loguear(String usu, String pass){
        try {
           
                    
            sql = SingletonConexionBD.getInstance().prepareStatement(
               "select * from users where correo ='"+usu+"' and contraseña='"+pass+"'"
            );
            r = sql.executeQuery();
            while(r.next())
                if(r.getString(1).equals(usu) && r.getString(2).equals(pass)){
                    
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Windows".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(Vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    //</editor-fold>

                                /* Create and display the form */
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        new Vista().setVisible(true);
                                    }
                                });
                }else{
                    System.out.println("No existe tal registro");
                    JOptionPane.showMessageDialog(null, "No existe el registro");
                }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorApiGoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}