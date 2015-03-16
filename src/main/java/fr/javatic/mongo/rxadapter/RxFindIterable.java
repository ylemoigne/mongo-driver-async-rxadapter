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

import com.mongodb.CursorType;
import com.mongodb.async.client.FindIterable;
import org.bson.conversions.Bson;

import java.util.concurrent.TimeUnit;

public class RxFindIterable<T> extends RxMongoIterable<T> {
    private final FindIterable<T> delegate;

    public RxFindIterable(FindIterable<T> delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    public RxFindIterable<T> filter(Bson filter) {
        return new RxFindIterable<>(delegate.filter(filter));
    }

    public RxFindIterable<T> limit(int limit) {
        return new RxFindIterable<>(delegate.limit(limit));
    }

    public RxFindIterable<T> skip(int skip) {
        return new RxFindIterable<>(delegate.skip(skip));
    }

    public RxFindIterable<T> maxTime(long maxTime, TimeUnit timeUnit) {
        return new RxFindIterable<>(delegate.maxTime(maxTime, timeUnit));
    }

    public RxFindIterable<T> modifiers(Bson modifiers) {
        return new RxFindIterable<>(delegate.modifiers(modifiers));
    }

    public RxFindIterable<T> projection(Bson projection) {
        return new RxFindIterable<>(delegate.projection(projection));
    }

    public RxFindIterable<T> sort(Bson sort) {
        return new RxFindIterable<>(delegate.sort(sort));
    }

    public RxFindIterable<T> noCursorTimeout(boolean noCursorTimeout) {
        return new RxFindIterable<>(delegate.noCursorTimeout(noCursorTimeout));
    }

    public RxFindIterable<T> oplogReplay(boolean oplogReplay) {
        return new RxFindIterable<>(delegate.oplogReplay(oplogReplay));
    }

    public RxFindIterable<T> partial(boolean partial) {
        return new RxFindIterable<>(delegate.partial(partial));
    }

    public RxFindIterable<T> cursorType(CursorType cursorType) {
        return new RxFindIterable<>(delegate.cursorType(cursorType));
    }
}
