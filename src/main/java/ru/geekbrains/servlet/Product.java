package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet (name = "Product" , urlPatterns = "/product" )
public class Product implements Servlet{

    private static Logger logger = LoggerFactory.getLogger(Product.class);
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
        res.getWriter().println( "<h1>Товар</h1>" );
        res.getWriter().println("<a href='main'>Главная</a>");
        res.getWriter().println("<a href='catalog'>Каталог</a>");
        res.getWriter().println("<a href='cart'>Корзина</a>");
        res.getWriter().println("<a href='order'>Заказ</a>");
    }
    @Override
    public String getServletInfo () {
        return "Product" ;
    }

    // При завершении работы веб приложения,контейнер вызывает этот метод для всех сервлетовиз этого приложения
    @Override
    public void destroy () {
        logger.info( "Servlet {} destroyed" , getServletInfo());
    }
}
