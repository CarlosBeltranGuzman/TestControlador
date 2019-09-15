/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.controladores;



import java.util.logging.Level;
import java.util.logging.Logger;
import us.monoid.json.JSONArray;
import us.monoid.json.JSONObject;
import us.monoid.web.Resty;



/**
 *
 * @author 1102888499
 */
public class ControladorApiGoRest {
    public static void main(String[] args) {
        try {
            JSONObject jsonUser = new Resty().json("https://gorest.co.in/public-api/users?_format=json&access-token=_ELgKHtDJOqZk7CNQs-psO5uJ5Kr7_84zHLZ").object();
            JSONObject jsonPosts = new Resty().json("https://gorest.co.in/public-api/posts?_format=json&access-token=PWtZ7dsfHDGzu4bdcwwy2ftZQZ2_sstaPo6w").object();
            JSONObject jsonComments = new Resty().json("https://gorest.co.in/public-api/comments?_format=json&access-token=PWtZ7dsfHDGzu4bdcwwy2ftZQZ2_sstaPo6w").object();
            
            JSONObject jsonMeta = jsonUser.getJSONObject("_meta");
            //System.out.println(jsonMeta.get("success"));
            
            JSONArray jSONArrayResult = jsonUser.getJSONArray("result");
            JSONArray jSONArrayResult2 = jsonPosts.getJSONArray("result");
            JSONArray jSONArrayResult3 = jsonComments.getJSONArray("result");
                    
            
            for (int i = 0; i < jSONArrayResult.length(); i++) {
                
                System.err.println(jSONArrayResult.getJSONObject(i).get("first_name"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("last_name"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("gender"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("dob"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("email"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("phone"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("website"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("address"));
                System.err.println(jSONArrayResult.getJSONObject(i).get("status"));
                System.out.println("\n");
            }
//            for (int i = 0; i < jSONArrayResult2.length(); i++) {
//                
//                System.err.println(jSONArrayResult2.getJSONObject(i).get("post_id"));
//                System.err.println(jSONArrayResult2.getJSONObject(i).get("name"));
//                System.err.println(jSONArrayResult2.getJSONObject(i).get("email"));
//                System.err.println(jSONArrayResult2.getJSONObject(i).get("bodybody"));
//                
//                System.out.println("\n");
//            }
            
        } catch (Exception ex) {
            Logger.getLogger(ControladorApiGoRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
