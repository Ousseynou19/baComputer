package sn.bacomputer.service;


import sn.bacomputer.DTO.ServiceDTO;

import java.util.List;

public interface ServiceBusiness {
    ServiceDTO create(ServiceDTO dto);
    List<ServiceDTO> listAll();
    ServiceDTO get(Long id);
    ServiceDTO update(Long id, ServiceDTO dto);
    void delete(Long id);
}

