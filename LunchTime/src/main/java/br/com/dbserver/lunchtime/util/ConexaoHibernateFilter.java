/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.util;

import java.io.IOException;
import javax.servlet.*;
import org.hibernate.SessionFactory;

/**
 *
 * @author Luiz Henrique
 */
public class ConexaoHibernateFilter implements Filter {

    private SessionFactory sf;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        try {
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(servletRequest, servletResponse);
            this.sf.getCurrentSession().getTransaction().commit();
            this.sf.getCurrentSession().close();
        } catch (Throwable ex) {
            try {
                this.sf.getCurrentSession().getTransaction().rollback();
            } catch (Throwable t) {
                t.printStackTrace();
            }
            throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
