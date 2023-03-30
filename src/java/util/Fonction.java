/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Christian
 */
public class Fonction {
    
    public String processUrl(String url_input, String ctx) {
        ctx+="/";
        int ctx_ind = url_input.indexOf(ctx);
        System.out.print(ctx_ind);
        String url = url_input.substring(ctx_ind + ctx.length());

        return url;
    }
}
