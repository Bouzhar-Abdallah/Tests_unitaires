package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.HibernateImps.ClientImpl;
import com.majidim.easybankv4.easybankv4.dto.Client;
import com.majidim.easybankv4.easybankv4.newService.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/searchClient")
public class SearchClientServlet extends HttpServlet {
    private ClientService clientService;

    @Override
    public void init() {
        this.clientService = new ClientService(new ClientImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientCode = request.getParameter("code");

        Optional<Client> optClient = clientService.findByCode(clientCode);

        if (!optClient.isEmpty()) {
            Client client = optClient.get();
            String clientDetails = " Last Name: "+ client.getNom() +
                    "\nFirst Name: " + client.getPrenom() +
                    "\nDate of Birth: " + client.getDateN() +
                    "\nPhone: " + client.getTel() +
                    "\nAddress: " + client.getAdress();

            response.setContentType("text/plain");
            response.getWriter().write(clientDetails);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

