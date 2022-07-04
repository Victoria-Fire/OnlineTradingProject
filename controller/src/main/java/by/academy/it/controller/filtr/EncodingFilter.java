package by.academy.it.controller.filtr;

import by.academy.it.controller.constants.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(Constant.DEFAULT_CHARACTER_ENCODING);
        response.setCharacterEncoding(Constant.DEFAULT_CHARACTER_ENCODING);
        chain.doFilter(request, response);
    }
}
