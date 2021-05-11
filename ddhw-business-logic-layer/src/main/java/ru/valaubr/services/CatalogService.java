package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.CatalogDto;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.CatalogRepo;
import ru.valaubr.jpa.CatalogWhiteListRepo;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.DataStorage;
import ru.valaubr.models.ServiceUser;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ServiceUserRepo user;
    @Autowired
    CatalogWhiteListRepo whiteListRepo;
    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    private DataStorage ds;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Transactional
    public List<CatalogDto> getCatalogData(Long id) {
        return catalogRepo.findChildlessByParentId(id).stream().map(CatalogDto::new).collect(Collectors.toList());
    }

    public void createCatalog(CatalogDto catalogDto) {
        DataStorage ds = new DataStorage();
        user.findByEmail(catalogDto.getAuthor());
        if (catalogDto.getParentId() != null) {
            if (!checkPerm()) {
                return;
            }
            ds.setName(catalogDto.getName());
            ds.setDateOfCreation(catalogDto.getDateOfCreation());
            ds.setParentId(catalogDto.getParentId());
            ds.setPathOnDisk(catalogDto.getPathOnDisk());
            ServiceUser tmp = new ServiceUser();
            tmp.setEmail(authentication.getPrincipal().toString());
            ds.setAuthor(tmp);
            ds.setFolder(true);
            catalogRepo.save(ds);
        }
    }

    public void updateCatalog(BufferedReader br) {

    }

    public void addDocsToCatalog(ServiceUser serviceUser, List<DocumentDto> documentDto, CatalogDto root) {
    }

    public void changeCatalogConfig(Long id, String name, String linkOnDisk, ServiceUser serviceUser) {
    }

    private void sendToModeration(List<DocumentDto> documentDto, CatalogDto root) {
    }

    private Boolean checkPerm() {
        if (authentication.getAuthorities().contains(Role.ROLE_ADMINISTRATOR)) {
            return true;
        } else {
            Permissions perm = whiteListRepo.getRoleForCatalog(authentication.getPrincipal().toString(), 0L).getPermissions();
            return perm != Permissions.READ;
        }
    }
}
