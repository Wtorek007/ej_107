package com.for_comprehension.function.misc;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class UsersClient {
    public User getUserById(Integer id) {
        return supplyAndMeter(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return new User(id, UUID.randomUUID().toString());
        });
    }

    public CompletableFuture<String> getAddressByUser(User user) {
        return CompletableFuture.supplyAsync(() -> supplyAndMeter(
          () -> {
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
              }

              return "Street: " + user.getId();
          }));
    }

    public User getUserByIdGaussian(Integer id) {
        return supplyAndMeter(() -> {
            try {
                Thread.sleep(1000 + ((int) (ThreadLocalRandom.current().nextGaussian() * 200)));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return new User(id, UUID.randomUUID().toString());
        });
    }

    public static <T> T supplyAndMeter(Supplier<T> supplier) {
        LocalTime now = LocalTime.now();
        T result = supplier.get();
        LocalTime after = LocalTime.now();
        System.out.println(String
          .format("Took %s millis to get [%s]", Duration.between(now, after).toMillis(), result.toString()));
        return result;
    }
}
