package application.talk.usecases.user;

import application.talk.domains.PrivateGroup;
import application.talk.domains.Request;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.enums.RequestStatus;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class AdminRequestExecutionTest {
    DataStorage _storage;
    AdminRequestExecution _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new AdminRequestExecution(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testAdminExecuteRequest() {
        User admin = new User("Trong", "123456");
        PrivateGroup privateGroup = new PrivateGroup("Group A", admin);
        User requester = new User("Requester", "654321");
        Request request = new Request(requester, privateGroup, LocalDateTime.now(), RequestStatus.WAITING);

        _storage.getGroups().add(privateGroup);

        AdminRequestExecution.InputValues input = new AdminRequestExecution.InputValues(privateGroup, admin, request, RequestStatus.APPROVE);
        AdminRequestExecution.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}