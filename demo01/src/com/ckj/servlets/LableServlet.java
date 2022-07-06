package com.ckj.servlets;

import com.ckj.bean.Lable;
import com.ckj.myspringmvc.ViewBaseServlet;
import com.ckj.myspringmvc.util.StringUtil;
import com.ckj.service.LableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/lable.do")
public class LableServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到请求");
        req.setCharacterEncoding("utf-8");
        LableService lableService = new LableService();
        HttpSession session = req.getSession();
        
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        Lable lable = lableService.getLableById(id);
        session.setAttribute("lable", lable);
        super.processTemplate("index", req, resp);
    }
}
