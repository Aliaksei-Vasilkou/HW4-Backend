/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.coyclab.backend;

import com.example.Updater;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VersionServlet extends HttpServlet {

    @Override
    public void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.setContentType("text/plain");
        pResponse.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.setContentType("application/json");

        final Integer actualVersion = Integer.valueOf(pRequest.getParameter("actual_version"));
        final Boolean isNeedForceUpdate = Boolean.valueOf(pRequest.getParameter("is_need_force_update"));

        final Updater updater = new Updater();

        updater.setActualVersion(actualVersion);
        updater.setNeedForceUpdate(isNeedForceUpdate);

        new Gson().toJson(updater, pResponse.getWriter());
    }
}
