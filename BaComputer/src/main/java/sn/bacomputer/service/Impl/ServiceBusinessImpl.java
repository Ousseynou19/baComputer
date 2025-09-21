package sn.bacomputer.service.Impl;


import org.springframework.stereotype.Service;
import sn.bacomputer.DTO.ServiceDTO;
import sn.bacomputer.entity.ServiceEntity;
import sn.bacomputer.mapper.ServiceMapper;
import sn.bacomputer.repository.ServiceRepository;
import sn.bacomputer.service.ServiceBusiness;

import java.util.List;

@Service
public class ServiceBusinessImpl implements ServiceBusiness {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceBusinessImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public ServiceDTO create(ServiceDTO dto) {
        ServiceEntity entity = serviceMapper.toEntity(dto);
        return serviceMapper.toDTO(serviceRepository.save(entity));
    }

    @Override
    public List<ServiceDTO> listAll() {
        return serviceRepository.findAll().stream().map(serviceMapper::toDTO).toList();
    }

    @Override
    public ServiceDTO get(Long id) {
        return serviceRepository.findById(id).map(serviceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public ServiceDTO update(Long id, ServiceDTO dto) {
        ServiceEntity entity = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        return serviceMapper.toDTO(serviceRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }
}
