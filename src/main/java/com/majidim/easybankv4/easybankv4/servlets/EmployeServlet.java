package com.majidim.easybankv4.easybankv4.servlets;

import com.majidim.easybankv4.easybankv4.dto.Employe;
import com.majidim.easybankv4.easybankv4.dto.Personne;
import com.majidim.easybankv4.easybankv4.HibernateImps.EmployeImpl;
import com.majidim.easybankv4.easybankv4.newService.EmployeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/employe")

public class EmployeServlet extends HttpServlet {
    private EmployeService employeService;

    @Override
    public void init(){
        this.employeService = new EmployeService(new EmployeImpl());
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "list";
        }
        switch (action){
            case "add" -> addEmploye(request,response);
            case "edit" -> editEmploye(request, response);
            case "update" -> updateEmploye(request, response);
            //case "search" -> searchEmployes(request, response);
            case "delete" -> deleteEmploye(request, response);
            default -> listEmploye(request,response);
        }

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    private void listEmploye(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        List<Employe> employes = employeService.ShowList();
        request.setAttribute("employes",employes);
        request.getRequestDispatcher("/view/employe/employelist.jsp").forward(request,response);
    }



    /*private void searchEmployes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        List<Employe> searchResults = employeService.SearchByEmail(query);

        request.setAttribute("employes", searchResults);
        request.getRequestDispatcher("/view/employe/employelist.jsp").forward(request,response);
    }*/


    private void addEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        LocalDate dateRecrutement = LocalDate.parse(request.getParameter("dateRecrutement"));
        String emailAdresse = request.getParameter("emailadresse");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateN = LocalDate.parse(request.getParameter("dateN"));
        String tel = request.getParameter("tel");
        String adress = request.getParameter("adress");
        Employe employe = new Employe(nom, prenom, dateN, tel, adress, emailAdresse, matricule, dateRecrutement);

        try {
            Optional<Employe> addedEmploye = employeService.create(employe);
            if (addedEmploye.isPresent()) {
                request.setAttribute("successMessage", "Employee added successfully!");
            } else {
                request.setAttribute("errorMessage", "Failed to add employee");
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Employee with the same matricule already exists");
        }

        request.getRequestDispatcher("/view/employe/addemploye.jsp").forward(request, response);
    }


    private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String matricule = request.getParameter("matricule");
        if (matricule != null){
            boolean deleted = employeService.delete(matricule);
            if (deleted){
                response.sendRedirect(request.getContextPath() + "/employe?success=delete-success");
            }else {
                response.sendRedirect("/employe?error=delete-failed");
            }

        }else {
            response.sendRedirect(request.getContextPath() + "/employe");

        }
    }
    private void editEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        if (matricule != null) {
            Optional<Employe> optEmp = employeService.findByMatricule(matricule);
            if (!optEmp.isEmpty()) {
                Employe employer = optEmp.get();
                request.setAttribute("employe", employer);
                request.getRequestDispatcher("/view/employe/employeedit.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/employe");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/employe");
        }
    }

    private void updateEmploye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        LocalDate dateRecrutement = LocalDate.parse(request.getParameter("dateRecrutement"));
        String emailAdresse = request.getParameter("emailadresse");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        LocalDate dateN = LocalDate.parse(request.getParameter("dateN"));
        String tel = request.getParameter("tel");
        String adress = request.getParameter("adress");

        Employe employe = new Employe(nom, prenom, dateN, tel, adress, emailAdresse, matricule, dateRecrutement);
        Optional<Employe> updatedEmploye = employeService.Update(employe);

        if (updatedEmploye.isPresent()) {
            request.setAttribute("successMessage", "Employee updated successfully!");
            request.getRequestDispatcher("/view/employe/employeedit.jsp").forward(request, response);
        } else {
            response.sendRedirect("/employe?error=update-failed");
        }
    }

    }
