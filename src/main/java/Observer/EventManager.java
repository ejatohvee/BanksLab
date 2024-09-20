package Observer;

import Banks.Clients.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<EventType, List<Client>> subscribers = new HashMap<>();

    public void Subscribe(EventType event, Client client) {
        subscribers.get(event).add(client);
    }

    public void Notify(EventType event, String message) {
        subscribers.get(event).forEach(subscriber -> subscriber.Update(message));
    }
}
