package sn.bacomputer.service;

import sn.bacomputer.dto.UsersDTO;

import java.util.List;

/**
 * Service Interface for managing {@link sn.bacomputer.entity.Users}.
 */
public interface UsersService {

    /**
     * Save a users.
     *
     * @param usersDTO the entity to save.
     * @return the persisted entity.
     */
    UsersDTO save(UsersDTO usersDTO);

    /**
     * Updates a users.
     *
     * @param usersDTO the entity to update.
     * @return the persisted entity.
     */
    UsersDTO update(UsersDTO usersDTO);

    /**
     * Get all the userss.
     *
     * @return the list of entities.
     */
    List<UsersDTO> findAll();

    /**
     * Get the "users" by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    UsersDTO findOne(Long id);

    /**
     * Delete the "users" by id.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
