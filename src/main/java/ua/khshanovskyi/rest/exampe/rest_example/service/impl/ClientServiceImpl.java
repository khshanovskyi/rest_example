package ua.khshanovskyi.rest.exampe.rest_example.service.impl;

import org.springframework.stereotype.Service;
import ua.khshanovskyi.rest.exampe.rest_example.model.Client;
import ua.khshanovskyi.rest.exampe.rest_example.service.ClientService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Map<Integer,Client> CLIENT_REPO_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientId);
        CLIENT_REPO_MAP.put(clientId,client);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(CLIENT_REPO_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_REPO_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (CLIENT_REPO_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_REPO_MAP.put(id, client);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPO_MAP.remove(id) != null;
    }
}
