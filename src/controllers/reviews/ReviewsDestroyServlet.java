package controllers.reviews;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Review;
import utils.DBUtil;

/**
 * Servlet implementation class ReviewsDestroyServlet
 */
@WebServlet("/reviews/destroy")
public class ReviewsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewsDestroyServlet() {
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

            em.getTransaction().begin();
            em.remove(r);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除が完了しました。");
            em.close();

            request.getSession().removeAttribute("review_id");

            response.sendRedirect(request.getContextPath() + "/reviews/index");
        }
    }

}
