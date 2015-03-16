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
