package fr.javatic.mongo.rxadapter;

import com.mongodb.async.client.ListIndexesIterable;

import java.util.concurrent.TimeUnit;

public class RxListIndexesIterable<T> extends RxMongoIterable<T> {
    private final ListIndexesIterable<T> delegate;

    public RxListIndexesIterable(ListIndexesIterable<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public RxListIndexesIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return new RxListIndexesIterable<>(delegate.maxTime(maxTime, timeUnit));
    }
}
