package ru.valaubr;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import ru.valaubr.enums.Importance;
import ru.valaubr.holder.AppContextHolder;
import ru.valaubr.models.Document;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DocumentController extends HttpServlet {
    private DocumentService documentService;
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        documentService = AppContextHolder.getAppContext().getBean(DocumentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Document document = documentService.getDoc(Long.valueOf(req.getParameter("id")));
        resp.getWriter().write(gson.toJson(document));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getContentType();
        documentService.createDoc(req.getReader());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getContentType();
        documentService.updateDoc(req.getReader());
    }
}
