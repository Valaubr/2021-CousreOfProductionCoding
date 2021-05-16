package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.CatalogDto;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.enums.Permissions;
import ru.valaubr.enums.Role;
import ru.valaubr.jpa.CatalogRepo;
import ru.valaubr.jpa.CatalogWhiteListRepo;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.CatalogWhiteList;
import ru.valaubr.models.DataStorage;
import ru.valaubr.models.ServiceUser;
import ru.valaubr.services.security.JwtProvider;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ServiceUserRepo user;
    @Autowired
    private CatalogWhiteListRepo whiteListRepo;
    @Autowired
    private JwtProvider provider;
    private DataStorage ds;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Transactional
    public List<CatalogDto> getCatalogData(Long id) {
        return catalogRepo.findChildlessByParentId(id).stream().map(CatalogDto::new).collect(Collectors.toList());
    }

    public ResponseEntity createCatalog(CatalogDto catalogDto, String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        if (catalogDto.getParentId() != null) {
            if (!checkPerm(sr, catalogDto.getParentId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            DataStorage ds = new DataStorage();
            ds.setName(catalogDto.getName());
            ds.setDateOfCreation(catalogDto.getDateOfCreation());
            ds.setParentId(catalogDto.getParentId());
            ds.setPathOnDisk(catalogDto.getPathOnDisk());
            ds.setAuthor(sr);
            ds.setFolder(true);
            catalogRepo.save(ds);
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity updateCatalog(CatalogDto catalogDto, String auth) {
        auth = provider.getLoginFromToken(auth.substring(7));
        ServiceUser sr = user.findByEmail(auth);
        if (catalogDto.getId() != null) {
            if (!checkPerm(sr, catalogDto.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            DataStorage ds = catalogRepo.findById(catalogDto.getId()).get();
            ds.setName(catalogDto.getName());
            ds.setDateOfCreation(catalogDto.getDateOfCreation());
            ds.setParentId(catalogDto.getParentId());
            ds.setPathOnDisk(catalogDto.getPathOnDisk());
            ds.setAuthor(sr);
            ds.setFolder(true);
            catalogRepo.save(ds);
        }
        return ResponseEntity.ok().build();
    }

    private Boolean checkPerm(ServiceUser sr, Long usedId) {
        CatalogWhiteList a = whiteListRepo.getPermForCatalog(sr.getId(), usedId);
        if (sr.getRole() == Role.ROLE_ADMINISTRATOR) {
            return true;
        } else {
            return a != null && a.getPermissions() != Permissions.READ;
        }
    }
}
