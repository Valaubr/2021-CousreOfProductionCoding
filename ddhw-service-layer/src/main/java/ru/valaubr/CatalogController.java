package ru.valaubr;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.valaubr.holder.AppContextHolder;
import ru.valaubr.models.User;
import ru.valaubr.services.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CatalogController extends HttpServlet {
    private CatalogService catalogService = null;
    private final Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        catalogService = AppContextHolder.getAppContext().getBean(CatalogService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Long parent_id = Long.getLong(req.getParameter("catalog_id"));
        writer.write(catalogService.getCatalogData(parent_id));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getContentType();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        long id = jsonObject.getAsJsonPrimitive("parent_id").getAsLong();
        User author = new User();
        author.setEmail(jsonObject.getAsJsonPrimitive("author").getAsString());
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();
        catalogService.createCatalog(id, author, name);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getContentType();
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        long id = jsonObject.getAsJsonPrimitive("id").getAsLong();
        String link = jsonObject.getAsJsonPrimitive("name").getAsString();
        String name = jsonObject.getAsJsonPrimitive("link").getAsString();
        catalogService.updateCatalog(id, name, link);
    }
}
