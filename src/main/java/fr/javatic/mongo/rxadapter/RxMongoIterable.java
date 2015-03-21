/*
 * Copyright 2015 Yann Le Moigne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.javatic.mongo.rxadapter;

import com.mongodb.Function;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoIterable;
import rx.Observable;

import java.util.Collection;

import static fr.javatic.mongo.rxadapter.Utils.resultHandler;

public class RxMongoIterable<T> {
    private final MongoIterable<T> delegate;

    public RxMongoIterable(MongoIterable<T> delegate) {
        this.delegate = delegate;
    }

    public Observable<T> first() {
        return Observable.create(subscriber -> delegate.first(resultHandler(subscriber)));
    }

    public Observable<T> toObservable() {
        return Observable.create(subscriber -> delegate.forEach(subscriber::onNext, (result, t) -> {
            if (t != null) {
                subscriber.onError(t);
            } else {
                subscriber.onCompleted();
            }
        }));
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
