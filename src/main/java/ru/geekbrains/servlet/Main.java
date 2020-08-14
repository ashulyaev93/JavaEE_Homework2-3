package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;


@WebServlet (name = "Main" , urlPatterns = "/main" )
public class Main extends HttpServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private transient ServletConfig config;

    // Метод вызывается контейнером после того как был создан класс сервлета
    @Override
    public void init (ServletConfig config) throws ServletException {

// Сохраняем полученную от сервера конфигурацию
        this .config = config;
    }
    @Override
    public ServletConfig getServletConfig () {
        return config;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service (ServletRequest req, ServletResponse res) throws
            ServletException, IOException {
        logger.info( "New request" );

        // получаем объект типа BufferedWriter и пишем в него ответ на пришедший запрос
        res.getWriter().println( "<h1>Главная</h1>" );
        res.getWriter().println("<a href='catalog'>Каталог</a>");
        res.getWriter().println("<a href='product'>Товар</a>");
        res.getWriter().println("<a href='cart'>Корзина</a>");
        res.getWriter().println("<a href='order'>Заказ</a>");
    }

    @Override
    public String getServletInfo () {
        return "Main" ;
    }

    // При завершении работы веб приложения,контейнер вызывает этот метод для всех сервлетовиз этого приложения
    @Override
    public void destroy () {
        logger.info( "Servlet {} destroyed" , getServletInfo());
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
// обратите внимание, что здесь указывается имя сервлета
        String path = "header.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");

        try {
            writer.println("<h2>Not Found: " + id + "</h2>");
        } finally {
            writer.close();
        }
    }
}
