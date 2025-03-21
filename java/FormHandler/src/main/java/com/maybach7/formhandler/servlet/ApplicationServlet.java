package com.maybach7.formhandler.servlet;

import com.maybach7.formhandler.dto.ApplicationDto;
import com.maybach7.formhandler.entity.User;
import com.maybach7.formhandler.exception.ValidationException;
import com.maybach7.formhandler.service.ApplicationService;
import com.maybach7.formhandler.util.CookiesUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


@WebServlet("/application")
public class ApplicationServlet extends HttpServlet {

    private static final List<String> singleFields = Arrays.asList(
            "fullname",
            "email",
            "phone",
            "birthday",
            "gender",
            "biography"
    );

    private static final List<String> multipleFields = Arrays.asList(
            "languages"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("form.jsp");

        HttpSession session = req.getSession();
        if(session.getAttribute("errors") != null) {
            req.setAttribute("errors", session.getAttribute("errors"));
            session.removeAttribute("errors");
            dispatcher.forward(req, resp);
        } else {
            // Мы перенаправляем сюда POST-запрос, предварительно записав в ответ необходимые Cookies
            // Здесь мы читаем эти Cookies из запроса, устанавливаем в запросе аттрибуты для каждого поля,
            // имея в них имя Cookie, и его значение.
            // Затем этот запрос направляется в form.jsp с помощью RequestDispatcher, откуда оно будет передано
            // обратно изначальному клиенту
            for (var field : singleFields) {
                CookiesUtil.getCookie(req, field).ifPresent(value -> req.setAttribute(field, value));
            }

            for (var field : multipleFields) {
                CookiesUtil.getCookieArray(req, field).ifPresent(value -> req.setAttribute(field, String.join(",", value)));
            }

            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        var applicationDto = ApplicationDto.builder()
                .fullName(req.getParameter("fullname"))
                .email(req.getParameter("email"))
                .phone(req.getParameter("phone"))
                .birthday(req.getParameter("birthday"))
                .gender(req.getParameter("gender"))
                .programmingLanguages(Arrays.stream(req.getParameterValues("languages")).toList())
                .biography(req.getParameter("biography"))
                .build();
        try
        {
            System.out.println(applicationDto);
            User user = ApplicationService.getInstance().createUser(applicationDto);
            System.out.println(user);

            // setting cookies after successfully submitting the form
            int year = 60 * 60 * 24 * 365;
            for(var field : singleFields) {
                String value = req.getParameter(field);
                CookiesUtil.setCookie(resp, field, value, year);
            }
            for(var field : multipleFields) {
                String[] values = req.getParameterValues(field);
                CookiesUtil.setCookieArray(resp, field, values, year);
            }

            resp.sendRedirect(req.getContextPath() + "/application");
        } catch(ValidationException exc) {      // здесь необходимо передать список ошибок в JSP и обработать там

//            System.out.println("Произошла ошибка в валидации");
//            req.setAttribute("errors", exc.getErrors());
//            System.out.println("Установили errors: " + exc.getErrors());
//            resp.sendRedirect(req.getContextPath() + "/application");

            System.out.println("Произошла ошибка в валидации");
            req.getSession().setAttribute("errors", exc.getErrors());
            resp.sendRedirect(req.getContextPath() + "/application");
        }
    }
}