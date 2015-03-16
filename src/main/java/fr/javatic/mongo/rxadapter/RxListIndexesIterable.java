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
