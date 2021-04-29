package ru.valaubr;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.valaubr.enums.Importance;
import ru.valaubr.holder.AppContextHolder;
import ru.valaubr.models.Document;
import ru.valaubr.models.User;
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
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        Long id = jsonObject.getAsJsonPrimitive("parent_id").getAsLong();
        User author = new User();
        author.setEmail(jsonObject.getAsJsonPrimitive("author").getAsString());
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();
        String link = jsonObject.getAsJsonPrimitive("link").getAsString();
        String description = jsonObject.getAsJsonPrimitive("description").getAsString();
        Importance importance = Importance.valueOf(jsonObject.getAsJsonPrimitive("importance").getAsString());

        documentService.createDoc(id, name, author, link, description, importance);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getContentType();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);

        resp.setStatus(400);

        Long id = jsonObject.getAsJsonPrimitive("id").getAsLong();
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();
        String link = jsonObject.getAsJsonPrimitive("link").getAsString();
        String description = jsonObject.getAsJsonPrimitive("description").getAsString();
        Importance importance = Importance.valueOf(jsonObject.getAsJsonPrimitive("importance").getAsString());

        documentService.updateDoc(id, name, link, description, importance);
    }
}
