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
 * Servlet implementation class ReviewsUpdateServlet
 */
@WebServlet("/reviews/update")
public class ReviewsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewsUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Review r = em.find(Review.class, (Integer)(request.getSession().getAttribute("review_id")));

            r.setReviewDate(Date.valueOf(request.getParameter("review_date")));
            r.setName(request.getParameter("name"));
            r.setTitle(request.getParameter("title"));
            r.setImpression(request.getParameter("impression"));
            r.setEvaluation(Integer.parseInt(request.getParameter("evaluation")));
            r.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = ReviewValidator.validate(r);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("review", r);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reviews/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("review_id");

                response.sendRedirect(request.getContextPath() + "/reviews/index");
            }
        }
    }
}
