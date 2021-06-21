package controllers.reviews;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Review;

/**
 * Servlet implementation class ReviewsNewServlet
 */
@WebServlet("/reviews/new")
public class ReviewsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Review r = new Review();
        r.setReviewDate(new Date(System.currentTimeMillis()));
        request.setAttribute("review", r);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reviews/new.jsp");
        rd.forward(request, response);
    }

}
