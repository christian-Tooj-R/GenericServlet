/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package etu1800.framework.servlet;

import etu1800.framework.Mapping;
import etu1800.framework.MethodAnnotation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import util.Fonction;
/**
 *
 * @author Christian
 */
public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> MappingUrls;
    protected Fonction func;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    public void init() throws ServletException {
        this.MappingUrls = new HashMap<>();
        List<Class<?>> allClass = getClassesInPackage("etu1800.framework");
        Mapping mapping;
        Method[] allMethods;
        for(Class<?> c : allClass) {
            allMethods = c.getMethods();

            for(Method m : allMethods) {
                if(m.isAnnotationPresent(MethodAnnotation.class)) {
                    mapping = new Mapping();
                    mapping.setClassName(c.getSimpleName());
                    mapping.setMethod(m.getName());

                    MappingUrls.put(m.getAnnotation(MethodAnnotation.class).name(), mapping);

                }
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
             String url = request.getRequestURL().toString();
          //   out.print(request.getContextPath());
          //  out.print("url: " + func.processUrl(url, request.getContextPath()));
            out.print("<h1>"+this.MappingUrls.get("test").getClassName()+"</h1>");
           // out.print(("D aona ry zandryy"))
        }
    }
    
    protected static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            for (java.net.URL resource : java.util.Collections.list(classLoader.getResources(path))) {
                for (String file : new java.io.File(resource.toURI()).list()) {
                    if (file.endsWith(".class")) {
                        String className = packageName + '.' + file.substring(0, file.length() - 6);
                        Class<?> clazz = Class.forName(className);
                        classes.add(clazz);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
