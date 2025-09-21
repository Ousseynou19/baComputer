package sn.bacomputer.controller;

import sn.bacomputer.dto.UsersDTO;
import sn.bacomputer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Users entities.
 */
@RestController
@RequestMapping("/api/userss")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * GET /api/userss : Get all Userss.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userss in body
     */
    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUserss() {
        List<UsersDTO> list = usersService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * GET /api/userss/{id} : Get the users by id.
     *
     * @param id the id of the users to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usersDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable Long id) {
        UsersDTO usersDTO = usersService.findOne(id);
        return ResponseEntity.ok(usersDTO);
    }

    /**
     * POST /api/userss : Create a new users.
     *
     * @param usersDTO the usersDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usersDTO
     */
    @PostMapping
    public ResponseEntity<UsersDTO> createUsers(@RequestBody UsersDTO usersDTO) {
        UsersDTO result = usersService.save(usersDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * PUT /api/userss : Updates an existing users.
     *
     * @param usersDTO the usersDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usersDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> updateUsers(@PathVariable Long id, @RequestBody UsersDTO usersDTO) {
        usersDTO.setId(id);
        UsersDTO result = usersService.update(usersDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * DELETE /api/userss/{id} : delete the "id" users.
     *
     * @param id the id of the users to delete
     * @return the ResponseEntity with status 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
