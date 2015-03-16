package fr.javatic.mongo.rxadapter;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.AggregateIterable;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;

public class RxAggregateIterable<T> extends RxMongoIterable<T> {
    private final AggregateIterable<T> delegate;

    public RxAggregateIterable(AggregateIterable<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public RxAggregateIterable<T> allowDiskUse(Boolean allowDiskUse) {
        return new RxAggregateIterable<>(delegate).allowDiskUse(allowDiskUse);
    }

    public RxAggregateIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return new RxAggregateIterable<>(delegate.maxTime(maxTime, timeUnit));
    }

    public RxAggregateIterable<T> useCursor(Boolean useCursor) {
        return new RxAggregateIterable<>(delegate.useCursor(useCursor));
    }

    public Observable<Void> toCollection(SingleResultCallback<Void> callback) {
        return Observable.create(subscriber -> delegate.toCollection(resultHandler(subscriber)));
    }
}
