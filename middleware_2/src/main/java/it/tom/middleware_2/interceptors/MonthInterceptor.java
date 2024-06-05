package it.tom.middleware_2.interceptors;

import it.tom.middleware_2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1,"January","Gennaio","Januar"),
            new Month(2,"February","Febbraio","Februar"),
            new Month(3,"March","Marzo","Marsch"),
            new Month(4,"April","Aprile","April"),
            new Month(5,"May","Maggio","Mai"),
            new Month(6,"June","Giugno","Juni")
    ));

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String month = request.getHeader("monthNumber");
        if (month.isEmpty() || month == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number");
            return false;
        }
        int monthNum = Integer.parseInt(month);
        Month month1 = months.stream().filter(m -> m.getMonthNumber() == monthNum).findFirst().orElse(new Month(0, "nope", "nope", "nope"));
        request.setAttribute("foundMonth", month1);
        response.setStatus(200);
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler,@NonNull ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler,@NonNull Exception ex) throws Exception {
    }
}
