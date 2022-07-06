package com.ckj.controller;

import com.ckj.myspringmvc.ViewBaseServlet;
import com.ckj.pojo.Lable;
import com.ckj.service.LableService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class PictureController extends ViewBaseServlet {

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) throws ServletException {
        this.servletContext = servletContext;
        super.init(servletContext);
    }

    public void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        String name = req.getParameter("name");
        String theme = req.getParameter("theme");
        List<Lable> lables = null;
        if (name != null) {
            lables = lableService.getLableByName(name);
        } else {
            lables = lableService.getLableByTheme(theme);
        }
        session.setAttribute("lables", lables);
        super.processTemplate("show", req, resp);
    }

    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        List<Lable> lableSrc = lableService.getLableSrc();
        for (int i = 0; i < lableSrc.size(); i++) {
            session.setAttribute("src"+String.valueOf(i),lableSrc.get(i));
        }

        super.processTemplate("index", req, resp);
    }

    public void search01(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        List<Lable> lables = lableService.getLableThemes();
        session.setAttribute("lables", lables);
        super.processTemplate("search01", req, resp);
    }

    public void search02(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        String name = req.getParameter("name");


        List<Lable> lables = lableService.getLableByName(name);
        session.setAttribute("lables", lables);
        super.processTemplate("search02", req, resp);
    }

    public void view(HttpServletRequest req, HttpServletResponse response) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();


        List<Lable> lables = lableService.orderByView();
        session.setAttribute("lables", lables);
        super.processTemplate("order", req, response);

    }

    public void good(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        List<Lable> lables = lableService.orderByGood();
        session.setAttribute("lables", lables);
        super.processTemplate("order", req, resp);
    }

    public void degree(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        List<Lable> lables = lableService.orderByDegree();
        session.setAttribute("lables", lables);
        super.processTemplate("order", req, resp);
    }

    public void picture(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getParameter("src"));
    }
}
