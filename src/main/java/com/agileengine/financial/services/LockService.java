package com.agileengine.financial.services;

import com.agileengine.financial.exceptions.LockedException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LockService {

  private final Map<String, String> store = new ConcurrentHashMap<>();

  public boolean isLocked(String id) {
    return store.get(id) != null;
  }

  public void unlock(String id) {
    store.remove(id);
  }

  public void lock(String id) {
    if (isLocked(id)) {
      throw new LockedException("locked", "you cant acces to this element now",
          HttpStatus.PRECONDITION_FAILED);
    }
    store.put(id, id);
  }
}
