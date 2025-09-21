package sn.bacomputer.service.impl;

import sn.bacomputer.entity.Service;
import sn.bacomputer.dto.ServiceDTO;
import sn.bacomputer.repository.ServiceRepository;
import sn.bacomputer.service.ServiceService;
import sn.bacomputer.mapper.ServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Service}.
 */
@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final Logger log = LoggerFactory.getLogger(ServiceServiceImpl.class);

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    /**
     * Save a service.
     *
     * @param serviceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        log.debug("Request to save Service : {}", serviceDTO);
        Service service = serviceMapper.toEntity(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    /**
     * Update a service.
     *
     * @param serviceDTO the entity to update.
     * @return the persisted entity.
     */
    @Override
    public ServiceDTO update(ServiceDTO serviceDTO) {
        log.debug("Request to update Service : {}", serviceDTO);
        Service service = serviceMapper.toEntity(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.toDto(service);
    }

    /**
     * Get all the services.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ServiceDTO> findAll() {
        log.debug("Request to get all Services");
        return serviceRepository.findAll().stream()
            .map(serviceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one service by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public ServiceDTO findOne(Long id) {
        log.debug("Request to get Service : {}", id);
        return serviceRepository.findById(id)
            .map(serviceMapper::toDto)
            .orElseThrow(() -> new RuntimeException("Entity not found with id " + id));
    }

    /**
     * Delete the service by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Service : {}", id);
        serviceRepository.deleteById(id);
    }
}
