package controllers.reviews;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Review;
import models.validators.ReviewValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ReviewsCreateServlet
 */
@WebServlet("/reviews/create")
public class ReviewsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Review r = new Review();

            Date reviewDate = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("review_date");
            if(rd_str != null && !rd_str.equals("")) {
                reviewDate = Date.valueOf(request.getParameter("review_date"));
            }
            r.setReviewDate(reviewDate);

            r.setTitle(request.getParameter("title"));
            r.setImpression(request.getParameter("impression"));

            r.setEvaluation(Integer.parseInt(request.getParameter("evaluation")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);

            List<String> errors = ReviewValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("review", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reviews/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(r);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/reviews/index");
            }
        }
    }

}
