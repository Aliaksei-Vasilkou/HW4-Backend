/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.coyclab.backend;

import java.io.IOException;

import com.example.Updater;
import com.google.gson.Gson;

import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Integer actualVersion = Integer.valueOf(request.getParameter("actual_version"));
        Boolean isNeedForceUpdate = Boolean.valueOf(request.getParameter("is_need_force_update"));

        response.setContentType("application/json");

        Updater updater = new Updater();

        updater.setActualVersion(actualVersion);
        updater.setNeedForceUpdate(isNeedForceUpdate);

        new Gson().toJson(updater, response.getWriter());
    }
}
