package sn.bacomputer.service;

import sn.bacomputer.dto.ServiceDTO;

import java.util.List;

/**
 * Service Interface for managing {@link sn.bacomputer.entity.Service}.
 */
public interface ServiceService {

    /**
     * Save a service.
     *
     * @param serviceDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceDTO save(ServiceDTO serviceDTO);

    /**
     * Updates a service.
     *
     * @param serviceDTO the entity to update.
     * @return the persisted entity.
     */
    ServiceDTO update(ServiceDTO serviceDTO);

    /**
     * Get all the services.
     *
     * @return the list of entities.
     */
    List<ServiceDTO> findAll();

    /**
     * Get the "service" by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    ServiceDTO findOne(Long id);

    /**
     * Delete the "service" by id.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
