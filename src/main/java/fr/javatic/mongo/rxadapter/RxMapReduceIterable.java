package fr.javatic.mongo.rxadapter;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MapReduceIterable;
import com.mongodb.client.model.MapReduceAction;
import org.bson.conversions.Bson;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;

public class RxMapReduceIterable<T> extends RxMongoIterable<T> {
    private final MapReduceIterable<T> delegate;

    public RxMapReduceIterable(MapReduceIterable<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public RxMapReduceIterable<T> collectionName(String collectionName) {
        return new RxMapReduceIterable<>(delegate.collectionName(collectionName));
    }

    public RxMapReduceIterable<T> finalizeFunction(String finalizeFunction) {
        return new RxMapReduceIterable<>(delegate.finalizeFunction(finalizeFunction));
    }

    public RxMapReduceIterable<T> scope(Bson scope) {
        return new RxMapReduceIterable<>(delegate.scope(scope));
    }

    public RxMapReduceIterable<T> sort(Bson sort) {
        return new RxMapReduceIterable<>(delegate.sort(sort));
    }

    public RxMapReduceIterable<T> filter(Bson filter) {
        return new RxMapReduceIterable<>(delegate.filter(filter));
    }

    public RxMapReduceIterable<T> limit(int limit) {
        return new RxMapReduceIterable<>(delegate.limit(limit));
    }

    public RxMapReduceIterable<T> jsMode(boolean jsMode) {
        return new RxMapReduceIterable<>(delegate.jsMode(jsMode));
    }

    public RxMapReduceIterable<T> verbose(boolean verbose) {
        return new RxMapReduceIterable<>(delegate.verbose(verbose));
    }

    public RxMapReduceIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return new RxMapReduceIterable<>(delegate.maxTime(maxTime, timeUnit));
    }

    public RxMapReduceIterable<T> action(MapReduceAction action) {
        return new RxMapReduceIterable<>(delegate.action(action));
    }

    public RxMapReduceIterable<T> databaseName(String databaseName) {
        return new RxMapReduceIterable<>(delegate.databaseName(databaseName));
    }

    public RxMapReduceIterable<T> sharded(boolean sharded) {
        return new RxMapReduceIterable<>(delegate.sharded(sharded));
    }

    public RxMapReduceIterable<T> nonAtomic(boolean nonAtomic) {
        return new RxMapReduceIterable<>(delegate.nonAtomic(nonAtomic));
    }

    public Observable<Void> toCollection(SingleResultCallback<Void> callback) {
        return Observable.create(subscriber -> delegate.toCollection(resultHandler(subscriber)));
    }
}
