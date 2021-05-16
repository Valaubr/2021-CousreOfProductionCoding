package ru.valaubr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.CatalogWhiteListDto;
import ru.valaubr.jpa.CatalogRepo;
import ru.valaubr.jpa.CatalogWhiteListRepo;
import ru.valaubr.jpa.ServiceUserRepo;
import ru.valaubr.models.CatalogWhiteList;

@Service
public class PermissionService {

    @Autowired
    private CatalogWhiteListRepo repo;
    @Autowired
    private CatalogRepo catalogRepo;
    @Autowired
    private ServiceUserRepo serviceUserRepo;

    public void setCatalogPermToUser(CatalogWhiteListDto catalogWhiteListDto) {
        CatalogWhiteList permPair = repo.getPermForCatalog(
                serviceUserRepo.findByEmail(catalogWhiteListDto.getEmail()).getId(),
                catalogWhiteListDto.getCatalog());
        if (permPair != null) {
            permPair.setPermissions(catalogWhiteListDto.getPermissions());
        } else {
            permPair = new CatalogWhiteList();
            permPair.setPermissions(catalogWhiteListDto.getPermissions());
            permPair.setCatalog(catalogRepo.findById(catalogWhiteListDto.getCatalog()).get());
            permPair.setServiceUser(serviceUserRepo.findByEmail(catalogWhiteListDto.getEmail()));
        }
        repo.save(permPair);
    }
}
