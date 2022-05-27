package az.unibank.unitech.service.functional;


import az.unibank.unitech.dto.RoleDto;
import az.unibank.unitech.enums.RoleEnum;
import az.unibank.unitech.mapping.RoleMapping;
import az.unibank.unitech.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleFunctionalService {

    private final RoleRepository roleRepository;

    public RoleFunctionalService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<RoleDto> findByRoleName(RoleEnum roleEnum) {
        return roleRepository.findByName(roleEnum).map(RoleMapping::toDto);
    }
}
