package sn.bacomputer.service;

import sn.bacomputer.entity.Users;
import sn.bacomputer.dto.UsersDTO;
import sn.bacomputer.mapper.UsersMapper;
import sn.bacomputer.repository.UsersRepository;
import sn.bacomputer.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UsersMapper usersMapper;

    @InjectMocks
    private UsersServiceImpl usersService;

    private Users users;
    private UsersDTO usersDTO;
    private List<Users> usersList;
    private Long testId;

    @BeforeEach
    void setUp() {
        // Setup test data
        testId = 1L;

        users = new Users();
        // Set id directly on the entity using reflection or direct field access if needed
        // For test purposes, we'll mock the responses instead of relying on actual entity getter methods

        usersDTO = new UsersDTO();
        usersDTO.setId(testId);

        usersList = new ArrayList<>();
        usersList.add(users);
    }

    @Test
    void testSave() {
        // Arrange
        when(usersMapper.toEntity(any(UsersDTO.class))).thenReturn(users);
        when(usersRepository.save(any(Users.class))).thenReturn(users);
        when(usersMapper.toDto(any(Users.class))).thenReturn(usersDTO);

        // Act
        UsersDTO result = usersService.save(usersDTO);

        // Assert
        assertThat(result).isNotNull();
        verify(usersRepository).save(any(Users.class));
        verify(usersMapper).toDto(any(Users.class));
    }

    @Test
    void testFindAll() {
        // Arrange
        List<UsersDTO> dtoList = new ArrayList<>();
        dtoList.add(usersDTO);

        when(usersRepository.findAll()).thenReturn(usersList);

        // Stub the individual entity mapping instead of the list mapping
        // This correctly handles the stream().map() call in the service
        when(usersMapper.toDto(any(Users.class))).thenReturn(usersDTO);

        // Act
        List<UsersDTO> result = usersService.findAll();

        // Assert
        assertThat(result).isNotNull().hasSize(1);
        verify(usersRepository).findAll();
    }

    @Test
    void testFindOne() {
        // Arrange
        when(usersRepository.findById(any())).thenReturn(Optional.of(users));
        when(usersMapper.toDto(any(Users.class))).thenReturn(usersDTO);

        // Act
        UsersDTO result = usersService.findOne(testId);

        // Assert
        assertThat(result).isNotNull();
        verify(usersRepository).findById(any());
    }

    @Test
    void testDelete() {
        // Arrange
        doNothing().when(usersRepository).deleteById(any());

        // Act
        usersService.delete(testId);

        // Assert
        verify(usersRepository).deleteById(any());
    }
}
