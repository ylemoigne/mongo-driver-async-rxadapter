package fr.javatic.mongo.rxadapter;

import com.mongodb.async.client.DistinctIterable;
import org.bson.conversions.Bson;

import java.util.concurrent.TimeUnit;

public class RxDistinctIterable<T> extends RxMongoIterable<T> {
    private final DistinctIterable<T> delegate;

    public RxDistinctIterable(DistinctIterable<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public RxDistinctIterable<T> filter(Bson filter) {
        return new RxDistinctIterable<>(delegate.filter(filter));
    }

    public RxDistinctIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return new RxDistinctIterable<>(delegate.maxTime(maxTime, timeUnit));
    }
}
