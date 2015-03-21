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

import com.mongodb.async.client.AggregateIterable;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static fr.javatic.mongo.rxadapter.Utils.voidResultHandler;

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

    public Observable<Void> toCollection() {
        return Observable.create(subscriber -> delegate.toCollection(voidResultHandler(subscriber, null)));
    }
}
