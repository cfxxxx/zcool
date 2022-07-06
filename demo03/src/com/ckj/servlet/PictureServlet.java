package com.ckj.servlet;

import com.ckj.pojo.Lable;
import com.ckj.service.LableService;
import com.ckj.servlet.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/lable.do")
public class PictureServlet extends ViewBaseServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String operate = req.getParameter("operate");

        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index":
                index(req, resp);
                break;
            case "search":
                search(req, resp);
                break;
            case "view":
                view(req, resp);
                break;
            case "good":
                good(req, resp);
                break;
            case "degree":
                degree(req, resp);
                break;
            case "picture":
                picture(req, resp);
                break;
            default:
                throw new RuntimeException("operate值非法");
        }
    }

    public void index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();
        super.processTemplate("index", req, resp);
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        String idStr = req.getParameter("id");
        int id = 2991;
        if (idStr != null) {
            id = Integer.parseInt(idStr);
        }

        Lable lable = lableService.getLableById(id);
        session.setAttribute("lable", lable);
        super.processTemplate("search", req, resp);
    }

    public void view(HttpServletRequest req, HttpServletResponse response) throws IOException {
        LableService lableService = new LableService();
        HttpSession session = req.getSession();

        long l = System.currentTimeMillis();
        List<Lable> lables = lableService.orderByView();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
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
