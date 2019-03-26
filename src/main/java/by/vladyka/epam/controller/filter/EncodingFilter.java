package by.vladyka.epam.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Vladyka Stas
 * on 23.02.2019 at 16:21
 **/
public class EncodingFilter implements Filter {
    private static final String FILTERABLE_CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;

    // MIME-тип (называемый "media type", а иногда "content type") - это строка,
    // отправляемая вместе с файлом, которая указывает тип файла.
    // (например, передаваемый аудиофайл может быть помечен как audio/ogg тип, а изображение - image/png).
    // MIME-тип играет точно такую же роль, как и расширение файла в системе Windows.
    // Когда HTTP-сообщение содержит Content-type заголовок,
    // тело запроса будет парситься в соответствии с MIME-типом, указанным в заголовке.
    // application/x-www-form-urlencoded - MIME-тип
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String contentType = servletRequest.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE)) {
            servletRequest.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
