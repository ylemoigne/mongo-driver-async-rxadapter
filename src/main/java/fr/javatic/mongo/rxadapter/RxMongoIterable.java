package fr.javatic.mongo.rxadapter;

import com.mongodb.Function;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoIterable;
import rx.Observable;

import java.util.Collection;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;
import static fr.javatic.mongo.rxadapter.Utils.voidResultHandler;

public class RxMongoIterable<T> {
    private final MongoIterable<T> delegate;

    public RxMongoIterable(MongoIterable<T> delegate) {
        this.delegate = delegate;
    }

    public Observable<T> first() {
        return Observable.create(subscriber -> delegate.first(resultHandler(subscriber)));
    }

    public Observable<T> toObservable() {
        return Observable.create(subscriber -> delegate.forEach(subscriber::onNext, voidResultHandler(subscriber)));
    }

    public <A extends Collection<? super T>> Observable<A> into(A target, SingleResultCallback<A> callback) {
        return Observable.create(subscriber -> delegate.into(target, resultHandler(subscriber)));
    }

    public <U> RxMongoIterable<U> map(Function<T, U> mapper) {
        return new RxMongoIterable<>(delegate.map(mapper));
    }

    public RxMongoIterable<T> batchSize(int batchSize) {
        return new RxMongoIterable<>(delegate.batchSize(batchSize));
    }

    public Observable<AsyncBatchCursor<T>> batchCursor() {
        return Observable.create(subscriber -> delegate.batchCursor(resultHandler(subscriber)));
    }
}
