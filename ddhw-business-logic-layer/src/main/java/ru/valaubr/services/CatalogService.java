package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.enums.Permissions;
import ru.valaubr.models.DataStorage;
import ru.valaubr.models.ServiceUser;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.util.List;

@Slf4j
@Service
public class CatalogService {
    @Autowired
    private CatalogDao catalogDao;
    private DataStorage ds;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Transactional
    public String getCatalogData(Long id) {
        return gson.toJson(catalogDao.findAllByParent(id));
    }

    public void createCatalog(BufferedReader br) {
        DataStorage ds = gson.fromJson(br, DataStorage.class);
        catalogDao.create(ds);

    }

    public void updateCatalog(BufferedReader br) {
        DataStorage ds = gson.fromJson(br, DataStorage.class);
        catalogDao.update(ds);
    }

    public void deleteCatalog(BufferedReader br) {
        DataStorage ds = gson.fromJson(br, DataStorage.class);
        catalogDao.delete(ds);
    }

    public void addDocsToCatalog(ServiceUser serviceUser, List<DocumentDao> documentDAOS, CatalogDao root) {
    }

    public void changeCatalogConfig(Long id, String name, String linkOnDisk, ServiceUser serviceUser) {
    }

    private void sendToModeration(List<DocumentDao> documentDao, CatalogDao root) {
    }

    private Permissions checkPerm(ServiceUser serviceUser) {
        return null;
    }
}
